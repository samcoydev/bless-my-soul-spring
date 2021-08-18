package blessmysoulbackend.rest.service;

import blessmysoulbackend.rest.dto.CartItemDto;
import blessmysoulbackend.rest.model.CartItem;

import java.util.List;

public interface CartItemService {

    CartItem save(CartItemDto cartItem);
    CartItem updateQty(long id, float qtyIncrease);
    CartItem attachToOrder(long id);
    void delete(long id);

    List<CartItem> findCartItemsByUserID(Long userID);
    List<CartItem> findCartItemsByItemID(Long itemID);

    List<CartItem> findCartItemsWithSameUserIDAndItemID(long userID, long itemID);

}
