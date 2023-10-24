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

class PartyRatingTest {
	
	private static EntityManagerFactory emf;
	private EntityManager em;
	private PartyRating partyRating;

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
		PartyRatingId uid = new PartyRatingId();
		uid.setUserId(1);
		uid.setPartyId(1);
		
		partyRating = em.find(PartyRating.class, uid);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		partyRating = null;
	}

	@Test
	void test() {
		assertNotNull(partyRating);
		assertEquals("Food was great and Huskers won!!!  GBR!", partyRating.getComment());
	}

}
