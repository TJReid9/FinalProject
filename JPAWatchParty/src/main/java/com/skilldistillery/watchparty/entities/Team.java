package com.skilldistillery.watchparty.entities;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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

	public Team() {
		super();
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
				+ "]";
	}
	
	

}
