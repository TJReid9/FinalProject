package com.skilldistillery.watchparty.controllers;

import java.security.Principal;
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

import com.skilldistillery.watchparty.entities.Party;
import com.skilldistillery.watchparty.services.PartyService;

@CrossOrigin({"*", "http://localhost/"})
@RequestMapping("api")
@RestController
public class PartyController {

	@Autowired
	private PartyService partyService;

	@GetMapping("watchparties")
	public List<Party> getPartyList() {
		return partyService.getAllPartys();
	}

	@GetMapping("watchparties/{partyId}")
	public Party getPartyById(@PathVariable int partyId, HttpServletResponse res) {
		Party party = partyService.getParty(partyId);
		if (party == null) {
			res.setStatus(404);
		}
		return party;
	}

	@PostMapping("watchparties")
	public Party createParty(@RequestBody Party party, HttpServletResponse res, HttpServletRequest req, Principal principal) {
		Party newParty = null;
		System.out.println(principal.getName());
		try {
			if (newParty == null) {
				res.setStatus(404);
			}
			newParty = partyService.createParty(party, principal.getName());
			res.setStatus(201);
			StringBuffer url = req.getRequestURL();
			url.append("/").append(newParty.getId());
			res.setHeader("Location", url.toString());
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
		}
		return newParty;
	}
	
	@PutMapping("watchparties/{partyId}")
	public Party updateParty(@RequestBody Party party, @PathVariable int partyId, HttpServletResponse res) {
		Party updatedParty = null;
		try {
			updatedParty = partyService.updateParty(partyId, party);
			if(updatedParty == null) {
				res.setStatus(404);
			}
		} catch (Exception e) {
			res.setStatus(400);
			e.printStackTrace();
		}
		return updatedParty;
	}
	
	@DeleteMapping("watchparties/{partyId}")
	public void deleteParty(@PathVariable int partyId, HttpServletResponse res) {
		try {
			if(partyService.deleteParty(partyId)) {
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
