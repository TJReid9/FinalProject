package com.skilldistillery.watchparty.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.watchparty.entities.Party;

public interface PartyRepository extends JpaRepository<Party, Integer> {
	
	Party searchById(int partyId);

}
