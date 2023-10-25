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

import com.skilldistillery.watchparty.entities.Team;
import com.skilldistillery.watchparty.services.TeamService;

@CrossOrigin({"*", "http://localhost/"})
@RequestMapping("api")
@RestController
public class TeamController {

	@Autowired
	private TeamService teamService;

	@GetMapping("teams")
	public List<Team> getTeamList() {
		return teamService.getAllTeams();
	}

	@GetMapping("teams/{teamId}")
	public Team getTeamById(@PathVariable int teamId, HttpServletResponse res) {
		Team team = teamService.getTeam(teamId);
		if (team == null) {
			res.setStatus(404);
		}
		return team;
	}

	@PostMapping("teams")
	public Team createTeam(@RequestBody Team team, HttpServletResponse res, HttpServletRequest req) {
		Team newTeam = null;
		try {
			if (newTeam == null) {
				res.setStatus(404);
			}
			newTeam = teamService.createTeam(team);
			res.setStatus(201);
			StringBuffer url = req.getRequestURL();
			url.append("/").append(newTeam.getId());
			res.setHeader("Location", url.toString());
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
		}
		return newTeam;
	}
	
	@PutMapping("teams/{teamId}")
	public Team updateTeam(@RequestBody Team team, @PathVariable int teamId, HttpServletResponse res) {
		Team updatedTeam = null;
		try {
			updatedTeam = teamService.updateTeam(teamId, team);
			if(updatedTeam == null) {
				res.setStatus(404);
			}
		} catch (Exception e) {
			res.setStatus(400);
			e.printStackTrace();
		}
		return updatedTeam;
	}
	
	@DeleteMapping("teams/{teamId}")
	public void deleteTeam(@PathVariable int teamId, HttpServletResponse res) {
		try {
			if(teamService.deleteTeam(teamId)) {
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
