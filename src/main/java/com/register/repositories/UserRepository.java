package com.register.repositories;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import com.register.models.ApplicationUser;

public interface UserRepository extends JpaRepository<ApplicationUser, Integer> {

  // SDP
//  ApplicationUser findByEmail(String email);
  Optional<ApplicationUser> findByEmail(String email);
}
