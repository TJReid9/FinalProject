package com.skilldistillery.watchparty.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.watchparty.entities.FriendStatus;

public interface FriendStatusRepository extends JpaRepository<FriendStatus, Integer>{
	
	FriendStatus searchById(int id);

}
