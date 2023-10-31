package com.skilldistillery.watchparty.services;

import java.util.List;

import com.skilldistillery.watchparty.entities.PartyComment;

public interface PartyCommentService {
	
	List<PartyComment> getAllPartyComments();
	PartyComment getPartyComment(int partyCommentId);
	PartyComment createPartyComment(PartyComment partyComment);
	PartyComment updatePartyComment(int partyCommentId, PartyComment partyComment);
	boolean deletePartyComment(int partyCommentId);
	List<PartyComment> findAllByParty(int partyId);

}
