package com.omkar.expanseManagerApi.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.omkar.expanseManagerApi.entity.User;
import com.omkar.expanseManagerApi.entity.UserModel;
import com.omkar.expanseManagerApi.exception.ItemAlreadyExistsException;
import com.omkar.expanseManagerApi.exception.ResourceNotFoundException;
import com.omkar.expanseManagerApi.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PasswordEncoder bcryptEncoder;
	
	

	@Override
	public User createUser(UserModel uModel) {
		if (userRepository.existsByEmail(uModel.getEmail())) {
			throw new ItemAlreadyExistsException("User is already register with email:" + uModel.getEmail());
		}
		User newUser = new User();
		BeanUtils.copyProperties(uModel, newUser);
		newUser.setPassword(bcryptEncoder.encode(newUser.getPassword()));
		return userRepository.save(newUser);
	}

	@Override
	public User readUser() {
		return userRepository.findById(getLoggedInUser().getId())
				.orElseThrow(() -> new ResourceNotFoundException("User not found for the id:" + getLoggedInUser().getId()));
	}

	@Override
	public User updateUser(UserModel user) {
	//	Long id = userService.getLoggedInUser().getId();
		User existingUser = readUser();
		existingUser.setName(user.getName() != null ? user.getName() : existingUser.getName());
		existingUser.setEmail(user.getEmail() != null ? user.getEmail() : existingUser.getEmail());
		// existingUser.setPassword(user.getPassword() != null ? user.getPassword() :
		// existingUser.getPassword());
		existingUser.setPassword(
				user.getPassword() != null ? bcryptEncoder.encode(user.getPassword()) : existingUser.getPassword());
		existingUser.setAge(user.getAge() != null ? user.getAge() : existingUser.getAge());
		return userRepository.save(existingUser);
	}

	@Override
	public void deleteUser() {
		User existingUser = readUser();
		userRepository.delete(existingUser);
	}

	@Override
	public Page<User> getAllUser(Pageable page) {
		// TODO Auto-generated method stub

		return userRepository.findAll(page);
	}

	@Override
	public User getLoggedInUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		String email = authentication.getName();
		return userRepository.findByEmail(email)
				.orElseThrow(() -> new UsernameNotFoundException("User Not found for the email " + email));

	}

}
