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
@Table(name = "MATCHS")
public class MatchsEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MATCHS_DB_ID")
	private Integer matchsDbId;

	@ManyToOne
    @JoinColumn(name="TEAM1_DB_ID", updatable=false)
	private TeamEntity team1;

	@ManyToOne
    @JoinColumn(name="TEAM2_DB_ID", updatable=false)
	private TeamEntity team2;

	public Integer getMatchsDbId() {
		return matchsDbId;
	}

	public void setMatchsDbId(Integer matchsDbId) {
		this.matchsDbId = matchsDbId;
	}

	public TeamEntity getTeam1() {
		return team1;
	}

	public void setTeam1(TeamEntity team1) {
		this.team1 = team1;
	}

	public TeamEntity getTeam2() {
		return team2;
	}

	public void setTeam2(TeamEntity team2) {
		this.team2 = team2;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MatchsEntity [matchsDbId=");
		builder.append(matchsDbId);
		builder.append(", team1=");
		builder.append(team1);
		builder.append(", team2=");
		builder.append(team2);
		builder.append("]");
		return builder.toString();
	}

}
