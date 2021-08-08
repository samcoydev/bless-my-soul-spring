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
    public List<CartItem> getCartItemsByUserID(@PathVariable Long id) {
        System.out.println("[GET] All cart items for user with id: " + id);
        return cartService.findCartItemsByUserID(id);
    }

    @PostMapping
    public CartItem saveCartItem(@Valid @RequestBody CartDto cartItem) {
        System.out.println("[POST] cart item with an ID of: " + cartItem.getId());
        List<CartItem> existingCartItems = cartService.findCartItemsWithSameUserIDAndItemID(cartItem.getUserID(), cartItem.getItemID());
        if (!existingCartItems.isEmpty())
            return cartService.updateQty(existingCartItems.get(0).getId(), cartItem);

        return cartService.save(cartItem);
    }

}
