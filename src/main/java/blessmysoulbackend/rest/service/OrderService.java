package blessmysoulbackend.rest.service;

import blessmysoulbackend.rest.dto.OrderDto;
import blessmysoulbackend.rest.model.Order;

import java.util.List;

public interface OrderService {

    Order save(OrderDto order);
    void delete(long id);

    List<Order> findAll();

    Order findById(Long id);

    List<Order> findByUserId(Long id);
}
