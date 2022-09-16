package com.omkar.expanseManagerApi.security;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.omkar.expanseManagerApi.entity.SecurityUser;
import com.omkar.expanseManagerApi.entity.User;
import com.omkar.expanseManagerApi.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
	 
		
		//System.out.println("Inside Load By User Name");
		//System.out.println(userRepository.findByEmail(email));
		
		User existingUser = userRepository.findByEmail(email)
		                     .orElseThrow(() -> new UsernameNotFoundException("User not found for the email:"+email));
		///System.out.println("User deails are:"+ existingUser);
		
		return new SecurityUser(existingUser);
	}

}
