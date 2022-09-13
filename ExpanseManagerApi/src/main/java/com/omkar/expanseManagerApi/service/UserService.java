package com.omkar.expanseManagerApi.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.omkar.expanseManagerApi.entity.User;
import com.omkar.expanseManagerApi.entity.UserModel;

public interface UserService {
	
     User createUser(UserModel user);
     User readUser(Long id);
	User updateUser(UserModel user, Long id);
	void deleteUser(Long id);
	Page<User> getAllUser(Pageable page);

}
