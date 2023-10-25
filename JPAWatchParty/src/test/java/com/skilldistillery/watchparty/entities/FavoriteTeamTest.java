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

class FavoriteTeamTest {
	
	private static EntityManagerFactory emf;
	private EntityManager em;
	private FavoriteTeam favoriteTeam;

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
		favoriteTeam = em.find(FavoriteTeam.class, new FavoriteTeamId(1,1));
		
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		favoriteTeam = null;
	}

	@Test
	void test() {
		assertNotNull(favoriteTeam);
		assertEquals(1, favoriteTeam.getId().getTeamId());
		assertEquals(1, favoriteTeam.getId().getUserId());
		
	
	}

}
