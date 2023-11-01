package com.skilldistillery.watchparty.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.watchparty.entities.Friend;
import com.skilldistillery.watchparty.entities.FriendId;
import com.skilldistillery.watchparty.entities.User;
import com.skilldistillery.watchparty.repositories.FriendRepository;
import com.skilldistillery.watchparty.repositories.FriendStatusRepository;
import com.skilldistillery.watchparty.repositories.UserRepository;

@Service
public class FriendServiceImpl implements FriendService {
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private FriendRepository friendRepo;
	
	@Autowired
	private FriendStatusRepository friendStatusRepo;

	@Override
	public List<Friend> getAllFriends() {
		return friendRepo.findAll();
	}

	@Override
	public Friend retrieveFriend(int friendId) {
		return friendRepo.searchById(friendId);
	}

	@Override
	public Friend create(Friend friend) {
		if(friend != null) {
			return friendRepo.saveAndFlush(friend);
		}
		return null;
	}

	@Override
	public Friend update(int friendId, Friend friend) {
		Friend editFriend = friendRepo.searchById(friendId);
		User user = userRepo.searchById(friendId);
		if(editFriend != null) {
			editFriend.setFriendStatus(friend.getFriendStatus());
			editFriend.setFriend(friend.getFriend());
			editFriend.setUser(user);
			friendRepo.saveAndFlush(editFriend);
		}
		return editFriend;
	}

	@Override
	public boolean delete(int friendId) {
		boolean deleted = false;
		Friend notFriend = friendRepo.searchById(friendId);
		if(notFriend != null) {
//			friendRepo.deleteById(notFriend.getId());
			deleted = true;
		}
		return deleted;
	}
	
	@Override
	public Friend addBuddy(int friendUserId, String username) {
		Friend friend = null;
		User user = userRepo.findByUsername(username);
		User otherUser= userRepo.searchById(friendUserId);
		if(user != null & otherUser != null) {
			friend = new Friend();
			FriendId id = new FriendId(friendUserId, user.getId());		
			friend.setId(id);
			friend.setUser(user);
			friend.setFriend(otherUser);
			friend.setFriendStatus(friendStatusRepo.searchById(2));
			friendRepo.saveAndFlush(friend);
		}
		return friend;
	}
	
//	public Friend removeBuddy(int friendUserName, String username) {
//		boolean deleted = false;
//		User user = userRepo.findByUsername(username);
//	}

	

}
