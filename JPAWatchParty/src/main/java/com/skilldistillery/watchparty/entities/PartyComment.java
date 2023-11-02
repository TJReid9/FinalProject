package com.skilldistillery.watchparty.entities;

import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "party_comment")
public class PartyComment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String comment;
	
	@Column(name="photo_url")
	private String photoUrl;
	
	private Boolean enabled;
	
	@Column(name="create_date")
	@CreationTimestamp
	private LocalDate createDate;
	
	@Column(name="update_date")
	@UpdateTimestamp
	private LocalDate updateDate;
	
	@ManyToOne
	@JoinColumn(name = "party_id")
	private Party party;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	@JsonIgnoreProperties({"friends"})
	private User user;
	
	@ManyToOne
	@JoinColumn(name = "in_reply_to_id")
	private PartyComment partyCommentReply;
	
	

	public PartyComment() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getPhotoUrl() {
		return photoUrl;
	}

	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
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

	public PartyComment getPartyCommentReply() {
		return partyCommentReply;
	}

	public void setPartyCommentReply(PartyComment partyCommentReply) {
		this.partyCommentReply = partyCommentReply;
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
		PartyComment other = (PartyComment) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "PartyComment [id=" + id + ", comment=" + comment + ", photoUrl=" + photoUrl + ", enabled=" + enabled
				+ ", createDate=" + createDate + ", updateDate=" + updateDate + ", party=" + party + ", user=" + user
				+ ", partyCommentReply=" + partyCommentReply + "]";
	}
	
	
	
}
