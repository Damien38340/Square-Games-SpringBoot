package com.example.demo.services.gameuser.dao;

import com.example.demo.entities.GameUserEntity;

import java.util.List;
import java.util.Optional;

public interface GameUserService {

        public List<GameUserEntity> getAllUsers();
        Optional<GameUserEntity> getUserByName(String username);
        public GameUserEntity addUser(GameUserEntity gameUserEntity);
        public GameUserEntity updateUser(GameUserEntity gameUserEntity);
        public void deleteUser(int id);
    }

