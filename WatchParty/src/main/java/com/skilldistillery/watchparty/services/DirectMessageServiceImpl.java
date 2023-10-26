package com.skilldistillery.watchparty.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.watchparty.entities.DirectMessage;
import com.skilldistillery.watchparty.repositories.DirectMessageRepository;

@Service
public class DirectMessageServiceImpl implements DirectMessageService {
	
	@Autowired
	private DirectMessageRepository directMessageRepo;
	
	@Override
	public List<DirectMessage> getAllDirectMessages() {
		return directMessageRepo.findAll();
	}

	@Override
	public DirectMessage getDirectMessage(int directMessageId) {
		return directMessageRepo.searchById(directMessageId);
	}

	@Override
	public DirectMessage createDirectMessage(DirectMessage directMessage) {
//		locationRepo.saveAndFlush(directMessage.getLocation());
		return directMessageRepo.saveAndFlush(directMessage);
	}

	@Override
	public DirectMessage updateDirectMessage(int directMessageId, DirectMessage directMessage) {
		DirectMessage dbDirectMessage = directMessageRepo.searchById(directMessageId);
		System.out.println("***************" + dbDirectMessage);
		if(dbDirectMessage != null) {
			dbDirectMessage.setContent(directMessage.getContent());
			directMessageRepo.saveAndFlush(dbDirectMessage);
		}
		return dbDirectMessage;
	}

	@Override
	public boolean deleteDirectMessage(int directMessageId) {
		boolean deleted = false;
		DirectMessage directMessageToDelete = directMessageRepo.searchById(directMessageId);
		if(directMessageToDelete != null) {
			directMessageRepo.deleteById(directMessageToDelete.getId());
			deleted = true;
		}
		return deleted;
	}

}
