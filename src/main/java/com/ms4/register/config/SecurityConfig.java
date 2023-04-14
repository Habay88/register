package com.ms4.register.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.ms4.register.services.UserService;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig {

  private final UserService userService;
  
  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
http.csrf()
.disable()
.authorizeRequests((request)->{
	try {
		request.antMatchers("/api/v*/authentication/**")
		.permitAll()
		.anyRequest().authenticated()
		.and()
		.formLogin();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
})
.authenticationProvider(authenticationProvider())
;


return http.build();

	
	 
  }
@Bean
public AuthenticationProvider authenticationProvider() {
	
	DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
	authenticationProvider.setUserDetailsService(userService);
	authenticationProvider.setPasswordEncoder(passwordEncoder());
	return authenticationProvider;
	
}
@Bean
public PasswordEncoder passwordEncoder() {
  return new BCryptPasswordEncoder();
}
//  @Override
//  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//    auth.userDetailsService(userService)
//        .passwordEncoder(passwordEncoder());
//  }

//  @Override
//  protected void configure(HttpSecurity http) throws Exception {
//    http.csrf().disable()
//        .authorizeRequests()
//        .antMatchers("/api/v*/authentication/**")
//        .permitAll()
//        .anyRequest().authenticated()
//        .and()
//        .formLogin();
//  }


}
