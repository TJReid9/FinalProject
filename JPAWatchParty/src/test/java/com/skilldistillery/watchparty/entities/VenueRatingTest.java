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

class VenueRatingTest {
	
	private static EntityManagerFactory emf;
	private EntityManager em;
	private VenueRating venueRating;

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
		VenueRatingId uid = new VenueRatingId();
		uid.setUserId(1);
		uid.setVenueId(1);
		
		venueRating = em.find(VenueRating.class, uid);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		venueRating = null;
	}

	@Test
	void test() {
		assertNotNull(venueRating);
		assertEquals("One of my favorite places to go, best burgers in town and $2 TACOS on Tuesday!!!!", venueRating.getComment());
	}
	
	@Test
	void test_VenueRating_relational_mapping_to_user() {
		User user = venueRating.getUser();
		assertNotNull(user);
		assertEquals("admin", user.getUsername());
	}
	
	@Test
	void test_VenueRating_relational_mapping_to_Venue() {
		Venue venue = venueRating.getVenue();
		assertNotNull(venue);
	}
}
