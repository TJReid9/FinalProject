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

import com.skilldistillery.watchparty.entities.PartyRating;
import com.skilldistillery.watchparty.services.PartyRatingService;

@CrossOrigin({"*", "http://localhost/"})
@RequestMapping("api")
@RestController
public class PartyRatingController {

	@Autowired
	private PartyRatingService partyRatingService;

	@GetMapping("watchparties/partyRatings")
	public List<PartyRating> getPartyRatingList() {
		return partyRatingService.getAllPartyRatings();
	}

	@GetMapping("watchparties/partyRatings/{partyRatingId}")
	public PartyRating getPartyRatingById(@PathVariable int partyRatingId, HttpServletResponse res) {
		PartyRating partyRating = partyRatingService.getPartyRating(partyRatingId);
		if (partyRating == null) {
			res.setStatus(404);
		}
		return partyRating;
	}

	@PostMapping("watchparties/partyRatings")
	public PartyRating createPartyRating(@RequestBody PartyRating partyRating, HttpServletResponse res, HttpServletRequest req) {
		PartyRating newPartyRating = null;
		try {
			if (newPartyRating == null) {
				res.setStatus(404);
			}
			newPartyRating = partyRatingService.createPartyRating(partyRating);
			res.setStatus(201);
			StringBuffer url = req.getRequestURL();
			url.append("/").append(newPartyRating.getId());
			res.setHeader("Location", url.toString());
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
		}
		return newPartyRating;
	}
	
	@PutMapping("watchparties/partyRatings/{partyRatingId}")
	public PartyRating updatePartyRating(@RequestBody PartyRating partyRating, @PathVariable int partyRatingId, HttpServletResponse res) {
		PartyRating updatedPartyRating = null;
		try {
			updatedPartyRating = partyRatingService.updatePartyRating(partyRatingId, partyRating);
			if(updatedPartyRating == null) {
				res.setStatus(404);
			}
		} catch (Exception e) {
			res.setStatus(400);
			e.printStackTrace();
		}
		return updatedPartyRating;
	}
	
	@DeleteMapping("watchparties/partyRatings/{partyRatingId}")
	public void deletePartyRating(@PathVariable int partyRatingId, HttpServletResponse res) {
		try {
			if(partyRatingService.deletePartyRating(partyRatingId)) {
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
