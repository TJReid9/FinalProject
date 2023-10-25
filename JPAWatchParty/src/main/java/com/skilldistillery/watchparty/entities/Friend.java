package com.skilldistillery.watchparty.entities;

import java.util.List;
import java.util.Objects;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

@Entity
public class Friend {

	@EmbeddedId
	private FriendId id;
	
	@ManyToOne
	@JoinColumn(name = "friend_status_id")
	private FriendStatus friendStatus;
	
//	@ManyToMany
//	@JoinTable(
//		name = "friend",
//		joinColumns = @JoinColumn(name = "user_id"),
//		inverseJoinColumns = @JoinColumn(name = "friend_id", insertable = false, updatable = false))
//	private List<User> friends;
	
	@ManyToOne
	@JoinColumn(name = "friend_id")
	@MapsId(value = "friendId")
	private User friend;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	@MapsId(value = "userId")
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

//	public List<User> getFriends() {
//		return friends;
//	}
//
//	public void setFriends(List<User> friends) {
//		this.friends = friends;
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
		Friend other = (Friend) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Friend [id=" + id + ", friendStatus=" + friendStatus + ", friends=" + "]";
	}
	
	
	
}
