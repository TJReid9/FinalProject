package com.skilldistillery.watchparty.services;

import java.util.List;

import com.skilldistillery.watchparty.entities.User;

public interface UserService {
	public List<User> getAllUsers();

    public User retrieveUser(int UserId);

    public User create(User user);

    public User update(int userId, User user);

    public boolean delete(int userId);
}
