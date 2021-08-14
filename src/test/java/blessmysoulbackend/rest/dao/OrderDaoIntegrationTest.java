package blessmysoulbackend.rest.dao;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import blessmysoulbackend.rest.RestApplication;
import blessmysoulbackend.rest.helpers.OrderType;
import blessmysoulbackend.rest.helpers.RoleType;
import blessmysoulbackend.rest.model.CartItem;
import blessmysoulbackend.rest.model.Item;
import blessmysoulbackend.rest.model.Order;
import blessmysoulbackend.rest.model.User;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RestApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(
		  locations = "classpath:application-test.properties")
@Slf4j
public class OrderDaoIntegrationTest {

	@Autowired
	OrderDao orderDao;
	
	@Autowired
	UserDao userDao;
	
	@Autowired
	ItemDao itemDao;
	
	@Test
	public void saveAnOrderWithItemsThenVerifySaved() {
		log.info("Start test");
		User user = new User();
		user.setEmail("christophercoy@live.com");
		user.setFirstname("Chris");
		user.setLastname("Coy");
		user.setPassword("password");
		user.setRole(RoleType.ADMIN);
		user.setUsername("ccoy");
		userDao.save(user);
		
		log.info("User saved: " + user.toString());
		org.junit.jupiter.api.Assertions.assertNotEquals(user.getId(), 0l);
		
		Order order = new Order();
		order.setNotes("Test notes");
		order.setState(OrderType.ORDER_RECEIVED);
		order.setUser(user);
		
		Item item = new Item();
		item.setDescription("A test item");
		item.setName("TEST");
		item.setPrice(1.99f);
		
		itemDao.save(item);
		
		log.info("Saved item: " + item.toString());
		org.junit.jupiter.api.Assertions.assertNotEquals(item.getId(), 0l);
		
		CartItem cartItem = new CartItem();
		cartItem.setItem(item);
		cartItem.setQty(2f);
		cartItem.setUser(user);
		
		order.getCartItems().add(cartItem);
		orderDao.save(order);
		
		org.junit.jupiter.api.Assertions.assertNotNull(order.getCartItems());
		org.junit.jupiter.api.Assertions.assertEquals(1, order.getCartItems().size());
	}
	
}
