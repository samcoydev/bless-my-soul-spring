package blessmysoulbackend.rest.dao;

import blessmysoulbackend.rest.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface UserDao extends CrudRepository<User, Long> {

    Set<User> findByOrderById();

    Optional<User> findById(Long id);
    Optional<User> findByUsername(String username);

}
