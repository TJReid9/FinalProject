package com.skilldistillery.watchparty.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.watchparty.entities.PartyComment;

public interface PartyCommentRepository extends JpaRepository<PartyComment, Integer> {
	
	PartyComment searchById(int partyCommentId);
	List<PartyComment> findAllByPartyId(int partyId);

}
