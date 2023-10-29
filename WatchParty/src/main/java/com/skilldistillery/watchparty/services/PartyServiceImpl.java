package com.skilldistillery.watchparty.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.watchparty.entities.Party;
import com.skilldistillery.watchparty.repositories.PartyRepository;
import com.skilldistillery.watchparty.repositories.UserRepository;

@Service
public class PartyServiceImpl implements PartyService {
	
	@Autowired
	private PartyRepository partyRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	@Override
	public List<Party> getAllPartys() {
		return partyRepo.findAll();
	}

	@Override
	public Party getParty(int partyId) {
		return partyRepo.searchById(partyId);
	}

	@Override
	public Party createParty(Party party, String username) {
//		locationRepo.saveAndFlush(party.getLocation());
		party.setUser(userRepo.findByUsername(username));
		return partyRepo.saveAndFlush(party);
	}

	@Override
	public Party updateParty(int partyId, Party party) {
		Party dbParty = partyRepo.searchById(partyId);
		System.out.println("***************" + dbParty);
		if(dbParty != null) {
			dbParty.setTitle(party.getTitle());
			dbParty.setPartyDate(party.getPartyDate());
			dbParty.setStartTime(party.getStartTime());
			dbParty.setDescription(party.getDescription());
			dbParty.setCompleted(party.getCompleted());
			dbParty.setEnabled(party.getEnabled());
			dbParty.setImageUrl(party.getImageUrl());
			dbParty.setVenue(party.getVenue());
			dbParty.setTeam(party.getTeam());
			partyRepo.saveAndFlush(dbParty);
		}
		return dbParty;
	}

	@Override
	public boolean deleteParty(int partyId) {
		boolean deleted = false;
		Party partyToDelete = partyRepo.searchById(partyId);
		if(partyToDelete != null) {
			partyRepo.deleteById(partyToDelete.getId());
			deleted = true;
		}
		return deleted;
	}

}
