package blessmysoulbackend.rest.service;

import blessmysoulbackend.rest.dto.CartDto;
import blessmysoulbackend.rest.model.CartItem;

import java.util.List;

public interface CartService {

    CartItem save(CartDto cartItem);
    CartItem updateQty(long id, CartDto cartDto, float qtyIncrease);
    void delete(long id);

    List<CartItem> findCartItemsByUserID(Long userID);
    List<CartItem> findCartItemsByItemID(Long itemID);

    List<CartItem> findCartItemsWithSameUserIDAndItemID(long userID, long itemID);

}
