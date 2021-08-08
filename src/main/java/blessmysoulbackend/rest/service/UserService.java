package blessmysoulbackend.rest.service;

import blessmysoulbackend.rest.dto.UserDto;
import blessmysoulbackend.rest.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    User save(UserDto user);
    User authenticate(UserDto user);
    void delete(long id);
    // TODO: Add Update

    List<User> findAll();

    User findById(Long id);
    User findByUsername(String username);

}