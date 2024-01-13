package com.springboot.employee.config;

import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class EmployeeSecurityConfig {

	@Bean
	UserDetailsService userDetailsService(PasswordEncoder encoder) {
		UserDetails user1 = User.withUsername("user1").password(encoder.encode("123")).roles("USER").build();
		UserDetails user2 = User.withUsername("user2").password(encoder.encode("123")).roles("USER").build();
		UserDetails user3 = User.withUsername("user3").password(encoder.encode("123")).roles("USER").build();
		UserDetails user4 = User.withUsername("user4").password(encoder.encode("123")).roles("USER").build();
		return new InMemoryUserDetailsManager(user1, user2, user3);

	}

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.csrf(csrf -> csrf.disable());
		http.httpBasic(withDefaults());
		http.authorizeHttpRequests().antMatchers(HttpMethod.POST, "/emp/add/").authenticated().antMatchers("/emp/**")
				.authenticated();

		return http.build();
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
		return configuration.getAuthenticationManager();
	}
}
