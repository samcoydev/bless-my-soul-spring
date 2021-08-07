package blessmysoulbackend.rest.controller;

import blessmysoulbackend.rest.dto.CartDto;
import blessmysoulbackend.rest.model.CartItem;
import blessmysoulbackend.rest.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Table;
import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("api/v1/cart")
@Table(name = "cart_items")
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping("/{id}")
    public List<CartItem> getCartItemsByUserId(@PathVariable Long id) {
        System.out.println("[GET] All cart items for user with id: " + id);
        return cartService.findCartItemsByUserId(id);
    }

    @PostMapping
    public CartItem saveCartItem(@Valid @RequestBody CartDto cartItem) {
        System.out.println("[POST] " + cartItem.getName());
        return cartService.save(cartItem);
    }

}
