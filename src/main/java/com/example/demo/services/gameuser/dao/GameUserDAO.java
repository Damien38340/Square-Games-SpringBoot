package com.example.demo.services.gameuser.dao;

import com.example.demo.entities.GameUserEntity;

import java.util.List;

public interface GameUserDAO {

    public List<GameUserEntity> getAllUsers();
    public GameUserEntity getUserById(int id);
    public void addUser(GameUserEntity gameUserEntity);
    public void updateUser(GameUserEntity gameUserEntity);
    public void deleteUser(int id);
}