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

import com.skilldistillery.watchparty.entities.VenueRating;
import com.skilldistillery.watchparty.services.VenueRatingService;

@CrossOrigin({"*", "http://localhost/"})
@RequestMapping("api")
@RestController
public class VenueRatingController {

	@Autowired
	private VenueRatingService venueRatingService;

	@GetMapping("venueRatings")
	public List<VenueRating> getVenueRatingList() {
		return venueRatingService.getAllVenueRatings();
	}

	@GetMapping("venueRatings/{venueRatingId}")
	public VenueRating getVenueRatingById(@PathVariable int venueRatingId, HttpServletResponse res) {
		VenueRating venueRating = venueRatingService.getVenueRating(venueRatingId);
		if (venueRating == null) {
			res.setStatus(404);
		}
		return venueRating;
	}

	@PostMapping("venueRatings")
	public VenueRating createVenueRating(@RequestBody VenueRating venueRating, HttpServletResponse res, HttpServletRequest req) {
		VenueRating newVenueRating = null;
		try {
			if (newVenueRating == null) {
				res.setStatus(404);
			}
			newVenueRating = venueRatingService.createVenueRating(venueRating);
			res.setStatus(201);
			StringBuffer url = req.getRequestURL();
			url.append("/").append(newVenueRating.getId());
			res.setHeader("Location", url.toString());
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
		}
		return newVenueRating;
	}
	
	@PutMapping("venueRatings/{venueRatingId}")
	public VenueRating updateVenueRating(@RequestBody VenueRating venueRating, @PathVariable int venueRatingId, HttpServletResponse res) {
		VenueRating updatedVenueRating = null;
		try {
			updatedVenueRating = venueRatingService.updateVenueRating(venueRatingId, venueRating);
			if(updatedVenueRating == null) {
				res.setStatus(404);
			}
		} catch (Exception e) {
			res.setStatus(400);
			e.printStackTrace();
		}
		return updatedVenueRating;
	}
	
	@DeleteMapping("venueRatings/{venueRatingId}")
	public void deleteVenueRating(@PathVariable int venueRatingId, HttpServletResponse res) {
		try {
			if(venueRatingService.deleteVenueRating(venueRatingId)) {
				res.setStatus(204);
			} else {
				res.setStatus(404);
			}
		} catch (Exception e) {
			res.setStatus(400);
			e.printStackTrace();
		}
	}

}
