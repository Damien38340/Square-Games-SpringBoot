package com.example.demo.controllers;

import com.example.demo.dto.GameUserDTO;
import com.example.demo.entities.GameUserEntity;
import com.example.demo.services.gameuser.dao.GameUserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class GameUserController {

    @Autowired
    private GameUserService gameUserService;

    @GetMapping
    public ResponseEntity<List<GameUserEntity>> getAllUsers() {
        return ResponseEntity.ok(gameUserService.getAllUsers());
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Optional<GameUserEntity>> getUser(@PathVariable int userId) {
        return ResponseEntity.ok(gameUserService.getUserById(userId));
    }

    @PostMapping
    public ResponseEntity<String> addUser(@RequestBody @Valid GameUserDTO gameUser) {
        GameUserEntity toAdd = new GameUserEntity();
        toAdd.setEmail(gameUser.email());
        toAdd.setUsername(gameUser.name());
        gameUserService.addUser(toAdd);
        return ResponseEntity.ok("User added successfully");
    }

    @PutMapping
    public ResponseEntity<String> updateUser(@RequestBody @Valid GameUserEntity gameUserEntity) {
        gameUserService.updateUser(gameUserEntity);
        return ResponseEntity.ok("User " + gameUserEntity.getUsername() + " with ID " + gameUserEntity.getId() + " updated successfully");
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable @Valid int userId) {
        gameUserService.deleteUser(userId);
        return ResponseEntity.ok("User with ID " + userId + " deleted successfully.");
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

}
