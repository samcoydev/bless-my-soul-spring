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
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping("/{id}")
    public List<CartItem> getCartItemsByUserID(@PathVariable Long id) {
        System.out.println("[GET] All cart items for user with id: " + id);
        return cartService.findCartItemsByUserID(id);
    }

    @PostMapping
    public CartItem saveCartItem(@Valid @RequestBody CartDto cartItem) {
        System.out.println("[POST] Cart Item with an ID of: " + cartItem.getId());
        List<CartItem> existingCartItems = cartService.findCartItemsWithSameUserIDAndItemID(cartItem.getUser().getId(), cartItem.getItem().getId());
        if (!existingCartItems.isEmpty()) {
            System.out.println("[POST] Cart Item already exists, increasing quantity by one: " + cartItem.getId());
            return cartService.updateQty(existingCartItems.get(0).getId(), cartItem, existingCartItems.get(0).getQty() + 1);
        }
        return cartService.save(cartItem);
    }

    @PutMapping("/{id}")
    public CartItem updateCartItem(@PathVariable Long id, @Valid @RequestBody CartDto cartItem) {
        System.out.println("[PUT] Update Cart Item -NOTE: Server can only update an items quantity-: " + cartItem.getId());
        return cartService.updateQty(id, cartItem, cartItem.getQty());
    }

    @DeleteMapping("/{id}")
    public void deleteItem(@PathVariable Long id) {
        System.out.println("[DELETE] Cart Item: " + id);
        cartService.delete(id);
    }
}
