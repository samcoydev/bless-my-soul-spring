package blessmysoulbackend.rest.controller;

import blessmysoulbackend.rest.dto.ItemDto;
import blessmysoulbackend.rest.dto.OrderDto;
import blessmysoulbackend.rest.model.Item;
import blessmysoulbackend.rest.model.Order;
import blessmysoulbackend.rest.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("api/v1/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping
    public List<Order> getAllOrders() {
        System.out.println("[GET] All Orders");
        return orderService.findAll();
    }

    @GetMapping("/{id}")
    public Order getOrderByID(@PathVariable Long id) {
        System.out.println("[GET] Order with ID: " + id);
        return orderService.findById(id);
    }

    @GetMapping("/user/{id}")
    public List<Order> getOrdersByUserID(@PathVariable Long id) {
        System.out.println("[GET] Order with ID: " + id);
        return orderService.findOrdersByUserID(id);
    }

    @PostMapping
    public Order saveOrder(@Valid @RequestBody OrderDto order) {
        System.out.println("[POST] " + order.getId());
        return orderService.save(order);
    }

    @PutMapping("/{id}")
    public Order updateOrder(@PathVariable Long id, @Valid @RequestBody OrderDto order) {
        System.out.println("[PUT] Order with an id of: " + order.getId());
        return orderService.update(id, order);
    }

    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable Long id) {
        System.out.println("[DELETE] Order: " + id);
        orderService.delete(id);
    }

}
