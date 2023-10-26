package com.skilldistillery.watchparty.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
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
	
	
	@GetMapping("watchparties/venues")
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
	
	@GetMapping("watchparties/venues/{venueId}")
	public Venue show(@PathVariable int venueId, HttpServletResponse res) {
		Venue venue = venueService.showVenueById(venueId);
		if (venue == null) {
			res.setStatus(404);
		} else {
			res.setStatus(200);
		}
		return venue;
	}
	
	@PostMapping("watchparties/venues")
	public Venue create(@RequestBody Venue venue,HttpServletResponse res, HttpServletRequest req) {
		try {
			venue = venueService.create(venue);
			res.setStatus(201);
			StringBuffer url = req.getRequestURL();
			url.append("/").append(venue.getId());
			res.setHeader("Location", url.toString());
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
		}
		return venue;
	}
	
	
	@DeleteMapping("watchparties/venues/{venueId}")
	public void delete(@PathVariable Integer venueId,HttpServletResponse res) {
		try {
			if (venueService.destroy(venueId)) {
			res.setStatus(204);
			} else {
				res.setStatus(404);
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
		}
	}
	
	@PutMapping("watchparties/venues/{venueId}")
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
