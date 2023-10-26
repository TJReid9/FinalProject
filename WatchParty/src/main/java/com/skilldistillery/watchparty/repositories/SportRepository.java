package com.skilldistillery.watchparty.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.watchparty.entities.Sport;

public interface SportRepository extends JpaRepository<Sport, Integer> {
	
	Sport searchById(int sportId);

}
