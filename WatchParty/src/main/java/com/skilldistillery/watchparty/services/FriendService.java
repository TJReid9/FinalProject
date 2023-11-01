package com.skilldistillery.watchparty.services;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.watchparty.entities.Friend;

public interface FriendService {
	
	public List<Friend> getAllFriends();

    public Friend retrieveFriend(int friendId);

    public Friend create(Friend friend);

    public Friend update(int friendId, Friend friend);

    public boolean delete(int friendId);

	Friend addBuddy(int friendUserId, String username);

}
