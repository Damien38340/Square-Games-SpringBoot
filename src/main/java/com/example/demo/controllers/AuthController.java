package com.example.demo.controllers;

import com.example.demo.JwtUtil;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final JwtUtil jwtUtil = new JwtUtil();

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password) {
        if ("user".equals(username) && "user123".equals(password)) {
             return jwtUtil.generateToken(username);
        }
         throw new RuntimeException("Invalid credentials");
    }
}
