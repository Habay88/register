package com.register.repositories;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.register.models.Token;



public interface TokenRepository extends JpaRepository<Token, Integer> {

 // Token findByToken(String token);
  Optional<Token> findByToken(String token);
}
