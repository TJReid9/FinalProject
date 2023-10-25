package com.skilldistillery.watchparty.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.watchparty.entities.PartyComment;

public interface PartyCommentRepository extends JpaRepository<PartyComment, Integer> {
	
	PartyComment searchById(int partyCommentId);

}
