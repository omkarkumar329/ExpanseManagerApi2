package com.omkar.expanseManagerApi.config;

import javax.servlet.Filter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;

import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
public class WebSecurityconfiguration {

	
	
	@Bean
	public SecurityFilterChain webSecurityFilterChain(HttpSecurity http) throws Exception {
		System.out.println("Inside Security filter chain");

		http.csrf().disable().authorizeRequests().antMatchers("/login", "/register").permitAll().anyRequest()
				.authenticated()
				.and()
				.httpBasic();
	//	http.authenticationProvider(daoAuthenticationProvider());
		return http.build();
	}

	
	@Bean
	public PasswordEncoder passwordEncoder() {
		System.out.println("Inside Password Encoder");
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationManager authenticationManagerBean(AuthenticationConfiguration cofiguration) throws Exception {
		return cofiguration.getAuthenticationManager();

	}
//	public DaoAuthenticationProvider daoAuthenticationProvider()
//	{
//		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
//		provider.setUserDetailsService(this.userDetailService);
//		provider.setPasswordEncoder(passwordEncoder());
//		return provider;
//	}
	
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.userDetailsService(userDetailsService);
//	}
}
