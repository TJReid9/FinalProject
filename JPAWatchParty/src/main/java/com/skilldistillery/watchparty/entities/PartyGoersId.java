package com.skilldistillery.watchparty.entities;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class PartyGoersId implements Serializable{
		private static final long serialVersionUID = 1L;
	
		@Column(name = "user_id")
		private int userId;
		
		@Column(name = "party_id")
		private int partyId;

		public PartyGoersId() {
			super();
		}

		public PartyGoersId(int userId, int partyId) {
			super();
			this.userId = userId;
			this.partyId = partyId;
		}

		public int getUserId() {
			return userId;
		}

		public void setUserId(int userId) {
			this.userId = userId;
		}

		public int getTeamId() {
			return partyId;
		}

		public void setTeamId(int partyId) {
			this.partyId = partyId;
		}

		public static long getSerialversionuid() {
			return serialVersionUID;
		}

		@Override
		public int hashCode() {
			return Objects.hash(partyId, userId);
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			PartyGoersId other = (PartyGoersId) obj;
			return partyId == other.partyId && userId == other.userId;
		}

		@Override
		public String toString() {
			return "PartyGoersId [userId=" + userId + ", partyId=" + partyId + "]";
		}
		
		

}
