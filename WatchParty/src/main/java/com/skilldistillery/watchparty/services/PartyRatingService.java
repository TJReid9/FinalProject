package com.skilldistillery.watchparty.services;

import java.util.List;

import com.skilldistillery.watchparty.entities.PartyRating;

public interface PartyRatingService {
	
	List<PartyRating> getAllPartyRatings();
	PartyRating getPartyRating(int partyRatingId);
	PartyRating createPartyRating(PartyRating partyRating);
	PartyRating updatePartyRating(int partyRatingId, PartyRating partyRating);
	boolean deletePartyRating(int partyRatingId);

}
