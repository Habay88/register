package com.register.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.register.repositories.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

  private final UserRepository repository;

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    return repository.findByEmail(email)
        .orElseThrow(() -> new UsernameNotFoundException("No user was found with the specified email"));
  }
}
