package com.example.demo.service.dao.jpa;

import com.example.demo.service.dao.GameUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
public interface UserRepository extends JpaRepository<GameUser, Integer> {
}
