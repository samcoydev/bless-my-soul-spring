package blessmysoulbackend.rest.dao;

import blessmysoulbackend.rest.model.CartItem;
import blessmysoulbackend.rest.model.Item;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface CartDao extends CrudRepository<CartItem, Long> {

    Set<CartItem> findByOrderById();

    Optional<CartItem> findCartItemsByUserId(Long id);

}
