package com.skilldistillery.watchparty.services;

import java.util.List;

import com.skilldistillery.watchparty.entities.Sport;

public interface SportService {
	
	List<Sport> getAllSports();
	Sport getSport(int sportId);
	Sport createSport(Sport sport);
	Sport updateSport(int sportId, Sport sport);
	boolean deleteSport(int sportId);

}
