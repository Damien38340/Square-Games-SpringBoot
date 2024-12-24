package com.example.demo.controller;

import com.example.demo.DTO.GameCreationParams;
import com.example.demo.service.dao.GameUser;
import com.example.demo.service.dao.GameUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class GameUserController {

    @Autowired
    private GameUserService gameUserService;

    @GetMapping
    public ResponseEntity<List<GameUser>> getAllUsers() {
        return ResponseEntity.ok(gameUserService.getAllUsers());
    }

    @GetMapping("/{userId}")
    public ResponseEntity<GameUser> getUser(@PathVariable int userId) {
        return ResponseEntity.ok(gameUserService.getUserById(userId));
    }

    @PostMapping
    public ResponseEntity<String> addUser(@RequestBody GameUser gameUser) {
        gameUserService.addUser(gameUser);
        return ResponseEntity.ok("User added successfully");
    }

    @PutMapping
    public ResponseEntity<String> updateUser(@RequestBody GameUser gameUser) {
        gameUserService.updateUser(gameUser);
        return ResponseEntity.ok("User " + gameUser.getName() + "with ID " + gameUser.getId() + " updated successfully");
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable int userId) {
        gameUserService.deleteUser(userId);
        return ResponseEntity.ok("User with ID " + userId + " deleted successfully.");
    }
}
