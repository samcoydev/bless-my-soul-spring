package blessmysoulbackend.rest.dao;

import blessmysoulbackend.rest.model.CartItem;
import blessmysoulbackend.rest.model.Item;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface CartItemDao extends CrudRepository<CartItem, Long> {

    Set<CartItem> findByOrderById();

    List<CartItem> findByItem(Item item);

}
