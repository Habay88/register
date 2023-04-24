package com.register.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.register.models.PasswordResetToken;

public interface passwordResetTokenRepository extends JpaRepository<PasswordResetToken, Long>{

	PasswordResetToken findByToken(String token);
}
