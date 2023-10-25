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

import com.skilldistillery.watchparty.entities.DirectMessage;
import com.skilldistillery.watchparty.services.DirectMessageService;

@CrossOrigin({"*", "http://localhost/"})
@RequestMapping("api")
@RestController
public class DirectMessageController {

	@Autowired
	private DirectMessageService directMessageService;

	@GetMapping("directMessages")
	public List<DirectMessage> getDirectMessageList() {
		return directMessageService.getAllDirectMessages();
	}

	@GetMapping("directMessages/{directMessageId}")
	public DirectMessage getDirectMessageById(@PathVariable int directMessageId, HttpServletResponse res) {
		DirectMessage directMessage = directMessageService.getDirectMessage(directMessageId);
		if (directMessage == null) {
			res.setStatus(404);
		}
		return directMessage;
	}

	@PostMapping("directMessages")
	public DirectMessage createDirectMessage(@RequestBody DirectMessage directMessage, HttpServletResponse res, HttpServletRequest req) {
		DirectMessage newDirectMessage = null;
		try {
			if (newDirectMessage == null) {
				res.setStatus(404);
			}
			newDirectMessage = directMessageService.createDirectMessage(directMessage);
			res.setStatus(201);
			StringBuffer url = req.getRequestURL();
			url.append("/").append(newDirectMessage.getId());
			res.setHeader("Location", url.toString());
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
		}
		return newDirectMessage;
	}
	
	@PutMapping("directMessages/{directMessageId}")
	public DirectMessage updateDirectMessage(@RequestBody DirectMessage directMessage, @PathVariable int directMessageId, HttpServletResponse res) {
		DirectMessage updatedDirectMessage = null;
		try {
			updatedDirectMessage = directMessageService.updateDirectMessage(directMessageId, directMessage);
			if(updatedDirectMessage == null) {
				res.setStatus(404);
			}
		} catch (Exception e) {
			res.setStatus(400);
			e.printStackTrace();
		}
		return updatedDirectMessage;
	}
	
	@DeleteMapping("directMessages/{directMessageId}")
	public void deleteDirectMessage(@PathVariable int directMessageId, HttpServletResponse res) {
		try {
			if(directMessageService.deleteDirectMessage(directMessageId)) {
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
