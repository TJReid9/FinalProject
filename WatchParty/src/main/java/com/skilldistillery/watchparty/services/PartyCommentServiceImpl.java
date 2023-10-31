package com.skilldistillery.watchparty.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.watchparty.entities.PartyComment;
import com.skilldistillery.watchparty.repositories.PartyCommentRepository;

@Service
public class PartyCommentServiceImpl implements PartyCommentService {
	
	@Autowired
	private PartyCommentRepository partyCommentRepo;
	
	@Override
	public List<PartyComment> getAllPartyComments() {
		return partyCommentRepo.findAll();
	}

	@Override
	public PartyComment getPartyComment(int partyCommentId) {
		return partyCommentRepo.searchById(partyCommentId);
	}

	@Override
	public PartyComment createPartyComment(PartyComment partyComment) {
//		locationRepo.saveAndFlush(partyComment.getLocation());
		return partyCommentRepo.saveAndFlush(partyComment);
	}

	@Override
	public PartyComment updatePartyComment(int partyCommentId, PartyComment partyComment) {
		PartyComment dbPartyComment = partyCommentRepo.searchById(partyCommentId);
		System.out.println("***************" + dbPartyComment);
		if(dbPartyComment != null) {
			dbPartyComment.setComment(partyComment.getComment());
			dbPartyComment.setPhotoUrl(partyComment.getPhotoUrl());
			dbPartyComment.setEnabled(partyComment.getEnabled());
			partyCommentRepo.saveAndFlush(dbPartyComment);
		}
		return dbPartyComment;
	}

	@Override
	public boolean deletePartyComment(int partyCommentId) {
		boolean deleted = false;
		PartyComment partyCommentToDelete = partyCommentRepo.searchById(partyCommentId);
		if(partyCommentToDelete != null) {
			partyCommentRepo.deleteById(partyCommentToDelete.getId());
			deleted = true;
		}
		return deleted;
	}

	@Override
	public List<PartyComment> findAllByParty(int partyId) {
		return partyCommentRepo.findAllByPartyId(partyId);
	}

}
