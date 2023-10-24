package com.skilldistillery.watchparty.entities;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PartyCommentTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private PartyComment partyComment;

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
		partyComment = em.find(PartyComment.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		partyComment = null;
	}

	@Test
	void test() {
		assertNotNull(partyComment);
		assertEquals("Great food and people and we won, woohoo! Who could ask for more???", partyComment.getComment());
	}

}
