package com.example.demo.service.dao;

import com.example.demo.service.User;

import java.util.List;

public class MySQLUserDAO implements UserDAO {

    @Override
    public List<User> getAllUsers() {
        return List.of();
    }

    @Override
    public User getUserById(int id) {
        return null;
    }

    @Override
    public void addUser(User user) {

    }

    @Override
    public void updateUser(User user) {

    }

    @Override
    public void deleteUser(int id) {

    }


}
