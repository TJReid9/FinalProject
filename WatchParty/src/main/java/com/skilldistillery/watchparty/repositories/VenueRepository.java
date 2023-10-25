package com.skilldistillery.watchparty.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.watchparty.entities.Venue;

public interface VenueRepository extends JpaRepository<Venue, Integer> {

	Venue findById(int id);
	
}
