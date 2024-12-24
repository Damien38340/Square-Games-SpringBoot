package com.example.demo.service.dao;

import java.util.List;

    public interface GameUserService {

        public List<GameUser> getAllUsers();
        public GameUser getUserById(int id);
        public void addUser(GameUser gameUser);
        public void updateUser(GameUser gameUser);
        public void deleteUser(int id);
    }

