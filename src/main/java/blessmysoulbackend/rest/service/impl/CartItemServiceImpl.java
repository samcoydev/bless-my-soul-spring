package blessmysoulbackend.rest.service.impl;

import blessmysoulbackend.rest.dao.CartItemDao;
import blessmysoulbackend.rest.dto.CartItemDto;
import blessmysoulbackend.rest.model.CartItem;
import blessmysoulbackend.rest.service.CartItemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Transactional
@Service(value = "cartService")
public class CartItemServiceImpl implements CartItemService {

    @Autowired
    CartItemDao cartItemDao;

    @Override
    public List<CartItem> findCartItemsByUserID(Long userID) {
        List<CartItem> cartItemList = new ArrayList<>();
        cartItemDao.findByOrderById().iterator().forEachRemaining(cartItem -> {
            if (cartItem.getUser().getId() == userID && !cartItem.isAttachedToOrder())
                cartItemList.add(cartItem);
        });
        return cartItemList;
    }

    @Override
    public List<CartItem> findCartItemsByItemID(Long itemID) {
        List<CartItem> cartItemList = new ArrayList<>();
        cartItemDao.findByOrderById().iterator().forEachRemaining(cartItem -> {
            if (cartItem.getItem().getId() == itemID && !cartItem.isAttachedToOrder())
                cartItemList.add(cartItem);
        });
        return cartItemList;
    }

    public List<CartItem> findCartItemsWithSameUserIDAndItemID(long userID, long itemID) {
        List<CartItem> itemIDFilteredList = findCartItemsByItemID(itemID);
        itemIDFilteredList.retainAll(findCartItemsByUserID(userID));
        return new ArrayList<>(itemIDFilteredList);
    }

    public CartItem save(CartItemDto cartItem) {
        CartItem newCartItem = new CartItem();
        newCartItem.setItem(cartItem.getItem());
        newCartItem.setUser(cartItem.getUser());
        newCartItem.setQty(cartItem.getQty());

        cartItemDao.save(newCartItem);

        return cartItemDao.save(newCartItem);
    }

    public CartItem updateQty(long id, float qtyIncrease) {
        Optional<CartItem> optionalCartItem = cartItemDao.findById(id);
        if (optionalCartItem.isPresent()) {
            CartItem existingCartItem = optionalCartItem.get();
            existingCartItem.setQty(qtyIncrease);

            return cartItemDao.save(existingCartItem);
        } else {
            return null;
        }
    }

    public CartItem attachToOrder(long id) {
        Optional<CartItem> optionalCartItem = cartItemDao.findById(id);
        if (optionalCartItem.isPresent()) {
            CartItem existingCartItem = optionalCartItem.get();
            existingCartItem.setAttachedToOrder(true);

            return cartItemDao.save(existingCartItem);
        } else {
            return null;
        }
    }

    @Override
    public void delete(long id) {
        CartItem cartItem = cartItemDao.findById(id).get();
        cartItemDao.delete(cartItem);
    }

}
