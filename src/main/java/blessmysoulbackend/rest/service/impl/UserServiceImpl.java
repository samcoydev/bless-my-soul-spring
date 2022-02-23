package blessmysoulbackend.rest.service.impl;

import blessmysoulbackend.rest.helpers.RoleType;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import blessmysoulbackend.rest.dao.UserDao;
import blessmysoulbackend.rest.dto.UserDto;
import blessmysoulbackend.rest.model.User;
import blessmysoulbackend.rest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Transactional
@Service(value = "userService")
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @Autowired
    private BCryptPasswordEncoder bcryptEncoder;

    @Cacheable("users")
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
            log.info("[AUTHENTICATE] Uh oh! something when wrong");
            return null;
        }

        if(bcryptEncoder.matches(userLoggingIn.getPassword(), dbUser.getPassword())) {
            log.info("[AUTHENTICATE] Passwords match!");
            return dbUser;
        } else {
            log.info("[AUTHENTICATE] Passwords don't match!");
            return null;
        }
    }

    @Override
    public void delete(long id) {
        User user = userDao.findById(id).get();
        userDao.delete(user);
    }
}
