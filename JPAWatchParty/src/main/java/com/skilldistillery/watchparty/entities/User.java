package com.skilldistillery.watchparty.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String username;
	
	private String password;
	
	private String email;
	
	@Column(name = "first_name")
	private String firstName;
	
	@Column(name = "photo_url")
	private String photoUrl;
	
	private String role;
	
	private boolean enabled;
	
	@Column(name = "create_date")
	@CreationTimestamp
	private LocalDate createDate;
	
	@Column(name = "update_date")
	@UpdateTimestamp
	private LocalDate updateDate;
	
	@OneToOne
	@JoinColumn(name = "address_id")
	private Address address;
	
	@JsonIgnore
	@OneToMany(mappedBy = "friend")
	private List<Friend> friends;

	public User() {
		super();
	}

	

	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getUsername() {
		return username;
	}



	public void setUsername(String username) {
		this.username = username;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getFirstName() {
		return firstName;
	}



	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}



	public String getPhotoUrl() {
		return photoUrl;
	}



	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}



	public String getRole() {
		return role;
	}



	public void setRole(String role) {
		this.role = role;
	}



	public boolean isEnabled() {
		return enabled;
	}



	public void setEnabled(boolean enabled) {
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



	public Address getAddress() {
		return address;
	}



	public void setAddress(Address address) {
		this.address = address;
	}



	public List<Friend> getFriends() {
		return friends;
	}



	public void setFriends(List<Friend> friends) {
		this.friends = friends;
	}



	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
	
	public void addFriend(User friend) {
		if(friends == null) {
			friends = new ArrayList<>();
		}
		if(! friends.contains(friend)) {
			friends.add(friend);
			friend.addFriend(this);
		}
	}
	
	public void removeFriend(User friend) {
		if (friends != null && friends.contains(friend)) {
			friends.remove(friend);
			friend.removeFriend(this);
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", email=" + email
				+ ", firstName=" + firstName + ", photoUrl=" + photoUrl + ", role=" + role + ", enabled=" + enabled
				+ ", createDate=" + createDate + ", updateDate=" + updateDate + ", address=" + address + "]";
	}
	
	

}
