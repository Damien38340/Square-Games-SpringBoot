package com.example.demo.services;

import com.example.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .map(userEntity -> User.withUsername(userEntity.getUsername())
                        .password(userEntity.getPassword())
                        .roles(userEntity.getRole())
                        .build()
                )
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}

//@Autowired
//private CustomUserDetailsService customUserDetailsService;
//@Bean
//public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
//    return http
//            .getSharedObject(AuthenticationManagerBuilder.class)
//            .userDetailsService(customUserDetailsService)
//            .and()
//            .build();
//}
