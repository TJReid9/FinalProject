package com.skilldistillery.watchparty.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name= "venue_rating")
public class VenueRating implements Serializable{

	@EmbeddedId
	private VenueRatingId id;
	
	private int rating;
	
	private String comment;
	
	@Column(name = "create_date")
	@CreationTimestamp
	private LocalDate createDate;
	
	@Column(name = "update_date")
	@UpdateTimestamp
	private LocalDate updateDate;
	
	@ManyToOne
	@JoinColumn(name = "venue_id", insertable = false, updatable = false)
	private Venue venue;
	
	@ManyToOne
	@JoinColumn(name = "user_id", insertable = false, updatable = false)
	private User user;
	

	public VenueRating() {
		super();
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
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

	public VenueRatingId getId() {
		return id;
	}

	public void setId(VenueRatingId id) {
		this.id = id;
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
		VenueRating other = (VenueRating) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "VenueRating [id=" + id + ", rating=" + rating + ", comment=" + comment + ", createDate=" + createDate
				+ ", updateDate=" + updateDate + ", venue=" + venue + ", user=" + user + "]";
	}



	
	
	
}
