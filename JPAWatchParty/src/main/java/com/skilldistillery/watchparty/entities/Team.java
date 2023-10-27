package com.skilldistillery.watchparty.entities;

import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Team {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String name;
	
	@Column(name = "logo_url")
	private String logoUrl;
	
	@Column(name = "team_website_url")
	private String teamWebsiteUrl;
	
	@ManyToOne
	@JoinColumn(name = "sport_id")
	private Sport sport;
	
//	@OneToMany
//	List<FavoriteTeam> favoriteTeams;

	public Team() {
		super();
	}
	
	

	public Team(int id, String name, String logoUrl, String teamWebsiteUrl, Sport sport) {
		super();
		this.id = id;
		this.name = name;
		this.logoUrl = logoUrl;
		this.teamWebsiteUrl = teamWebsiteUrl;
		this.sport = sport;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLogoUrl() {
		return logoUrl;
	}

	public void setLogoUrl(String logoUrl) {
		this.logoUrl = logoUrl;
	}

	public String getTeamWebsiteUrl() {
		return teamWebsiteUrl;
	}

	public void setTeamWebsiteUrl(String teamWebsiteUrl) {
		this.teamWebsiteUrl = teamWebsiteUrl;
	}

	public Sport getSport() {
		return sport;
	}

	public void setSport(Sport sport) {
		this.sport = sport;
	}

//	public List<FavoriteTeam> getFavoriteTeams() {
//		return favoriteTeams;
//	}
//
//	public void setFavoriteTeams(List<FavoriteTeam> favoriteTeams) {
//		this.favoriteTeams = favoriteTeams;
//	}

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
		Team other = (Team) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "Team [id=" + id + ", name=" + name + ", logoUrl=" + logoUrl + ", teamWebsiteUrl=" + teamWebsiteUrl
				+ ", sport=" + sport + "]";
	}
	
	

}
