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

import com.skilldistillery.watchparty.entities.UserComment;
import com.skilldistillery.watchparty.services.UserCommentService;

@CrossOrigin({"*", "http://localhost/"})
@RequestMapping("api")
@RestController
public class UserCommentController {

	@Autowired
	private UserCommentService userCommentService;

	@GetMapping("watchparties/userComments")
	public List<UserComment> getUserCommentList() {
		return userCommentService.getAllUserComments();
	}

	@GetMapping("watchparties/userComments/{userCommentId}")
	public UserComment getUserCommentById(@PathVariable int userCommentId, HttpServletResponse res) {
		UserComment userComment = userCommentService.getUserComment(userCommentId);
		if (userComment == null) {
			res.setStatus(404);
		}
		return userComment;
	}

	@PostMapping("watchparties/userComments")
	public UserComment createUserComment(@RequestBody UserComment userComment, HttpServletResponse res, HttpServletRequest req) {
		UserComment newUserComment = null;
		try {
			if (newUserComment == null) {
				res.setStatus(404);
			}
			newUserComment = userCommentService.createUserComment(userComment);
			res.setStatus(201);
			StringBuffer url = req.getRequestURL();
			url.append("/").append(newUserComment.getId());
			res.setHeader("Location", url.toString());
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
		}
		return newUserComment;
	}
	
	@PutMapping("watchparties/userComments/{userCommentId}")
	public UserComment updateUserComment(@RequestBody UserComment userComment, @PathVariable int userCommentId, HttpServletResponse res) {
		UserComment updatedUserComment = null;
		try {
			updatedUserComment = userCommentService.updateUserComment(userCommentId, userComment);
			if(updatedUserComment == null) {
				res.setStatus(404);
			}
		} catch (Exception e) {
			res.setStatus(400);
			e.printStackTrace();
		}
		return updatedUserComment;
	}
	
	@DeleteMapping("watchparties/userComments/{userCommentId}")
	public void deleteUserComment(@PathVariable int userCommentId, HttpServletResponse res) {
		try {
			if(userCommentService.deleteUserComment(userCommentId)) {
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
