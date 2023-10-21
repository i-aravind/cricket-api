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
@Table(name = "MATCHS_SUMMARY")
public class MatchsSummaryEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MATCHS_SUMMARY_DB_ID")
	private Long matchsSummaryDbId;

	@ManyToOne
	@JoinColumn(name = "MATCHS_DB_ID", updatable=false)
	private MatchsEntity matchs;

	@ManyToOne
	@JoinColumn(name = "TEAM_DB_ID", updatable=false)
	private TeamEntity team;

	@ManyToOne
	@JoinColumn(name = "PLAYER_DB_ID", updatable=false)
	private PlayerEntity player;

	@Column(name = "SCORE")
	private Integer score;

	@Column(name = "BALLS_CONSUMED")
	private Integer ballsConsumed;

	public Long getMatchsSummaryDbId() {
		return matchsSummaryDbId;
	}

	public void setMatchsSummaryDbId(Long matchsSummaryDbId) {
		this.matchsSummaryDbId = matchsSummaryDbId;
	}

	public MatchsEntity getMatchs() {
		return matchs;
	}

	public void setMatchs(MatchsEntity matchs) {
		this.matchs = matchs;
	}

	public TeamEntity getTeam() {
		return team;
	}

	public void setTeam(TeamEntity team) {
		this.team = team;
	}

	public PlayerEntity getPlayer() {
		return player;
	}

	public void setPlayer(PlayerEntity player) {
		this.player = player;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public Integer getBallsConsumed() {
		return ballsConsumed;
	}

	public void setBallsConsumed(Integer ballsConsumed) {
		this.ballsConsumed = ballsConsumed;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MatchsSummaryEntity [matchsSummaryDbId=");
		builder.append(matchsSummaryDbId);
		builder.append(", matchs=");
		builder.append(matchs);
		builder.append(", team=");
		builder.append(team);
		builder.append(", player=");
		builder.append(player);
		builder.append(", score=");
		builder.append(score);
		builder.append(", ballsConsumed=");
		builder.append(ballsConsumed);
		builder.append("]");
		return builder.toString();
	}

}
