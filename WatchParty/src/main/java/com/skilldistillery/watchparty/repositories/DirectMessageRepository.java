package com.skilldistillery.watchparty.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.watchparty.entities.DirectMessage;

public interface DirectMessageRepository extends JpaRepository<DirectMessage, Integer> {
	
	DirectMessage searchById(int directMessageId);

}
