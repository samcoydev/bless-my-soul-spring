package blessmysoulbackend.rest.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import blessmysoulbackend.rest.dto.UserDto;
import blessmysoulbackend.rest.model.User;
import blessmysoulbackend.rest.service.UserService;
import lombok.extern.slf4j.Slf4j;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("api/v1/user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAllUsers() {
        if(log.isDebugEnabled()) log.debug("[GET] All Users");
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public User getUserByID(@PathVariable Long id) {
        if(log.isDebugEnabled()) log.debug("[GET] User with ID: " + id);
        return userService.findById(id);
    }

    @PostMapping
    public User saveUser(@Valid @RequestBody UserDto user) {
        if(log.isDebugEnabled()) log.debug("[POST] New User");
        return userService.save(user);
    }

    @PostMapping(path = "/authenticate")
    public User authenticateUser(@Valid @RequestBody UserDto user) {
        if(log.isDebugEnabled()) log.debug("[POST] Authenticate User: " + user.getUsername());
        return userService.authenticate(user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        if(log.isDebugEnabled()) log.debug("[DELETE] User: " + id);
        userService.delete(id);
    }

}