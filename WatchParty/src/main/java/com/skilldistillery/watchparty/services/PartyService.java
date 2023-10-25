package com.skilldistillery.watchparty.services;

import java.util.List;

import com.skilldistillery.watchparty.entities.Party;

public interface PartyService {
	
	List<Party> getAllPartys();
	Party getParty(int partyId);
	Party createParty(Party party);
	Party updateParty(int partyId, Party party);
	boolean deleteParty(int partyId);

}
