package com.example.demo.service.dao;

import com.example.demo.dto.GameUserDTO;

import java.util.List;
import java.util.Optional;

public interface GameUserService {

        public List<GameUser> getAllUsers();
        public Optional<GameUser> getUserById(int id);
        public GameUser addUser(GameUser gameUser);
        public GameUser updateUser(GameUser gameUser);
        public void deleteUser(int id);
    }

