package blessmysoulbackend.rest.service.impl;

import blessmysoulbackend.rest.dao.CartDao;
import blessmysoulbackend.rest.dto.CartDto;
import blessmysoulbackend.rest.model.CartItem;
import blessmysoulbackend.rest.model.Item;
import blessmysoulbackend.rest.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service(value = "cartService")
public class CartServiceImpl implements CartService {

    @Autowired
    CartDao cartDao;

    @Override
    public List<CartItem> findCartItemsByUserId(Long userId) {
        List<CartItem> cartItemList = new ArrayList<>();

        cartDao.findByOrderById().iterator().forEachRemaining(cartItem -> {
            if (cartItem.getUserID() == userId)
                cartItemList.add(cartItem);
        });

        return cartItemList;
    }

    public CartItem save(CartDto cartItem) {
        CartItem newCartItem = new CartItem();
        newCartItem.setName(cartItem.getName());
        newCartItem.setItemID(cartItem.getItemID());
        newCartItem.setUserID(cartItem.getUserID());
        newCartItem.setQty(cartItem.getQty());

        cartDao.save(newCartItem);

        return cartDao.save(newCartItem);
    }

}
