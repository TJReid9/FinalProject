package com.skilldistillery.watchparty.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.watchparty.entities.DirectMessage;
import com.skilldistillery.watchparty.entities.User;

public interface DirectMessageRepository extends JpaRepository<DirectMessage, Integer> {
	
	DirectMessage searchById(int directMessageId);
	List<DirectMessage> findAllBySenderIdAndRecipientId(int senderId, int recipientId);
}
