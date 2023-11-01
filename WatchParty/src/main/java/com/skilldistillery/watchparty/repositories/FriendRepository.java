package com.skilldistillery.watchparty.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.watchparty.entities.Friend;
import com.skilldistillery.watchparty.entities.FriendId;

public interface FriendRepository extends JpaRepository<Friend, FriendId>{
	
	Friend searchById(int friendId);

}
