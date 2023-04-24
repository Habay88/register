package com.register.config;


import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.register.services.UserService;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	  private static final String[] WHITE_LIST_URLS = {
			  "/api/v*/authentication/**",
	            "/swagger-resources/**",
	            "/swagger-ui.html",
	            "/v2/api-docs",
	            "/webjars/**"
	    };
  private final UserService userService;

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userService)
        .passwordEncoder(passwordEncoder());
  }
//  @Override
//  protected void configure(HttpSecurity http) throws Exception {
//      http.authorizeRequests()
//          .antMatchers("/").hasAnyAuthority("USER", "CREATOR", "EDITOR", "ADMIN")
//          .antMatchers("/new").hasAnyAuthority("ADMIN", "CREATOR")
//          .antMatchers("/edit/**").hasAnyAuthority("ADMIN", "EDITOR")
//          .antMatchers("/delete/**").hasAuthority("ADMIN")
//          .anyRequest().authenticated()
//          .and()
//          .formLogin().permitAll()
//          .and()
//          .logout().permitAll()
//          .and()
//          .exceptionHandling().accessDeniedPage("/403")
//          ;
//  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.csrf().disable()
        .authorizeRequests()
        .antMatchers(WHITE_LIST_URLS)
        .permitAll()
        .anyRequest().authenticated()
        .and()
        .formLogin();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}
