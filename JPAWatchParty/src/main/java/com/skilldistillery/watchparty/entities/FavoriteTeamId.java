package com.skilldistillery.watchparty.entities;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class FavoriteTeamId implements Serializable{
		private static final long serialVersionUID = 1L;
	
		@Column(name = "user_id")
		private int userId;
		
		@Column(name = "team_id")
		private int teamId;

		public FavoriteTeamId() {
			super();
		}

		public FavoriteTeamId(int userId, int teamId) {
			super();
			this.userId = userId;
			this.teamId = teamId;
		}

		public int getUserId() {
			return userId;
		}

		public void setUserId(int userId) {
			this.userId = userId;
		}

		public int getTeamId() {
			return teamId;
		}

		public void setTeamId(int teamId) {
			this.teamId = teamId;
		}

		public static long getSerialversionuid() {
			return serialVersionUID;
		}

		@Override
		public int hashCode() {
			return Objects.hash(teamId, userId);
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			FavoriteTeamId other = (FavoriteTeamId) obj;
			return teamId == other.teamId && userId == other.userId;
		}

		@Override
		public String toString() {
			return "FavoriteTeamId [userId=" + userId + ", teamId=" + teamId + "]";
		}
		
		

}
