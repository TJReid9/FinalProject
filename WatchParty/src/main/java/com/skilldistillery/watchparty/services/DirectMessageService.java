package com.skilldistillery.watchparty.services;

import java.util.List;

import com.skilldistillery.watchparty.entities.DirectMessage;

public interface DirectMessageService {
	
	List<DirectMessage> getAllDirectMessages();
	DirectMessage getDirectMessage(int directMessageId);
	DirectMessage createDirectMessage(DirectMessage directMessage);
	DirectMessage updateDirectMessage(int directMessageId, DirectMessage directMessage);
	boolean deleteDirectMessage(int directMessageId);

}
