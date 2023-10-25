package com.skilldistillery.watchparty.services;

import java.util.List;

import com.skilldistillery.watchparty.entities.Venue;

public interface VenueService {
	public List<Venue> showAll();
	
	Venue showVenueById(int id);
	
	Venue create(Venue venue);
	
	Venue update(Venue venue, int id);
	
	boolean destroy(int id);
	
}
