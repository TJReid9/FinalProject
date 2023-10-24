package com.skilldistillery.watchparty.entities;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class FriendId implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Column(name = "friend_id")
	private int friendId;
	
	@Column(name = "user_id")
	private int userId;
	
	

	public FriendId(int friendId, int userId) {
		super();
		this.friendId = friendId;
		this.userId = userId;
	}

	public FriendId() {
		super();
	}

	public int getFriendId() {
		return friendId;
	}

	public void setFriendId(int friendId) {
		this.friendId = friendId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		return Objects.hash(friendId, userId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FriendId other = (FriendId) obj;
		return friendId == other.friendId && userId == other.userId;
	}

	@Override
	public String toString() {
		return "PartyRatingId [friendId=" + friendId + ", userId=" + userId + "]";
	}
	
	
	
	
}
