package com.example.cricket.model;

public class ScoreBoardModel {

	private String battingTeamCode;

	private Integer teamScore;

	private Integer wickets;

	private String striker;

	private Integer strikerScore;

	private Integer ballsConsByStriker;

	private String nonStriker;

	private Integer nonStrikerScore;

	private Integer ballsConsByNonStriker;

	private String bowlingTeamCode;

	private Float overs;

	public String getBattingTeamCode() {
		return battingTeamCode;
	}

	public void setBattingTeamCode(String battingTeamCode) {
		this.battingTeamCode = battingTeamCode;
	}

	public Integer getTeamScore() {
		return teamScore;
	}

	public void setTeamScore(Integer teamScore) {
		this.teamScore = teamScore;
	}

	public Integer getWickets() {
		return wickets;
	}

	public void setWickets(Integer wickets) {
		this.wickets = wickets;
	}

	public String getStriker() {
		return striker;
	}

	public void setStriker(String striker) {
		this.striker = striker;
	}

	public String getNonStriker() {
		return nonStriker;
	}

	public void setNonStriker(String nonStriker) {
		this.nonStriker = nonStriker;
	}

	public Integer getStrikerScore() {
		return strikerScore;
	}

	public void setStrikerScore(Integer strikerScore) {
		this.strikerScore = strikerScore;
	}

	public Integer getNonStrikerScore() {
		return nonStrikerScore;
	}

	public void setNonStrikerScore(Integer nonStrikerScore) {
		this.nonStrikerScore = nonStrikerScore;
	}

	public Integer getBallsConsByStriker() {
		return ballsConsByStriker;
	}

	public void setBallsConsByStriker(Integer ballsConsByStriker) {
		this.ballsConsByStriker = ballsConsByStriker;
	}

	public Integer getBallsConsByNonStriker() {
		return ballsConsByNonStriker;
	}

	public void setBallsConsByNonStriker(Integer ballsConsByNonStriker) {
		this.ballsConsByNonStriker = ballsConsByNonStriker;
	}

	public String getBowlingTeamCode() {
		return bowlingTeamCode;
	}

	public void setBowlingTeamCode(String bowlingTeamCode) {
		this.bowlingTeamCode = bowlingTeamCode;
	}

	public Float getOvers() {
		return overs;
	}

	public void setOvers(Float overs) {
		this.overs = overs;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("=".repeat(135));
		builder.append("\n");
		builder.append(battingTeamCode);
		builder.append("  ");
		builder.append(teamScore);
		builder.append("-");
		builder.append(wickets);
		builder.append("\t");
		builder.append(striker);
		builder.append(" " + strikerScore);
		builder.append("(");
		builder.append(ballsConsByStriker);
		builder.append(")*");
		builder.append("\t".repeat(2));
		builder.append(nonStriker);
		builder.append(" " + nonStrikerScore);
		builder.append("(");
		builder.append(ballsConsByNonStriker);
		builder.append(")");
		builder.append("\t".repeat(8));

		builder.append(" Overs - ");
		builder.append(String.format("%.1f", overs));
		builder.append("(");
		builder.append(bowlingTeamCode);
		builder.append(")");
		builder.append("\n");
		builder.append("=".repeat(135));
		return builder.toString();
	}

}
