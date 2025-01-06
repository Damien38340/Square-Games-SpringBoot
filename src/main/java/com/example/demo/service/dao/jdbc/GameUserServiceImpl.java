package com.example.demo.service.dao.jdbc;

import com.example.demo.dto.GameUserDTO;
import com.example.demo.service.dao.GameUser;
import com.example.demo.service.dao.GameUserDAO;
import com.example.demo.service.dao.GameUserService;
import com.example.demo.service.dao.jpa.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GameUserServiceImpl implements GameUserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public List<GameUser> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<GameUser> getUserById(int id) {
        return userRepository.findById(id);
    }

    @Override
    public GameUser addUser(GameUser gameUser) {
        return userRepository.save(gameUser);
    }

    @Override
    public GameUser updateUser(GameUser gameUser) {
        return userRepository.save(gameUser);
    }

    @Override
    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }
}
