package com.skilldistillery.watchparty.entities;

import java.util.Objects;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "favorite_team")
public class FavoriteTeam {
	
	@EmbeddedId
	private FavoriteTeamId id;

	public FavoriteTeam() {
		super();
	}

	public FavoriteTeam(FavoriteTeamId id) {
		super();
		this.id = id;
	}

	public FavoriteTeamId getId() {
		return id;
	}

	public void setId(FavoriteTeamId id) {
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
		FavoriteTeam other = (FavoriteTeam) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "FavoriteTeam [id=" + id + "]";
	}
	
	

}
