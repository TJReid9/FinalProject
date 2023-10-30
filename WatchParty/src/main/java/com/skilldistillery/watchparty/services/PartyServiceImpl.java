package com.skilldistillery.watchparty.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.watchparty.entities.Party;
import com.skilldistillery.watchparty.entities.PartyGoer;
import com.skilldistillery.watchparty.entities.PartyGoerId;
import com.skilldistillery.watchparty.repositories.PartyGoerRepository;
import com.skilldistillery.watchparty.repositories.PartyRepository;
import com.skilldistillery.watchparty.repositories.UserRepository;
import com.skilldistillery.watchparty.repositories.VenueRepository;

@Service
public class PartyServiceImpl implements PartyService {
	
	@Autowired
	private PartyRepository partyRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private VenueRepository venueRepo;
	
	@Autowired
	private PartyGoerRepository partyGoerRepo;
	
	@Override
	public List<Party> getAllPartys() {
		return partyRepo.findAll();
	}

	@Override
	public Party getParty(int partyId) {
		return partyRepo.searchById(partyId);
	}

	@Override
	public Party createParty(Party newParty, String username) {
	   // venueRepo.saveAndFlush(newParty.getVenue());
		newParty.setUser(userRepo.findByUsername(username)); 
		partyRepo.saveAndFlush(newParty);
		System.out.println(newParty.getId());
		System.out.println(newParty.getUser().getId());
		partyRepo.searchById(newParty.getId());
		PartyGoer partyGoer = new PartyGoer();
		PartyGoerId id = new PartyGoerId(newParty.getUser().getId(), newParty.getId());
		partyGoer.setId(id);
		System.out.println(partyGoer.getId());
		partyGoer.setUser(userRepo.findByUsername(username));
		partyGoer.setParty(newParty);
		partyGoerRepo.saveAndFlush(partyGoer);
//		List<PartyGoer> partyGoers = new ArrayList<>();
//		partyGoers.add(partyGoer);
//		System.out.println(partyGoers);
//		newParty.setPartyGoers(partyGoers);
		
		newParty.setUser(userRepo.findByUsername(username));
		
		//System.out.println("********" + partyGoer);
		return newParty;
	
	//	user = userRepo.searchById(user.getId());
//		if (user != null) {
//			newParty.setUser(user);
//			PartyGoer partyOwner = new PartyGoer();
//			PartyGoerId id = new PartyGoerId(user.getId(), newParty.getId());
//			partyOwner.setId(id);
//			List<PartyGoer> members = new ArrayList<>();
//			members.add(partyOwner);
//			newParty.setPartyGoers(members);
//			return partyRepo.saveAndFlush(newParty);
//
//		}
//		return null;
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
			dbParty.setPartyGoers(party.getPartyGoers());
			partyRepo.saveAndFlush(dbParty);
		}
		return dbParty;
	}

	@Override
	public boolean deleteParty(int partyId) {
		boolean deleted = false;
	
		Party partyToDelete = partyRepo.searchById(partyId);
		if(partyToDelete != null) {
			if(partyToDelete.getPartyGoers() != null) {
				partyRepo.findById(partyId);
				List<PartyGoer> pg = new ArrayList<>();
				pg = partyToDelete.getPartyGoers();
					for (PartyGoer partyGoer : pg) {
						partyGoerRepo.delete(partyGoer);	
					}
			}
			partyRepo.deleteById(partyToDelete.getId());
			deleted = true;
		}
		return deleted;
	}

	@Override
	public List<PartyGoer> getAllPartyGoers(Party party) {
		partyRepo.findById(party.getId());
		List<PartyGoer> pg = party.getPartyGoers(); 
			for (PartyGoer partyGoer : pg) {
				pg.remove(partyGoer);	
			}
	
		return pg;
	}

	@Override
	public PartyGoer updatePartyGoers(int partyId, Party party, PartyGoer partyGoers, String username) {
		PartyGoer partyGoer = new PartyGoer();
		PartyGoerId id = new PartyGoerId(party.getUser().getId(), party.getId());
		partyGoer.setId(id);
		partyGoer.setUser(userRepo.findByUsername(username));
		partyGoer.setParty(party);
		partyGoerRepo.saveAndFlush(partyGoer);
		return partyGoer;
	}

}
