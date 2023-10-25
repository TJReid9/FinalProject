package com.skilldistillery.watchparty.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.watchparty.entities.Team;

public interface TeamRepository extends JpaRepository<Team, Integer> {
	
	Team searchById(int teamId);

}
