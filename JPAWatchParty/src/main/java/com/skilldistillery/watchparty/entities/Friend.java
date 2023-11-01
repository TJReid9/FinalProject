package com.skilldistillery.watchparty.entities;

import java.util.Objects;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Friend {

	@EmbeddedId
	private FriendId id;
	
	@ManyToOne
	@JoinColumn(name = "friend_status_id")
	private FriendStatus friendStatus;
	
	@ManyToOne
	@JoinColumn(name = "friend_id")
	@MapsId(value = "friendId")
	@JsonIgnoreProperties({"friends"})
	private User friend;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	@MapsId(value = "userId")
	@JsonIgnoreProperties({"friends"})
	private User user;

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

	public FriendStatus getFriendStatus() {
		return friendStatus;
	}

	public void setFriendStatus(FriendStatus friendStatus) {
		this.friendStatus = friendStatus;
	}

	public User getFriend() {
		return friend;
	}

	public void setFriend(User friend) {
		this.friend = friend;
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
		Friend other = (Friend) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Friend [id=" + id + ", friendStatus=" + friendStatus + ", friend=" + friend + ", user=" + user + "]";
	}
	
	
	
}
