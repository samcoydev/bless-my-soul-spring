package blessmysoulbackend.rest.controller;

import blessmysoulbackend.rest.dto.UserDto;
import blessmysoulbackend.rest.model.User;
import blessmysoulbackend.rest.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("api/v1/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAllUsers() {
        log.info("[GET] All Users");
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public User getUserByID(@PathVariable Long id) {
        log.info("[GET] User with ID: " + id);
        return userService.findById(id);
    }

    @PostMapping
    public User saveUser(@Valid @RequestBody UserDto user) {
        log.info("[POST] New User");
        return userService.save(user);
    }

    @PostMapping(path = "/authenticate")
    public User authenticateUser(@Valid @RequestBody UserDto user) {
        log.info("[POST] Authenticate User: " + user.getEmail());
        return userService.authenticate(user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        log.info("[DELETE] User: " + id);
        userService.delete(id);
    }

}