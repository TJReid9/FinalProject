package com.skilldistillery.watchparty.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.watchparty.entities.Team;
import com.skilldistillery.watchparty.repositories.TeamRepository;

@Service
public class TeamServiceImpl implements TeamService {
	
	@Autowired
	private TeamRepository teamRepo;
	
	@Override
	public List<Team> getAllTeams() {
		return teamRepo.findAll();
	}

	@Override
	public Team getTeam(int teamId) {
		return teamRepo.searchById(teamId);
	}

	@Override
	public Team createTeam(Team team) {
//		locationRepo.saveAndFlush(team.getLocation());
		return teamRepo.saveAndFlush(team);
	}

	@Override
	public Team updateTeam(int teamId, Team team) {
		Team dbTeam = teamRepo.searchById(teamId);
		System.out.println("***************" + dbTeam);
		if(dbTeam != null) {
			dbTeam.setName(team.getName());
			dbTeam.setLogoUrl(team.getLogoUrl());
			dbTeam.setTeamWebsiteUrl(team.getTeamWebsiteUrl());
			dbTeam.setSport(team.getSport());
			teamRepo.saveAndFlush(dbTeam);
		}
		return dbTeam;
	}

	@Override
	public boolean deleteTeam(int teamId) {
		boolean deleted = false;
		Team teamToDelete = teamRepo.searchById(teamId);
		if(teamToDelete != null) {
			teamRepo.deleteById(teamToDelete.getId());
			deleted = true;
		}
		return deleted;
	}

}
