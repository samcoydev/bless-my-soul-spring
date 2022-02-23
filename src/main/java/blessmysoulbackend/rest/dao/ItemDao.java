package blessmysoulbackend.rest.dao;

import blessmysoulbackend.rest.model.Item;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ItemDao extends CrudRepository<Item, Long> {

    List<Item> findByOrderById();

    Optional<Item> findById(Long id);

}
