package blessmysoulbackend.rest.dao;

import blessmysoulbackend.rest.model.Item;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface ItemDao extends CrudRepository<Item, Long> {

    Set<Item> findByOrderById();

    Optional<Item> findById(Long id);

}
