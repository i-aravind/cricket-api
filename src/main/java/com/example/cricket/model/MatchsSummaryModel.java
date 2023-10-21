package com.example.cricket.model;

import com.example.cricket.entity.MatchsSummaryEntity;

public class MatchsSummaryModel extends MatchsSummaryEntity{

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("-".repeat(135));
		builder.append("\n");
		builder.append(getTeam().getTeamCode());
		builder.append("\t".repeat(3));
		builder.append(getPlayer().getPlayerName());
		builder.append("\t".repeat(3));
		builder.append(getScore());
		builder.append("(");
		builder.append(getBallsConsumed());
		builder.append(")");
		builder.append("\t".repeat(3));
		builder.append((getBallsConsumed()!=0)?getScore()*100.0f/getBallsConsumed() : 0);
		builder.append("\n");
		builder.append("-".repeat(135));
		return builder.toString();
	}

	
}
