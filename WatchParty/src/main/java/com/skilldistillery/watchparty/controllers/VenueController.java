package com.skilldistillery.watchparty.controllers;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.watchparty.entities.Venue;
import com.skilldistillery.watchparty.services.VenueService;

@CrossOrigin({"*", "http://localhost/"})
@RestController
@RequestMapping("api")
public class VenueController {

	@Autowired
	private VenueService venueService;
	
	
	@GetMapping("venues")
	public List<Venue> listVenues(HttpServletResponse res){
		List<Venue> venues = null;
		try {
			venues = venueService.showAll();
			res.setStatus(200);
		} catch (Exception e) {
			res.setStatus(404);
			e.printStackTrace();
		}
		return venues;		
	}
	
	@GetMapping("venues/{venueId}")
	public Venue show(@PathVariable int venueId, HttpServletResponse res) {
		Venue venue = venueService.showVenueById(venueId);
		if (venue == null) {
			res.setStatus(404);
		} else {
			res.setStatus(200);
		}
		return venue;
	}
	
	@PostMapping("venues")
	public Venue create(@RequestBody Venue venue,HttpServletResponse res) {
		try {
			venue = venueService.create(venue);
			res.setStatus(201);
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
		}
		return venue;
	}
	
	
	@DeleteMapping("venues/{venueId}")
	public void delete(@PathVariable Integer venueId,HttpServletResponse res) {
		try {
			venueService.destroy(venueId);
			res.setStatus(204);
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
		}
	}
	
	@PutMapping("venues/{venueId}")
	public Venue update(@PathVariable int venueId, @RequestBody Venue venue,HttpServletResponse res) {
		Venue updatedVenue = null;
		try {
			updatedVenue = venueService.update(venue, venueId);
			res.setStatus(200);
			if (updatedVenue == null) {
				res.setStatus(404);
			}
		} catch (Exception e) {
			res.setStatus(400);
			e.printStackTrace();
		}
		return updatedVenue;
	}
	
	
}