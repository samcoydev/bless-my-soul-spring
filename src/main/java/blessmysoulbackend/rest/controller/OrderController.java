package blessmysoulbackend.rest.controller;

import blessmysoulbackend.rest.dto.OrderDto;
import blessmysoulbackend.rest.model.Order;
import blessmysoulbackend.rest.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("api/v1/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping
    public List<Order> getAllOrders() {
        log.info("[GET] All Orders");
        return orderService.findAll();
    }

    @GetMapping("/{id}")
    public Order getOrderByID(@PathVariable Long id) {
        log.info("[GET] Order with ID: " + id);
        return orderService.findById(id);
    }

    @GetMapping("/user/{id}")
    public List<Order> getOrdersByUserID(@PathVariable Long id) {
        log.info("[GET] Order with ID: " + id);
        return orderService.findOrdersByUserID(id);
    }

    @PostMapping
    public Order saveOrder(@Valid @RequestBody OrderDto order) {
        log.info("[POST] " + order.getId());
        return orderService.save(order);
    }

    @PutMapping("/{id}")
    public Order updateOrder(@PathVariable Long id, @Valid @RequestBody OrderDto order) {
        log.info("[PUT] Order with an id of: " + order.getId());
        return orderService.update(id, order);
    }

    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable Long id) {
        log.info("[DELETE] Order: " + id);
        orderService.delete(id);
    }

}
