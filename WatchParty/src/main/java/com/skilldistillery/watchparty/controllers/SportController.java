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

import com.skilldistillery.watchparty.entities.Sport;
import com.skilldistillery.watchparty.services.SportService;

@CrossOrigin({"*", "http://localhost/"})
@RequestMapping("api")
@RestController
public class SportController {

	@Autowired
	private SportService sportService;

	@GetMapping("sports")
	public List<Sport> getSportList() {
		return sportService.getAllSports();
	}

	@GetMapping("sports/{sportId}")
	public Sport getSportById(@PathVariable int sportId, HttpServletResponse res) {
		Sport sport = sportService.getSport(sportId);
		if (sport == null) {
			res.setStatus(404);
		}
		return sport;
	}

	@PostMapping("sports")
	public Sport createSport(@RequestBody Sport sport, HttpServletResponse res, HttpServletRequest req) {
		Sport newSport = null;
		try {
			if (newSport == null) {
				res.setStatus(404);
			}
			newSport = sportService.createSport(sport);
			res.setStatus(201);
			StringBuffer url = req.getRequestURL();
			url.append("/").append(newSport.getId());
			res.setHeader("Location", url.toString());
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
		}
		return newSport;
	}
	
	@PutMapping("sports/{sportId}")
	public Sport updateSport(@RequestBody Sport sport, @PathVariable int sportId, HttpServletResponse res) {
		Sport updatedSport = null;
		try {
			updatedSport = sportService.updateSport(sportId, sport);
			if(updatedSport == null) {
				res.setStatus(404);
			}
		} catch (Exception e) {
			res.setStatus(400);
			e.printStackTrace();
		}
		return updatedSport;
	}
	
	@DeleteMapping("sports/{sportId}")
	public void deleteSport(@PathVariable int sportId, HttpServletResponse res) {
		try {
			if(sportService.deleteSport(sportId)) {
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
