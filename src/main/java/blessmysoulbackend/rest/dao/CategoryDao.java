package blessmysoulbackend.rest.dao;

import blessmysoulbackend.rest.model.Category;
import blessmysoulbackend.rest.model.Item;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface CategoryDao extends CrudRepository<Category, Long> {

    Set<Category> findByOrderById();

    Optional<Category> findById(Long id);

}