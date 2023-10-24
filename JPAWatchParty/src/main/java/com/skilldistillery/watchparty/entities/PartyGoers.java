package com.skilldistillery.watchparty.entities;

import java.util.Objects;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "party_goers")
public class PartyGoers {
	
	@EmbeddedId
	private PartyGoersId id;

	public PartyGoers() {
		super();
	}

	public PartyGoers(PartyGoersId id) {
		super();
		this.id = id;
	}

	public PartyGoersId getId() {
		return id;
	}

	public void setId(PartyGoersId id) {
		this.id = id;
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
		PartyGoers other = (PartyGoers) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "PartyGoers [id=" + id + "]";
	}
	
	

}
