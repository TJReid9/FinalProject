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

class PartyTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private Party party;

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
		party = em.find(Party.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		party = null;
	}

	@Test
	void test() {
		assertNotNull(party);
		assertEquals("Huskers vs Northwestern", party.getTitle());
	}

}
