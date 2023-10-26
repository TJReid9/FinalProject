package com.skilldistillery.watchparty.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.watchparty.entities.Address;
import com.skilldistillery.watchparty.entities.User;
import com.skilldistillery.watchparty.repositories.AddressRepository;
import com.skilldistillery.watchparty.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private AddressRepository addressRepo;
	
	@Autowired
	private UserRepository userRepo;

	@Override
	public List<User> getAllUsers() {
		return userRepo.findAll();
	}

	@Override
	public User retrieveUser(int UserId) {
		return userRepo.searchById(UserId);
	}

	@Override
	public User create(User user) {
		if(user != null) {
			Address address = addressRepo.searchById(1);
			user.setAddress(address);
			return userRepo.saveAndFlush(user);
		}
		return null;
	}

	@Override
	public User update(int userId, User user) {
		User existingUser = userRepo.searchById(userId);
		if(existingUser != null) {
			existingUser.setUsername(user.getUsername());
			existingUser.setPassword(user.getPassword());
			existingUser.setEmail(user.getEmail());
			existingUser.setFirstName(user.getFirstName());
			existingUser.setPhotoUrl(user.getPhotoUrl());
			existingUser.setEnabled(user.isEnabled());
			existingUser.setAddress(user.getAddress());
			existingUser.setFriends(user.getFriends());
			userRepo.saveAndFlush(existingUser);
			
		}
		return existingUser;
	}

	@Override
	public boolean delete(int userId) {
		boolean deleted = false;
		if(userRepo.existsById(userId)) {
			userRepo.deleteById(userId);
			deleted = true;
		}
		return deleted;
	}

	

}
