package blessmysoulbackend.rest.dao;

import blessmysoulbackend.rest.model.Image;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface ImageDao extends CrudRepository<Image, Long> {

    Set<Image> findByOrderById();

    Optional<Image> findById(Long id);
}
