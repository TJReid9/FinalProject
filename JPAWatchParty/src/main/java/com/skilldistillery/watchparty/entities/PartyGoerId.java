package com.skilldistillery.watchparty.entities;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class PartyGoerId implements Serializable{
		private static final long serialVersionUID = 1L;
	
		@Column(name = "user_id")
		private int userId;
		
		@Column(name = "party_id")
		private int partyId;

		public PartyGoerId() {
			super();
		}

		public PartyGoerId(int userId, int partyId) {
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

		public int getPartyId() {
			return partyId;
		}

		public void setPartyId(int partyId) {
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
			PartyGoerId other = (PartyGoerId) obj;
			return partyId == other.partyId && userId == other.userId;
		}

		@Override
		public String toString() {
			return "PartyGoersId [userId=" + userId + ", partyId=" + partyId + "]";
		}
		
		

}
