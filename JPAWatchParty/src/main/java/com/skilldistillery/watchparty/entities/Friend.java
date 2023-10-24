package com.skilldistillery.watchparty.entities;

import java.util.Objects;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class Friend {

	@EmbeddedId
	private FriendId id;

	public Friend(FriendId id) {
		super();
		this.id = id;
	}

	public Friend() {
		super();
	}

	public FriendId getId() {
		return id;
	}

	public void setId(FriendId id) {
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
		Friend other = (Friend) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Friend [id=" + id + "]";
	}
	
	
	
}
