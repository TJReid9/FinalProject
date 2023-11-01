package com.skilldistillery.watchparty.services;

import java.util.List;

import com.skilldistillery.watchparty.entities.Friend;
import com.skilldistillery.watchparty.entities.Team;
import com.skilldistillery.watchparty.entities.User;

public interface UserService {
	public List<User> getAllUsers();

    public User retrieveUser(int UserId);
    
    public User retrieveUser(String userName);

    public User create(User user);

    public User update(int userId, User user);

    public boolean delete(int userId);
    
    public List<Team> addFavoriteTeamById(Team team, User user);
    
    public List<Team> removeFavoriteTeamById(Team team, User user);
    
    public List<Friend> addFriendById(Friend friend, User user);
    
    public List<Friend> removeFriendById(Friend friend, User user);
}
