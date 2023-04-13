package com.ms4.register.repositories;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ms4.register.models.Token;

public interface TokenRepository extends JpaRepository<Token, Integer> {

  Optional<Token> findByToken(String token);
}
