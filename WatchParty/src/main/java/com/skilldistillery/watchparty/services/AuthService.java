package com.skilldistillery.watchparty.services;

import com.skilldistillery.watchparty.entities.User;

public interface AuthService {
	public User register(User user);
	public User getUserByUsername(String username);

}
