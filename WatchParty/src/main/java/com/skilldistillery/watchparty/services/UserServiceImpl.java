package com.skilldistillery.watchparty.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.watchparty.entities.Address;
import com.skilldistillery.watchparty.entities.Friend;
import com.skilldistillery.watchparty.entities.Team;
import com.skilldistillery.watchparty.entities.User;
import com.skilldistillery.watchparty.repositories.AddressRepository;
import com.skilldistillery.watchparty.repositories.FriendRepository;
import com.skilldistillery.watchparty.repositories.TeamRepository;
import com.skilldistillery.watchparty.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private AddressRepository addressRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private TeamRepository teamRepo;
	
	@Autowired
	private FriendRepository friendRepo;

	@Override
	public List<User> getAllUsers() {
		return userRepo.findAll();
	}

	@Override
	public User retrieveUser(int UserId) {
		return userRepo.searchById(UserId);
	}
	
	@Override
	public User retrieveUser(String userName) {
		return userRepo.findByUsername(userName);
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
			existingUser.setFavoriteTeams(user.getFavoriteTeams());
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

	@Override
	public List<Team> addFavoriteTeamById(Team team, User user) {
		List<Team> favoriteTeams = new ArrayList<>();
		Team newTeam = teamRepo.searchById(team.getId());
		user = userRepo.findByUsername(user.getUsername());
		favoriteTeams.add(newTeam);
		user.setFavoriteTeams(favoriteTeams);
		userRepo.saveAndFlush(user);
		return favoriteTeams;
	}

	@Override
	public List<Team> removeFavoriteTeamById(Team team, User user) {
		Team oldTeam = teamRepo.searchById(team.getId());
		user = userRepo.findByUsername(user.getUsername());
		List<Team> favoriteTeams = user.getFavoriteTeams();
		favoriteTeams.remove(oldTeam);
		user.setFavoriteTeams(favoriteTeams);
		userRepo.saveAndFlush(user);
		return favoriteTeams;
	}

	@Override
	public List<Friend> addFriendById(Friend friend, User user) {
		List<Friend> friends = user.getFriends();
		Friend newFriend = friendRepo.searchById(user.getId());
		friends.add(newFriend);
		user.setFriends(friends);
		userRepo.saveAndFlush(user);
		return friends;
	}

	@Override
	public List<Friend> removeFriendById(Friend friend, User user) {
		Friend notFriend = friendRepo.searchById(user.getId());
		List<Friend> friends = user.getFriends();
		friends.remove(notFriend);
		user.setFriends(friends);
		userRepo.saveAndFlush(user);
		return friends;
	}

	

}
