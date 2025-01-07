package com.example.demo.controller;

import com.example.demo.dto.GameUserDTO;
import com.example.demo.service.dao.GameUser;
import com.example.demo.service.dao.GameUserDAO;
import com.example.demo.service.dao.GameUserService;
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
    public ResponseEntity<List<GameUser>> getAllUsers() {
        return ResponseEntity.ok(gameUserService.getAllUsers());
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Optional<GameUser>> getUser(@PathVariable int userId) {
        return ResponseEntity.ok(gameUserService.getUserById(userId));
    }

    @PostMapping
    public ResponseEntity<String> addUser(@RequestBody @Valid GameUserDTO gameUser) {
        GameUser toAdd = new GameUser();
        toAdd.setEmail(gameUser.email());
        toAdd.setName(gameUser.name());
        gameUserService.addUser(toAdd);
        return ResponseEntity.ok("User added successfully");
    }

    @PutMapping
    public ResponseEntity<String> updateUser(@RequestBody @Valid GameUser gameUser) {
        gameUserService.updateUser(gameUser);
        return ResponseEntity.ok("User " + gameUser.getName() + " with ID " + gameUser.getId() + " updated successfully");
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
