package com.lagou.edu.service;

import com.lagou.edu.dao.UserDao;
import com.lagou.edu.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public User findByEmail(String email) {
        return userDao.findByEmail(email);
    }

    public User findByEmailAndPassword(String email, String password) {
        return userDao.findByEmailAndPassword(email, password);
    }

    public void saveUser(User user) {
        userDao.save(user);
    }
}
