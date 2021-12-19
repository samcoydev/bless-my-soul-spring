package blessmysoulbackend.rest.service.impl;

import blessmysoulbackend.rest.dao.CartItemDao;
import blessmysoulbackend.rest.dao.OrderDao;
import blessmysoulbackend.rest.dto.ItemDto;
import blessmysoulbackend.rest.dto.OrderDto;
import blessmysoulbackend.rest.model.CartItem;
import blessmysoulbackend.rest.model.Item;
import blessmysoulbackend.rest.model.Order;
import blessmysoulbackend.rest.service.CartItemService;
import blessmysoulbackend.rest.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Transactional
@Service(value = "orderService")
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private CartItemDao cartItemDao;

    @Autowired
    private CartItemService cartItemService;

    public List<Order> findAll() {
        return orderDao.findByOrderById();
    }

    @Override
    public Order findById(Long id) {
        return orderDao.findById(id).get();
    }

    @Override
    public List<Order> findOrdersByUserID(Long id) {
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

        newOrder.getCartItems().forEach(cartItem -> {
            cartItemService.attachToOrder(cartItem.getId());
        });

        orderDao.save(newOrder);

        return orderDao.save(newOrder);
    }

    public Order update(long id, OrderDto order) {
        Optional<Order> optionalItem = orderDao.findById(id);
        if (optionalItem.isPresent()) {
            Order existingOrder = optionalItem.get();
            existingOrder.setUser(order.getUser());
            existingOrder.setCartItems(order.getCartItems());
            existingOrder.setNotes(order.getNotes());
            existingOrder.setState(order.getState());

            return orderDao.save(existingOrder);
        } else {
            return null;
        }
    }

    @Override
    public void delete(long id) {
        Order order = orderDao.findById(id).get();
        List<CartItem> ciList = order.getCartItems();
        order.setCartItems(Collections.emptyList());
        ciList.forEach(cartItem -> {
            CartItem _cartItem = cartItemDao.findById(cartItem.getId()).get();
            cartItemDao.delete(_cartItem);
        });
        orderDao.delete(order);
    }

}
