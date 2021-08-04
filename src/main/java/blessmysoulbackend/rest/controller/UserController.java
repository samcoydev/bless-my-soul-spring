package blessmysoulbackend.rest.controller;

import blessmysoulbackend.rest.dto.UserDto;
import blessmysoulbackend.rest.model.User;
import blessmysoulbackend.rest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Table;
import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("api/v1/user")
@Table(name = "bms_users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAllUsers() {
        System.out.println("[GET] All Users");
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public User getUserByID(@PathVariable Long id) {
        System.out.println("[GET] User with ID: " + id);
        return userService.findById(id);
    }

    @PostMapping
    public User saveUser(@Valid @RequestBody UserDto user) {
        System.out.println("[POST] New User");
        return userService.save(user);
    }

    @PostMapping(path = "/authenticate")
    public User authenticateUser(@Valid @RequestBody UserDto user) {
        System.out.println("[POST] Authenticate User: " + user.getUsername());
        return userService.authenticate(user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        System.out.println("[DELETE] User: " + id);
        userService.delete(id);
    }

}