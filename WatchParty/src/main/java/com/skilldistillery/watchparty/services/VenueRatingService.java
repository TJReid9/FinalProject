package com.skilldistillery.watchparty.services;

import java.util.List;

import com.skilldistillery.watchparty.entities.VenueRating;

public interface VenueRatingService {
	
	List<VenueRating> getAllVenueRatings();
	VenueRating getVenueRating(int venueRatingId);
	VenueRating createVenueRating(VenueRating venueRating);
	VenueRating updateVenueRating(int venueRatingId, VenueRating venueRating);
	boolean deleteVenueRating(int venueRatingId);

}
