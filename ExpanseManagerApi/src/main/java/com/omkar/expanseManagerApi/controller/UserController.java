package com.omkar.expanseManagerApi.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.omkar.expanseManagerApi.entity.User;
import com.omkar.expanseManagerApi.entity.UserModel;
import com.omkar.expanseManagerApi.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/register")
	public ResponseEntity<User> Save(@Valid @RequestBody UserModel user) {

		return new ResponseEntity<User>(userService.createUser(user), HttpStatus.CREATED);
	}

	@GetMapping("/users/{id}")
	public ResponseEntity<User> readUser(@PathVariable Long id) {
		return new ResponseEntity<User>(userService.readUser(id), HttpStatus.OK);
	}
	
	@GetMapping("/users")
	public List<User> getAllUser(Pageable page) {
		return userService.getAllUser(page).toList();
	}

	@PutMapping("/users/{id}")
	public ResponseEntity<User> updateUser(@RequestBody UserModel user, @PathVariable Long id) {
		return new ResponseEntity<User>(userService.updateUser(user, id), HttpStatus.OK);
	}

	@DeleteMapping("/users/{id}")
	public ResponseEntity<HttpStatus> deleteUser(@PathVariable Long id) {
		userService.deleteUser(id);
		return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
	}

}
