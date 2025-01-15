package com.example.demo.services.gameuser.dao;

import com.example.demo.entities.GameUserEntity;
import com.example.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GameUserServiceImpl implements GameUserService {

    @Autowired
    UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public GameUserServiceImpl(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public List<GameUserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<GameUserEntity> getUserByName(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public GameUserEntity addUser(GameUserEntity gameUserEntity) {
        gameUserEntity.setPassword(passwordEncoder.encode(gameUserEntity.getPassword()));
        userRepository.save(gameUserEntity);
        return gameUserEntity;
    }

    @Override
    public GameUserEntity updateUser(GameUserEntity gameUserEntity) {
        return userRepository.save(gameUserEntity);
    }

    @Override
    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }

}
