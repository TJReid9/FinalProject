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

import com.skilldistillery.watchparty.entities.Friend;
import com.skilldistillery.watchparty.entities.Team;
import com.skilldistillery.watchparty.entities.User;
import com.skilldistillery.watchparty.services.FriendService;
import com.skilldistillery.watchparty.services.TeamService;
import com.skilldistillery.watchparty.services.UserService;

@CrossOrigin({"*", "http://localhost/"})
@RestController
@RequestMapping("api")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private TeamService teamService;
	
	@Autowired
	private FriendService friendService;
	
	@GetMapping("watchparties/users")
	public List<User> listUsers(HttpServletResponse resp) {
		return userService.getAllUsers();
	}
	
	@GetMapping("watchparties/users/{userId}")
	public User retrieveUser(@PathVariable int userId, HttpServletResponse resp) {
		User user = userService.retrieveUser(userId);
		if (user == null ) {
			resp.setStatus(404);			
		}
		return user;
	}
	
	@PostMapping("watchparties/users/{userId}/teams")
	public List<Team> addFavoriteTeam(@PathVariable int userId, HttpServletResponse resp) {
		return teamService.getAllTeams();
	}
	
	@PostMapping("watchparties/users")
	public User create(@RequestBody User newUser, HttpServletResponse res, HttpServletRequest req) {
		User createdUser = null;
		try {
			createdUser = userService.create(newUser);
			res.setStatus(201);
			res.setHeader("Location", "http://localhost:8090/api/users" + createdUser.getId());
			StringBuffer url = req.getRequestURL();
			url.append("/").append(createdUser.getId());
			res.setHeader("Location", url.toString());
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
		}
		return createdUser;
	}
	
	@PutMapping("watchparties/users/{userId}")
	public User update(@PathVariable Integer userId, @RequestBody User updatingUser, HttpServletResponse res) {
		User updatedUser = null;
		try {
			updatedUser = userService.update(userId, updatingUser);
			if (updatedUser == null) {
				res.setStatus(404);
			}
		} catch (Exception e) {
			res.setStatus(400);
			e.printStackTrace();
		}
		return updatedUser;
	}
	
	@DeleteMapping("watchparties/users/{userId}")
	public void delete(@PathVariable Integer userId, HttpServletResponse res) {
		try {
			userService.delete(userId);
				res.setStatus(204);
				if(userId == null) {
					res.setStatus(404);
				}
			
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
		}
		
	}
	
	@PutMapping("watchparties/users/{userId}/teams/{teamId}")
	public void deleteFavTeamById(@PathVariable Integer userId,@PathVariable int teamId, HttpServletResponse res) {
		try {
			userService.removeFavoriteTeamById(teamService.getTeam(teamId), userService.retrieveUser(userId));
			res.setStatus(204);
			if(userId == null) {
				res.setStatus(404);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
		}
		
	}
	
//	@PostMapping("watchparties/users/{userId}/friends")
//	public List<Friend> addFriend(@PathVariable int userId, HttpServletResponse resp) {
////		return friendService.create();
//	}
	
	@PutMapping("watchparties/users/{userId}/friends")
	public void deleteFriendById(@PathVariable Integer userId, HttpServletResponse res, Principal pr) {
		try {
			userService.removeFriendById(userId, pr.getName());
			res.setStatus(204);
			if(userId == null) {
				res.setStatus(404);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
		}
		
	}
	
	@PostMapping("watchparties/{friendId}/friends")
	public Friend addFriend( @PathVariable int friendId, Principal principal, HttpServletResponse res) {
		Friend updatedFriend = null;
		try {
			updatedFriend = friendService.addBuddy(friendId, principal.getName());
			if(updatedFriend == null) {
				res.setStatus(404);
			}
		} catch (Exception e) {
			res.setStatus(400);
			e.printStackTrace();
		}
		return updatedFriend;
	}

}
