package com.example.demo.repositories;

import com.example.demo.entities.GameUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
@Repository
public interface UserRepository extends JpaRepository<GameUserEntity, Integer> {
}
