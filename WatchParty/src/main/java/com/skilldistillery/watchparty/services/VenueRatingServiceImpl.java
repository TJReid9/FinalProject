package com.skilldistillery.watchparty.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.watchparty.entities.VenueRating;
import com.skilldistillery.watchparty.repositories.VenueRatingRepository;

@Service
public class VenueRatingServiceImpl implements VenueRatingService {
	
	@Autowired
	private VenueRatingRepository venueRatingRepo;
	
	@Override
	public List<VenueRating> getAllVenueRatings() {
		return venueRatingRepo.findAll();
	}

	@Override
	public VenueRating getVenueRating(int venueRatingId) {
		return venueRatingRepo.searchById(venueRatingId);
	}

	@Override
	public VenueRating createVenueRating(VenueRating venueRating) {
//		locationRepo.saveAndFlush(venueRating.getLocation());
		return venueRatingRepo.saveAndFlush(venueRating);
	}

	@Override
	public VenueRating updateVenueRating(int venueRatingId, VenueRating venueRating) {
		VenueRating dbVenueRating = venueRatingRepo.searchById(venueRatingId);
		System.out.println("***************" + dbVenueRating);
		if(dbVenueRating != null) {
			dbVenueRating.setRating(venueRating.getRating());
			dbVenueRating.setComment(venueRating.getComment());
			venueRatingRepo.saveAndFlush(dbVenueRating);
		}
		return dbVenueRating;
	}

	@Override
	public boolean deleteVenueRating(int venueRatingId) {
		boolean deleted = false;
		VenueRating venueRatingToDelete = venueRatingRepo.searchById(venueRatingId);
		if(venueRatingToDelete != null) {
			venueRatingRepo.deleteById(venueRatingId);
			deleted = true;
		}
		return deleted;
	}

}
