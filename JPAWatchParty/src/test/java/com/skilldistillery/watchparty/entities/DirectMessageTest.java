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

class DirectMessageTest {
	
	private static EntityManagerFactory emf;
	private EntityManager em;
	private DirectMessage directMessage;

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
		directMessage = em.find(DirectMessage.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		directMessage = null;
	}

	@Test
	void test() {
		assertNotNull(directMessage);
		assertEquals("Hey James, I'll bring the Hardware to the NW Party!!!", directMessage.getContent());
	}
	
	@Test
	void test_sender_relationship_mapping() {
		assertNotNull(directMessage);
		assertEquals("Johnny", directMessage.getSender().getFirstName());
	}
	
	@Test
	void test_recipient_relationship_mapping() {
		assertNotNull(directMessage);
		assertEquals("James", directMessage.getRecipient().getFirstName());
	}

}
