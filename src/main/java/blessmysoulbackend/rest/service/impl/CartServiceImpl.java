package blessmysoulbackend.rest.service.impl;

import blessmysoulbackend.rest.dao.CartDao;
import blessmysoulbackend.rest.dto.CartDto;
import blessmysoulbackend.rest.dto.ItemDto;
import blessmysoulbackend.rest.model.CartItem;
import blessmysoulbackend.rest.model.Item;
import blessmysoulbackend.rest.model.Order;
import blessmysoulbackend.rest.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service(value = "cartService")
public class CartServiceImpl implements CartService {

    @Autowired
    CartDao cartDao;

    @Override
    public List<CartItem> findCartItemsByUserID(Long userID) {
        List<CartItem> cartItemList = new ArrayList<>();
        cartDao.findByOrderById().iterator().forEachRemaining(cartItem -> {
            if (cartItem.getUser().getId() == userID)
                cartItemList.add(cartItem);
        });
        return cartItemList;
    }

    @Override
    public List<CartItem> findCartItemsByItemID(Long itemID) {
        List<CartItem> cartItemList = new ArrayList<>();
        cartDao.findByOrderById().iterator().forEachRemaining(cartItem -> {
            if (cartItem.getItem().getId() == itemID)
                cartItemList.add(cartItem);
        });
        return cartItemList;
    }

    public List<CartItem> findCartItemsWithSameUserIDAndItemID(long userID, long itemID) {
        List<CartItem> itemIDFilteredList = findCartItemsByItemID(itemID);
        itemIDFilteredList.retainAll(findCartItemsByUserID(userID));
        return new ArrayList<>(itemIDFilteredList);
    }

    public CartItem save(CartDto cartItem) {
        CartItem newCartItem = new CartItem();
        newCartItem.setItem(cartItem.getItem());
        newCartItem.setUser(cartItem.getUser());
        newCartItem.setQty(cartItem.getQty());

        cartDao.save(newCartItem);

        return cartDao.save(newCartItem);
    }

    public CartItem updateQty(long id, float qtyIncrease) {
        Optional<CartItem> optionalCartItem = cartDao.findById(id);
        if (optionalCartItem.isPresent()) {
            CartItem existingCartItem = optionalCartItem.get();
            existingCartItem.setQty(qtyIncrease);

            return cartDao.save(existingCartItem);
        } else {
            return null;
        }
    }

    @Override
    public void delete(long id) {
        CartItem cartItem = cartDao.findById(id).get();
        cartDao.delete(cartItem);
    }

}
