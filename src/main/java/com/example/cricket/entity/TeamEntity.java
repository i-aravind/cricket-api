package com.example.cricket.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "TEAM")
public class TeamEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "TEAM_DB_ID")
	private Integer teamDbId;

	@Column(name = "TEAM_CODE")
	private String teamCode;

	@Column(name = "TEAM_NAME")
	private String teamName;

	public Integer getTeamDbId() {
		return teamDbId;
	}

	public void setTeamDbId(Integer teamDbId) {
		this.teamDbId = teamDbId;
	}

	public String getTeamCode() {
		return teamCode;
	}

	public void setTeamCode(String teamCode) {
		this.teamCode = teamCode;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TeamEntity [teamDbId=");
		builder.append(teamDbId);
		builder.append(", teamCode=");
		builder.append(teamCode);
		builder.append(", teamName=");
		builder.append(teamName);
		builder.append("]");
		return builder.toString();
	}

}
