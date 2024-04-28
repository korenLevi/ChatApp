package com.hit.service;

import com.hit.dao.Dao;
import com.hit.dao.UserDao;
import com.hit.dm.User;

import java.util.ArrayList;
import java.util.List;

public class UserService {
    private final Dao<User> userDao;

    public UserService(String fileName) {
        this.userDao = new UserDao(fileName);
    }

    public User createUser(String username, String fullName, String password, String email, String gender, String phoneNo) {
        User user = new User();
        user.username = username;
        user.fullName = fullName;
        user.password = password;
        user.email = email;
        user.gender = gender;
        user.phoneNo = phoneNo;
        userDao.save(user);
        return user;
    }

    public User getUser(Long id) {
        return userDao.get(id);
    }

    public ArrayList<User> getAllUsers() {
        return userDao.getAll();
    }

    public void updateUser(User user, String username, String fullName, String password, String email, String gender, String phoneNo) {
        String[] params = {username, fullName, password, email, gender, phoneNo};
        userDao.update(user, params);
    }

    public void deleteUser(User user) {
        userDao.delete(user);
    }

    public void  createUserFromString(String userString) {
        String[] parts = userString.split(",");
        User user = new User();
        user.setId(Long.parseLong(parts[0]));
        user.username = parts[1];
        user.fullName = parts[2];
        user.password = parts[3];
        user.email = parts[4];
        user.gender = parts[5];
        user.phoneNo = parts[6];
        userDao.save(user);
    }
}
