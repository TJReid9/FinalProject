package com.skilldistillery.watchparty.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.watchparty.entities.PartyRating;

public interface PartyRatingRepository extends JpaRepository<PartyRating, Integer> {
	
	PartyRating searchById(int partyRatingId);

}
