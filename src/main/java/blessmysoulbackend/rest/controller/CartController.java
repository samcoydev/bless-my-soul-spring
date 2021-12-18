package blessmysoulbackend.rest.controller;

import blessmysoulbackend.rest.dto.CartItemDto;
import blessmysoulbackend.rest.model.CartItem;
import blessmysoulbackend.rest.service.CartItemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("api/v1/cart")
public class CartController {

    @Autowired
    private CartItemService cartItemService;

    @GetMapping("/{id}")
    public List<CartItem> getCartItemsByUserID(@PathVariable Long id) {
        log.info("[GET] All cart items for user with id: " + id);
        return cartItemService.findCartItemsByUserID(id);
    }

    @PostMapping
    public CartItem saveCartItem(@Valid @RequestBody CartItemDto cartItem) {
        log.info("[POST] Cart Item with an ID of: " + cartItem.getId());
        List<CartItem> existingCartItems = cartItemService.findCartItemsWithSameUserIDAndItemID(cartItem.getUser().getId(), cartItem.getItem().getId());
        if (!existingCartItems.isEmpty()) {
            log.info("[POST] Cart Item already exists, increasing quantity by one: " + cartItem.getId());
            return cartItemService.updateQty(existingCartItems.get(0).getId(), existingCartItems.get(0).getQty() + 1);
        }
        return cartItemService.save(cartItem);
    }

    @PutMapping("/{id}")
    public CartItem updateCartItem(@PathVariable Long id, @Valid @RequestBody CartItemDto cartItem) {
        log.info("[PUT] Update Cart Item -NOTE: Server can only update an items quantity-: " + cartItem.getId());
        return cartItemService.updateQty(id, cartItem.getQty());
    }

    @DeleteMapping("/{id}")
    public void deleteItem(@PathVariable Long id) {
        log.info("[DELETE] Cart Item: " + id);
        cartItemService.delete(id);
    }
}
