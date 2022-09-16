package com.omkar.expanseManagerApi.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.omkar.expanseManagerApi.entity.User;
import com.omkar.expanseManagerApi.entity.UserModel;

public interface UserService {
	
     User createUser(UserModel user);
     User readUser();
	User updateUser(UserModel user);
	void deleteUser();
	Page<User> getAllUser(Pageable page);
     User getLoggedInUser();
}
