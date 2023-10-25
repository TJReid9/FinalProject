package com.skilldistillery.watchparty.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.watchparty.entities.VenueRating;

public interface VenueRatingRepository extends JpaRepository<VenueRating, Integer> {
	
	VenueRating searchById(int venueRatingId);

}
