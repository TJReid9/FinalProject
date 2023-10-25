package com.skilldistillery.watchparty.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.watchparty.entities.Venue;
import com.skilldistillery.watchparty.repositories.VenueRepository;


@Service
public class VenueServiceImpl implements VenueService {
	
	@Autowired 
	VenueRepository venueRepo;
	

	@Override
	public List<Venue> showAll() {
		
		return venueRepo.findAll();
	}

	@Override
	public Venue showVenueById(int id) {
		Venue venue = venueRepo.findById(id);
		if( venue != null) {
			return venue;
		}
		return null;
	}

	@Override
	public Venue create(Venue venue) {
		
		return venueRepo.saveAndFlush(venue);
	}

	@Override
	public void destroy(int id) {
		venueRepo.deleteById(id);
		
	}

	@Override
	public Venue update(Venue venue, int id) {
		Venue dbVenue = venueRepo.findById(id);
		
		if(dbVenue != null) {
			
			//might need to add by individual column info city/street/state/zip
			dbVenue.setAddress(venue.getAddress());
			
			//do we want enabled in here or is this for admin only??
			dbVenue.setEnabled(venue.getEnabled());
			
			dbVenue.setDescription(venue.getDescription());
			dbVenue.setImageUrl(venue.getImageUrl());
			dbVenue.setName(venue.getName());
			dbVenue.setPhone(venue.getPhone());
			dbVenue.setWebsiteUrl(venue.getWebsiteUrl());
			venueRepo.saveAndFlush(dbVenue);
		}
		return dbVenue;
	}

}
