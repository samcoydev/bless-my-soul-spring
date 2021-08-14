package blessmysoulbackend.rest.service.impl;

import blessmysoulbackend.rest.dao.OrderDao;
import blessmysoulbackend.rest.dto.OrderDto;
import blessmysoulbackend.rest.model.Order;
import blessmysoulbackend.rest.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service(value = "orderService")
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderDao orderDao;

    public List<Order> findAll() {
        List<Order> orderList = new ArrayList<>();
        orderDao.findByOrderById().iterator().forEachRemaining(orderList::add);
        return orderList;
    }

    @Override
    public Order findById(Long id) {
        return orderDao.findById(id).get();
    }

    @Override
    public List<Order> findByUserId(Long id) {
        List<Order> orderList = new ArrayList<>();
        orderDao.findByOrderById().iterator().forEachRemaining(order -> {
            if (order.getUser().getId() == id)
                orderList.add(order);
        });
        return orderList;
    }

    public Order save(OrderDto order) {
        Order newOrder = new Order();
        newOrder.setUser(order.getUser());
        newOrder.setCartItems(order.getCartItems());
        newOrder.setNotes(order.getNotes());
        newOrder.setState(order.getState());

        System.out.println("BEEP BEEP -------------- \n" +
                "user: " + newOrder.getUser() + "\n" +
                "cartItems: " + newOrder.getCartItems() + "\n" +
                "notes: " + newOrder.getNotes() + "\n" +
                "state: " + newOrder.getUser());

        newOrder.getCartItems().forEach(i -> i.setOrder(newOrder));

        orderDao.save(newOrder);

        return orderDao.save(newOrder);
    }

    @Override
    public void delete(long id) {
        Order order = orderDao.findById(id).get();
        orderDao.delete(order);
    }

}
