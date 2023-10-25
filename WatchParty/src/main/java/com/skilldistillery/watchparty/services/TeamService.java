package com.skilldistillery.watchparty.services;

import java.util.List;

import com.skilldistillery.watchparty.entities.Team;

public interface TeamService {
	
	List<Team> getAllTeams();
	Team getTeam(int teamId);
	Team createTeam(Team team);
	Team updateTeam(int teamId, Team team);
	boolean deleteTeam(int teamId);

}
