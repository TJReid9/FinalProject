package com.skilldistillery.watchparty.entities;

import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "party_rating")
public class PartyRating{
	
	@EmbeddedId
	private PartyRatingId id;
	
	private String comment;
	
	private int rating;
	
	@Column(name = "create_date")
	@CreationTimestamp
	private LocalDate createDate; 
	
	@Column(name = "last_update")
	private LocalDate lastUpdate;

	public PartyRating() {
		super();
	}
	

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public LocalDate getCreateDate() {
		return createDate;
	}

	public void setCreateDate(LocalDate createDate) {
		this.createDate = createDate;
	}

	public LocalDate getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(LocalDate lastUpdate) {
		this.lastUpdate = lastUpdate;
	}


	public PartyRatingId getId() {
		return id;
	}


	public void setId(PartyRatingId id) {
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
		PartyRating other = (PartyRating) obj;
		return Objects.equals(id, other.id);
	}


	@Override
	public String toString() {
		return "PartyRating [id=" + id + ", comment=" + comment + ", rating=" + rating + ", createDate=" + createDate
				+ ", lastUpdate=" + lastUpdate + "]";
	}

	


	
	
	
}
