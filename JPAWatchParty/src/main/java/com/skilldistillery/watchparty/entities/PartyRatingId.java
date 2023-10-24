package com.skilldistillery.watchparty.entities;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class PartyRatingId implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Column(name = "party_id")
	private int partyId;
	
	@Column(name = "user_id")
	private int userId;
	
	

	public PartyRatingId(int partyId, int userId) {
		super();
		this.partyId = partyId;
		this.userId = userId;
	}

	public PartyRatingId() {
		super();
	}

	public int getPartyId() {
		return partyId;
	}

	public void setPartyId(int partyId) {
		this.partyId = partyId;
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
		return Objects.hash(partyId, userId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PartyRatingId other = (PartyRatingId) obj;
		return partyId == other.partyId && userId == other.userId;
	}

	@Override
	public String toString() {
		return "PartyRatingId [partyId=" + partyId + ", userId=" + userId + "]";
	}
	
	
	
	
}
