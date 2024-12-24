package com.example.demo.service.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameUserServiceImpl implements GameUserService {

    @Autowired
    GameUserDAO gameUserDAO;


    @Override
    public List<GameUser> getAllUsers() {
        return gameUserDAO.getAllUsers();
    }

    @Override
    public GameUser getUserById(int id) {
        return gameUserDAO.getUserById(id);
    }

    @Override
    public void addUser(GameUser gameUser) {
        gameUserDAO.addUser(gameUser);
        getAllUsers().stream()
                .filter(u -> u.getEmail().equals(gameUser.getEmail()))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void updateUser(GameUser gameUser) {
        gameUserDAO.updateUser(gameUser);
        getAllUsers().stream()
                .filter(u -> u.getEmail().equals(gameUser.getEmail()))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void deleteUser(int id) {
        gameUserDAO.deleteUser(id);
        getAllUsers().stream()
                .filter(u -> u.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
}
