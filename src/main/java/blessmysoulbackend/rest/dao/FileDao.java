package blessmysoulbackend.rest.dao;

import blessmysoulbackend.rest.model.File;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FileDao extends CrudRepository<File, Long> {

    List<File> findByOrderById();

    Optional<File> findById(Long id);
}
