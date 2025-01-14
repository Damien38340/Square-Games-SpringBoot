package com.example.demo.controllers;

import com.example.demo.dto.TokenDTO;
import com.example.demo.dto.UserCredentialsDTO;
import com.example.demo.entities.GameUserEntity;
import com.example.demo.util.JwtUtil;
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

    @PostMapping("/signup")
    public String signup(@RequestBody UserCredentialsDTO userCredentialsDto) {
        GameUserEntity gameUserEntity = new GameUserEntity();
        gameUserEntity.setUsername(userCredentialsDto.username());
        gameUserEntity.setPassword(userCredentialsDto.password());
        gameUserEntity.setRole(userCredentialsDto.role());
        return "Success";
    }

    @PostMapping("/login")
    public TokenDTO login(@RequestBody UserCredentialsDTO userCredentialsDto) {
        final Authentication authenticate = authenticationManager
                .authenticate(
                        new UsernamePasswordAuthenticationToken(
                                userCredentialsDto.username(), userCredentialsDto.password()
                        )
                );
        String token = jwtUtil.generateToken(userCredentialsDto.username());
        return new TokenDTO(token, userCredentialsDto.username());
    }
}

