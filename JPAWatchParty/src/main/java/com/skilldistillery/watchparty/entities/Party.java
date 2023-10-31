package com.skilldistillery.watchparty.entities;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Party {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String title;
	
	@Column(name="party_date")
	private LocalDate partyDate;
	
	@Column(name="start_time")
	private LocalTime startTime;
	
	private String description;
	
	private Boolean completed;
	
	private Boolean enabled;
	
	@Column(name = "image_url")
	private String imageUrl;
	
	@Column(name="create_date")
	@CreationTimestamp
	private LocalDate createDate;
	
	@Column(name="update_date")
	@UpdateTimestamp
	private LocalDate updateDate;
	
	@ManyToOne
	@JoinColumn(name = "venue_id")
	private Venue venue;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	@ManyToOne
	@JoinColumn(name = "team_id")
	private Team team;
	
	@OneToMany(mappedBy = "party", fetch = FetchType.EAGER)
	private List<PartyGoer> partyGoers;
	
	@JsonIgnore
	@OneToMany(mappedBy = "party")
	private List<PartyComment> partyComments;
	

	public Party() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public LocalDate getPartyDate() {
		return partyDate;
	}

	public void setPartyDate(LocalDate partyDate) {
		this.partyDate = partyDate;
	}

	public LocalTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getCompleted() {
		return completed;
	}

	public void setCompleted(Boolean completed) {
		this.completed = completed;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public LocalDate getCreateDate() {
		return createDate;
	}

	public void setCreateDate(LocalDate createDate) {
		this.createDate = createDate;
	}

	public LocalDate getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(LocalDate updateDate) {
		this.updateDate = updateDate;
	}

	public Venue getVenue() {
		return venue;
	}

	public void setVenue(Venue venue) {
		this.venue = venue;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	public List<PartyGoer> getPartyGoers() {
		return partyGoers;
	}

	public void setPartyGoers(List<PartyGoer> partyGoers) {
		this.partyGoers = partyGoers;
	}

	

	public List<PartyComment> getPartyComments() {
		return partyComments;
	}

	public void setPartyComments(List<PartyComment> partyComments) {
		this.partyComments = partyComments;
	}

	@Override
	public String toString() {
		return "Party [id=" + id + ", title=" + title + ", partyDate=" + partyDate + ", startTime=" + startTime
				+ ", description=" + description + ", completed=" + completed + ", enabled=" + enabled + ", imageUrl="
				+ imageUrl + ", createDate=" + createDate + ", updateDate=" + updateDate + ", venue=" + venue
				+ ", user=" + user + ", team=" + team + ", partyGoers=" + partyGoers + "]";
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
		Party other = (Party) obj;
		return id == other.id;
	}
	
	
}
