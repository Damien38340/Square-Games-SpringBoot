package com.example.demo.controllers;

import com.example.demo.dto.TokenDTO;
import com.example.demo.dto.UserCredentialsDTO;
import com.example.demo.entities.GameUserEntity;
import com.example.demo.services.gameuser.dao.GameUserService;
import com.example.demo.util.JwtUtil;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;
    private JwtUtil jwtUtil = new JwtUtil();

    @Autowired
    private GameUserService gameUserService;

    @PostMapping("/signup")
    public String signup(@RequestBody GameUserEntity gameUserEntity) {
        gameUserService.addUser(gameUserEntity);
        return "Success";
    }

    @PostMapping("/login")
    public TokenDTO login(@RequestBody GameUserEntity gameUserEntity, HttpServletResponse response) {
        final Authentication authenticate = authenticationManager
                .authenticate(
                        new UsernamePasswordAuthenticationToken(
                                gameUserEntity.getUsername(), gameUserEntity.getPassword()
                        )
                );
        String token = jwtUtil.generateToken(gameUserEntity.getUsername());

        GameUserEntity username = gameUserService.getUserByName(gameUserEntity.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));
        username.setToken(token);  // Store the token
        gameUserService.addUser(username);

        response.setHeader("Authorization", "Bearer " + token);

        return new TokenDTO(token, gameUserEntity.getUsername());
    }
}

