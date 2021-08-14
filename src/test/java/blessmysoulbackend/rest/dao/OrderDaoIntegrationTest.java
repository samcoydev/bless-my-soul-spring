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
		
		Order order = new Order();
		order.setNotes("Test notes");
		order.setState(OrderType.ORDER_RECEIVED);
		
	}
	
}
