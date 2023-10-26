package com.skilldistillery.watchparty.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.watchparty.entities.Sport;
import com.skilldistillery.watchparty.repositories.SportRepository;

@Service
public class SportServiceImpl implements SportService {
	
	@Autowired
	private SportRepository sportRepo;
	
	@Override
	public List<Sport> getAllSports() {
		return sportRepo.findAll();
	}

	@Override
	public Sport getSport(int sportId) {
		return sportRepo.searchById(sportId);
	}

	@Override
	public Sport createSport(Sport sport) {
//		locationRepo.saveAndFlush(sport.getLocation());
		return sportRepo.saveAndFlush(sport);
	}

	@Override
	public Sport updateSport(int sportId, Sport sport) {
		Sport dbSport = sportRepo.searchById(sportId);
		System.out.println("***************" + dbSport);
		if(dbSport != null) {
			dbSport.setName(sport.getName());
			sportRepo.saveAndFlush(dbSport);
		}
		return dbSport;
	}

	@Override
	public boolean deleteSport(int sportId) {
		boolean deleted = false;
		Sport sportToDelete = sportRepo.searchById(sportId);
		if(sportToDelete != null) {
			sportRepo.deleteById(sportToDelete.getId());
			deleted = true;
		}
		return deleted;
	}

}
