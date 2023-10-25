package com.skilldistillery.watchparty.entities;

import static org.junit.jupiter.api.Assertions.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PartyGoersTest {
	
	private static EntityManagerFactory emf;
	private EntityManager em;
	private PartyGoers partyGoers;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		emf = Persistence.createEntityManagerFactory("JPAWatchParty");
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		emf.close();
	}

	@BeforeEach
	void setUp() throws Exception {
		em = emf.createEntityManager();
		partyGoers = em.find(PartyGoers.class, new PartyGoersId(2,1));
		
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		partyGoers = null;
	}

	@Test
	void test() {
		assertNotNull(partyGoers);
		assertEquals(1, partyGoers.getId().getPartyId());
		assertEquals(2, partyGoers.getId().getUserId());
		
	
	}

}
