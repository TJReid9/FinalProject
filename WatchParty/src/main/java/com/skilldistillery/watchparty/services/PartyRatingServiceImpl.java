package com.skilldistillery.watchparty.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.watchparty.entities.PartyRating;
import com.skilldistillery.watchparty.repositories.PartyRatingRepository;

@Service
public class PartyRatingServiceImpl implements PartyRatingService {
	
	@Autowired
	private PartyRatingRepository partyRatingRepo;
	
	@Override
	public List<PartyRating> getAllPartyRatings() {
		return partyRatingRepo.findAll();
	}

	@Override
	public PartyRating getPartyRating(int partyRatingId) {
		return partyRatingRepo.searchById(partyRatingId);
	}

	@Override
	public PartyRating createPartyRating(PartyRating partyRating) {
//		locationRepo.saveAndFlush(partyRating.getLocation());
		return partyRatingRepo.saveAndFlush(partyRating);
	}

	@Override
	public PartyRating updatePartyRating(int partyRatingId, PartyRating partyRating) {
		PartyRating dbPartyRating = partyRatingRepo.searchById(partyRatingId);
		System.out.println("***************" + dbPartyRating);
		if(dbPartyRating != null) {
			dbPartyRating.setRating(partyRating.getRating());
			dbPartyRating.setComment(partyRating.getComment());
			partyRatingRepo.saveAndFlush(dbPartyRating);
		}
		return dbPartyRating;
	}

	@Override
	public boolean deletePartyRating(int partyRatingId) {
		boolean deleted = false;
		PartyRating partyRatingToDelete = partyRatingRepo.searchById(partyRatingId);
		if(partyRatingToDelete != null) {
			partyRatingRepo.deleteById(partyRatingId);
			deleted = true;
		}
		return deleted;
	}

}
