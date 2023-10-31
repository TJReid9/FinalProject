package com.skilldistillery.watchparty.services;

import java.util.List;

import com.skilldistillery.watchparty.entities.Party;
import com.skilldistillery.watchparty.entities.PartyGoer;

public interface PartyService {
	
	List<Party> getAllPartys();
	Party getParty(int partyId);
	Party createParty(Party party, String username);
	Party updateParty(int partyId, Party party);
	boolean deleteParty(int partyId);
	List<PartyGoer> getAllPartyGoers(Party party);
	PartyGoer addPartyGoerToParty(int partyId, String username);
	
	
}
