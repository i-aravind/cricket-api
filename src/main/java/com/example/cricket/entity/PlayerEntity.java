package com.example.cricket.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "PLAYER")
public class PlayerEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PLAYER_DB_ID")
	private Integer playerDbId;

	@ManyToOne
	@JoinColumn(name="TEAM_DB_ID", updatable=false)
	private TeamEntity team;

	@Column(name = "PLAYER_NAME")
	private String playerName;

	public Integer getPlayerDbId() {
		return playerDbId;
	}

	public void setPlayerDbId(Integer playerDbId) {
		this.playerDbId = playerDbId;
	}

	public TeamEntity getTeam() {
		return team;
	}

	public void setTeam(TeamEntity team) {
		this.team = team;
	}

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PlayerEntity [playerDbId=");
		builder.append(playerDbId);
		builder.append(", team=");
		builder.append(team);
		builder.append(", playerName=");
		builder.append(playerName);
		builder.append("]");
		return builder.toString();
	}

}
