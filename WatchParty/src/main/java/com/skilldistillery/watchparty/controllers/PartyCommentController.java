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

import com.skilldistillery.watchparty.entities.PartyComment;
import com.skilldistillery.watchparty.services.PartyCommentService;

@CrossOrigin({"*", "http://localhost/"})
@RequestMapping("api")
@RestController
public class PartyCommentController {

	@Autowired
	private PartyCommentService partyCommentService;

	@GetMapping("watchparties/partyComments")
	public List<PartyComment> getPartyCommentList() {
		return partyCommentService.getAllPartyComments();
	}
	
	@GetMapping("watchparties/{partyId}/partyComments")
	public List<PartyComment> getPartyComments(@PathVariable int partyId) {
		return partyCommentService.findAllByParty(partyId);
	}

	@GetMapping("watchparties/partyComments/{partyCommentId}")
	public PartyComment getPartyCommentById(@PathVariable int partyCommentId, HttpServletResponse res) {
		PartyComment partyComment = partyCommentService.getPartyComment(partyCommentId);
		if (partyComment == null) {
			res.setStatus(404);
		}
		return partyComment;
	}

	@PostMapping("watchparties/{partyId}/partyComments")
	public PartyComment createPartyComment(@RequestBody PartyComment partyComment, @PathVariable int partyId, HttpServletResponse res, HttpServletRequest req) {
		PartyComment newPartyComment = null;
		try {
			if (newPartyComment == null) {
				res.setStatus(404);
			}
			newPartyComment = partyCommentService.createPartyComment(partyComment);
			res.setStatus(201);
			StringBuffer url = req.getRequestURL();
			url.append("/").append(newPartyComment.getId());
			res.setHeader("Location", url.toString());
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
		}
		return newPartyComment;
	}
	
	@PutMapping("watchparties/partyComments/{partyCommentId}")
	public PartyComment updatePartyComment(@RequestBody PartyComment partyComment, @PathVariable int partyCommentId, HttpServletResponse res) {
		PartyComment updatedPartyComment = null;
		try {
			updatedPartyComment = partyCommentService.updatePartyComment(partyCommentId, partyComment);
			if(updatedPartyComment == null) {
				res.setStatus(404);
			}
		} catch (Exception e) {
			res.setStatus(400);
			e.printStackTrace();
		}
		return updatedPartyComment;
	}
	
	
	@DeleteMapping("watchparties/{partyId}/partyComments/{partyCommentId}")
	public void deletePartyComment(@PathVariable int partyId, @PathVariable int partyCommentId, HttpServletResponse res) {
		try {
			if(partyCommentService.deletePartyComment(partyCommentId)) {
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
