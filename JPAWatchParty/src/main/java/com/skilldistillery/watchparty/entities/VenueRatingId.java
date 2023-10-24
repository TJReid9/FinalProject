package com.skilldistillery.watchparty.entities;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class VenueRatingId implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Column(name = "venue_id")
	private int venueId;
	
	@Column(name = "user_id")
	private int userId;
	
	

	public VenueRatingId(int venueId, int userId) {
		super();
		this.venueId = venueId;
		this.userId = userId;
	}

	public VenueRatingId() {
		super();
	}

	public int getVenueId() {
		return venueId;
	}

	public void setVenueId(int venueId) {
		this.venueId = venueId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		return Objects.hash(venueId, userId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		VenueRatingId other = (VenueRatingId) obj;
		return venueId == other.venueId && userId == other.userId;
	}

	@Override
	public String toString() {
		return "PartyRatingId [venueId=" + venueId + ", userId=" + userId + "]";
	}
	
	
	
	
}
