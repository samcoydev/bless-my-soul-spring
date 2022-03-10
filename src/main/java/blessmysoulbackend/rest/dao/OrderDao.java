package blessmysoulbackend.rest.dao;

import blessmysoulbackend.rest.model.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderDao extends CrudRepository<Order, Long> {

    List<Order> findByOrderById();
    List<Order> findByUserId(Long id);

    Optional<Order> findById(Long id);
}
