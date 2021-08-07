package blessmysoulbackend.rest.service;

import blessmysoulbackend.rest.dto.CartDto;
import blessmysoulbackend.rest.model.CartItem;

import java.util.List;

public interface CartService {

    CartItem save(CartDto cartItem);

    List<CartItem> findCartItemsByUserId(Long id);

}
