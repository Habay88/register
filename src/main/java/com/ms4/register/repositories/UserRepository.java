package com.ms4.register.repositories;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ms4.register.models.ApplicationUser;

public interface UserRepository extends JpaRepository<ApplicationUser, Integer> {

  // SDP
  Optional<ApplicationUser> findByEmail(String email);
}
