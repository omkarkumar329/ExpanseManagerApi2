package com.omkar.expanseManagerApi.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.omkar.expanseManagerApi.entity.User;
import com.omkar.expanseManagerApi.entity.UserModel;
import com.omkar.expanseManagerApi.exception.ResourceNotFoundException;
import com.omkar.expanseManagerApi.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public User createUser(UserModel uModel) {
		User user = new User();
		BeanUtils.copyProperties(uModel, user);

		return userRepository.save(user);

	}

	@Override
	public User readUser(Long id) {
		return userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("User not found for the id:" + id));
	}

	@Override
	public User updateUser(UserModel user, Long id) {
		User existingUser = readUser(id);
		existingUser.setName(user.getName() != null ? user.getName() : existingUser.getName());
		existingUser.setEmail(user.getEmail() != null ? user.getEmail() : existingUser.getEmail());
		existingUser.setPassword(user.getPassword() != null ? user.getPassword() : existingUser.getPassword());
		existingUser.setAge(user.getAge() != null ? user.getAge() : existingUser.getAge());
		return userRepository.save(existingUser);
	}

	@Override
	public void deleteUser(Long id) {
		User existingUser = readUser(id);
		userRepository.delete(existingUser);
	}

	@Override
	public Page<User> getAllUser(Pageable page) {
		// TODO Auto-generated method stub
		
		return userRepository.findAll(page);
	}

}
