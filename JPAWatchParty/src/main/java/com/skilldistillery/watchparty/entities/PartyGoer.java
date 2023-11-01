package com.skilldistillery.watchparty.entities;

import java.util.Objects;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "party_goer")
public class PartyGoer {
	
	@EmbeddedId
	private PartyGoerId id;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="party_id")
	@MapsId(value="partyId")
	@JsonIgnoreProperties({"friends"})
	private Party party;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	@MapsId(value="userId")
	@JsonIgnoreProperties({"friends"})
	private User user;
	
	public PartyGoer() {
		super();
	}

	public PartyGoer(PartyGoerId id) {
		super();
		this.id = id;
	}

	public PartyGoerId getId() {
		return id;
	}

	public void setId(PartyGoerId id) {
		this.id = id;
	}

	public Party getParty() {
		return party;
	}

	public void setParty(Party party) {
		this.party = party;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PartyGoer other = (PartyGoer) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "PartyGoers [id=" + id + "]";
	}
	
	

}
