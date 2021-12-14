package blessmysoulbackend.rest.service.impl;

import blessmysoulbackend.rest.helpers.RoleType;
import org.apache.commons.lang3.StringUtils;
import blessmysoulbackend.rest.dao.UserDao;
import blessmysoulbackend.rest.dto.UserDto;
import blessmysoulbackend.rest.model.User;
import blessmysoulbackend.rest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Transactional
@Service(value = "userService")
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @Autowired
    private BCryptPasswordEncoder bcryptEncoder;

    public List<User> findAll() {
        List<User> list = new ArrayList<>();
        userDao.findByOrderById().iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public User findById(Long id) {
        return userDao.findById(id).get();
    }

    @Override
    public User findByEmail(String email) {
        return userDao.findByEmail(email).get();
    }

    public User save(UserDto user) {
        User newUser = new User();
        newUser.setEmail(user.getEmail());
        if (StringUtils.isNotEmpty(user.getPassword())) {
            newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
        }
        newUser.setFirstname(user.getFirstname());
        newUser.setLastname(user.getLastname());
        newUser.setRole(RoleType.USER);

        return userDao.save(newUser);
    }

    public User authenticate(UserDto user) {
        User userLoggingIn = new User();
        userLoggingIn.setEmail(user.getEmail());
        userLoggingIn.setPassword(user.getPassword());

        User dbUser = findByEmail(userLoggingIn.getEmail());
        if(dbUser == null) {
            System.out.println("[AUTHENTICATE] Uh oh! something when wrong");
            return null;
        }

        if(bcryptEncoder.matches(userLoggingIn.getPassword(), dbUser.getPassword())) {
            System.out.println("[AUTHENTICATE] Passwords match!");
            return dbUser;
        } else {
            System.out.println("[AUTHENTICATE] Passwords don't match!");
            return null;
        }
    }

    @Override
    public void delete(long id) {
        User user = userDao.findById(id).get();
        userDao.delete(user);
    }
}
