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

class FriendTest {
	
	private static EntityManagerFactory emf;
	private EntityManager em;
	private Friend friend;

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
		FriendId uid = new FriendId();
		uid.setUserId(1);
		uid.setFriendId(2);
		
		friend = em.find(Friend.class, uid);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		friend = null;
	}
	@Test
	void test() {
		assertNotNull(friend);
		assertEquals(1, friend.getId().getUserId());

	}

}
