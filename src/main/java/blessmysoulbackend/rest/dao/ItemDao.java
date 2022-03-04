package blessmysoulbackend.rest.dao;

import blessmysoulbackend.rest.model.Item;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ItemDao extends CrudRepository<Item, Long> {

    List<Item> findByOrderById();

    List<Item> findByCategoryIdAndIsDeletedFalse(Long id);

    List<Item> findByIsDeletedFalse();

    @Query(value = "SELECT * FROM items item WHERE item.is_deleted = false ORDER BY item.id DESC LIMIT 4", nativeQuery = true)
    List<Item> findTop4ByOrderByIdDescAndIsDeletedFalse();

    @Query(value = "SELECT * FROM items item WHERE item.is_deleted = false AND item.is_featured = true", nativeQuery = true)
    List<Item> findByIsFeaturedTrueAndIsDeletedFalse();

    Optional<Item> findById(Long id);

}
