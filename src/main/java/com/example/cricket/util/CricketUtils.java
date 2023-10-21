package com.example.cricket.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.example.cricket.model.MatchsModel;
import com.example.cricket.model.MatchsSummaryModel;
import com.example.cricket.model.PlayerModel;
import com.example.cricket.model.ScoreBoardModel;
import com.example.cricket.model.TeamModel;

/**
 * 
 * This is a util class for cricket related classes.
 *
 */
@Component
public class CricketUtils {

	@Autowired
	private RestTemplate restTemplate;

	private static List<String> TEAM_LIST = new ArrayList<>(Arrays.asList("IND", "PAK"));

	private static final List<? super Object> POSSIBLE_DELIVERY_OUTCOME = new ArrayList<>(
			Arrays.asList(0, 1, 2, 3, 4, 5, 6, 'W'));

	public void showScoreCard(String battingTeamCode, Integer teamScore, Integer wickets, String striker,
			Integer strikerScore, Integer ballsConsBystriker, String nonStriker, Integer nonStrikerScore,
			Integer ballsConsByNonStriker, String bowlingTeamCode, Float overs) {
		ScoreBoardModel boardModel = new ScoreBoardModel();
		boardModel.setBattingTeamCode(battingTeamCode);
		boardModel.setTeamScore(teamScore);
		boardModel.setWickets(wickets);
		boardModel.setStriker(striker);
		boardModel.setStrikerScore(strikerScore);
		boardModel.setBallsConsByStriker(ballsConsBystriker);
		boardModel.setNonStriker(nonStriker);
		boardModel.setNonStrikerScore(nonStrikerScore);
		boardModel.setBallsConsByNonStriker(ballsConsByNonStriker);
		boardModel.setBowlingTeamCode(bowlingTeamCode);
		boardModel.setOvers(overs);
		System.out.println(boardModel);
	}

	public void playMatch() {
		System.out.println("Welcome to ICC Men's T20 World Cup 2024!");
		System.out.println("Select 2 teams to start the match");

		// Setup TEAM1
		System.out.println("AVAILABLE TEAMS : " + TEAM_LIST);
		System.out.print("Select Team 1 :");
		Scanner sc = new Scanner(System.in);
		String team1 = sc.nextLine();
		ResponseEntity<TeamModel> response = restTemplate.getForEntity("http://localhost:8081/team/" + team1,
				TeamModel.class);
		TeamModel teamModel1 = response.getBody();
		TEAM_LIST.remove(team1);
		System.out.println("Team1 " + teamModel1.getTeamName() + " [OK]");

		// Setup TEAM2
		System.out.println("AVAILABLE TEAMS : " + TEAM_LIST);
		System.out.print("Select Team 2 :");
		String team2 = sc.nextLine();
		response = restTemplate.getForEntity("http://localhost:8081/team/" + team2, TeamModel.class);
		TeamModel teamModel2 = response.getBody();
		System.out.println("Team2 " + teamModel2.getTeamName() + " [OK]");

		// START MATCH
		Integer matchDbId = null;
		System.out.println("STARTING MATCH...");
		MatchsModel matchsModel = new MatchsModel();
		matchsModel.setTeam1(teamModel1);
		matchsModel.setTeam2(teamModel2);
		MatchsModel responseMatchsModel = restTemplate.postForObject("http://localhost:8081/startMatch", matchsModel,
				MatchsModel.class);
		matchDbId = responseMatchsModel.getMatchsDbId();
		System.out.println(Objects.nonNull(responseMatchsModel) ? "Match started!"
				: "Match not started. Some technical issue happened!!!");

		// Toss
		TeamModel battingTeamModel = getBattingTeam(new ArrayList<TeamModel>(Arrays.asList(teamModel1, teamModel2)));
		System.out.println(battingTeamModel.getTeamCode() + " won the toss and elected to bat first");

		TeamModel bowlingTeamModel = teamModel1.equals(battingTeamModel) ? teamModel2 : teamModel1;

		// Batsmen on crease
		Float totalOvers = 20f;
		Integer ballsPerOver = 6;
		String result = "";

		Integer teamScore = 0;
		Integer wickets = 0;
		Integer ballsCompleted = 0;
		Float oversCompleted = 0f;
		Integer target = 0;
		Integer milestone = 50;

		for (TeamModel playingTeamModel : new ArrayList<TeamModel>(Arrays.asList(battingTeamModel, bowlingTeamModel))) {
			// ScoreCard
			Collection<MatchsSummaryModel> matchsSummaryModels = initializeScoreCard(restTemplate, responseMatchsModel,
					playingTeamModel.getTeamCode());
			Iterator<MatchsSummaryModel> lineUp = matchsSummaryModels.iterator();
			MatchsSummaryModel striker = lineUp.next();
			MatchsSummaryModel nonStriker = lineUp.next();
			while (wickets < 10 && oversCompleted < totalOvers) {
				ArrayList<TeamModel> bowlingTeamModels = new ArrayList<TeamModel>(
						Arrays.asList(battingTeamModel, bowlingTeamModel));
				bowlingTeamModels.remove(playingTeamModel);
				showScoreCard(playingTeamModel.getTeamCode(), teamScore, wickets, striker.getPlayer().getPlayerName(),
						striker.getScore(), striker.getBallsConsumed(), nonStriker.getPlayer().getPlayerName(),
						nonStriker.getScore(), nonStriker.getBallsConsumed(), bowlingTeamModels.get(0).getTeamCode(),
						oversCompleted);
				if (ballsCompleted != 0 && ballsCompleted % ballsPerOver == 0) {
					MatchsSummaryModel temp;
					temp = striker;
					striker = nonStriker;
					nonStriker = temp;
				}
				System.out.print("Press 5 to bowl next ball:");
				String play = sc.nextLine();
				Object ballResult = bowl();
				if (ballResult instanceof Number) {
					teamScore += (Integer) ballResult;
					striker.setScore(striker.getScore() + (Integer) ballResult);
					striker.setBallsConsumed(striker.getBallsConsumed() + 1);
					if (striker.getScore() > milestone) {
						System.out.println(striker.getPlayer().getPlayerName() + " scored " + milestone + " runs!!!");
					}
					milestone = ((striker.getScore() / 50) + 1) * 50;
					if (target != 0 && teamScore > target) {
						break;
					}
					if ((Integer) ballResult % 2 == 1) {
						MatchsSummaryModel temp;
						temp = striker;
						striker = nonStriker;
						nonStriker = temp;
					}
				} else {
					wickets++;
					striker.setBallsConsumed(striker.getBallsConsumed() + 1);
					restTemplate.postForObject("http://localhost:8081/postSummary", striker, MatchsSummaryModel.class);
					restTemplate.postForObject("http://localhost:8081/postSummary", nonStriker,
							MatchsSummaryModel.class);
					if (wickets == 10) {
						break;
					}
					striker = lineUp.next();
				}

				ballsCompleted++;
				oversCompleted = ((ballsCompleted / ballsPerOver) + (ballsCompleted % ballsPerOver) / 10.0f) * 1.0f;
			}
			if (target == 0) {
				System.out.println(battingTeamModel.getTeamName() + " scored " + teamScore + " runs");
				System.out.println(bowlingTeamModel.getTeamName() + " needs " + (teamScore + 1) + " runs from "
						+ totalOvers + " overs");
				target = teamScore + 1;
			} else if (teamScore > target) {
				result = bowlingTeamModel.getTeamName() + " beats " + battingTeamModel.getTeamName() + " by "
						+ (10 - wickets) + " wickets!!!";
			} else if (teamScore == target - 1) {
				System.out.println("Match drawn");
			} else {
				result = battingTeamModel.getTeamName() + " beats " + bowlingTeamModel.getTeamName() + " by "
						+ (target - teamScore - 1) + " runs!!!";
			}

			teamScore = 0;
			wickets = 0;
			ballsCompleted = 0;
			oversCompleted = 0f;

		}
		ResponseEntity<Collection<MatchsSummaryModel>> scorecard = restTemplate.exchange(
				"http://localhost:8081/getScoreCard/" + matchDbId, HttpMethod.GET, null,
				new ParameterizedTypeReference<Collection<MatchsSummaryModel>>() {
				});
		System.out.println("*".repeat(135));
		System.out.println("\t".repeat(5) + "SCORECARD");
		System.out.println("*".repeat(135));
		System.out.println("TEAM"+ "\t".repeat(3) +"NAME" + "\t".repeat(3) + "RUNS" + "\t".repeat(3) + "S/R");
		scorecard.getBody().stream().forEach(e -> System.out.println(e));
		System.out.println("*".repeat(135));
		System.out.println(result);
		System.out.println("*".repeat(135));
		sc.close();
	}

	private Collection<MatchsSummaryModel> initializeScoreCard(RestTemplate restTemplate,
			MatchsModel responseMatchsModel, String team) {
		Collection<MatchsSummaryModel> matchsSummaryModels = new ArrayList<MatchsSummaryModel>();
		ResponseEntity<Collection<PlayerModel>> players = restTemplate.exchange(
				"http://localhost:8081/player?teamCode=" + team, HttpMethod.GET, null,
				new ParameterizedTypeReference<Collection<PlayerModel>>() {
				});
		for (PlayerModel player : players.getBody()) {
			MatchsSummaryModel matchsSummaryModel = new MatchsSummaryModel();
			matchsSummaryModel.setMatchs(responseMatchsModel);
			matchsSummaryModel.setPlayer(player);
			matchsSummaryModel.setTeam(player.getTeam());
			matchsSummaryModel.setScore(0);
			matchsSummaryModel.setBallsConsumed(0);
			MatchsSummaryModel responseSummaryModel = restTemplate.postForObject("http://localhost:8081/postSummary",
					matchsSummaryModel, MatchsSummaryModel.class);
			matchsSummaryModels.add(responseSummaryModel);
		}
		return matchsSummaryModels;
	}

	private TeamModel getBattingTeam(List<TeamModel> toss) {
		Random rand = new Random();
		return toss.get(rand.nextInt(toss.size()));
	}

	private Object bowl() {
		Random rand = new Random();
		Object ballResult = POSSIBLE_DELIVERY_OUTCOME.get(rand.nextInt(POSSIBLE_DELIVERY_OUTCOME.size()));
		if (ballResult instanceof Integer) {
			switch ((Integer) ballResult) {
			case 0:
				System.out.println("It's a dot ball");
				break;
			case 1:
				System.out.println("It's a Single, batsman off the mark");
				break;
			case 2:
				System.out.println("It's a double, batsman gets back to strike");
				break;
			case 3:
				System.out.println("It's a triple, good running between the wickets");
				break;
			case 4:
				System.out.println("It's a boundary, one bounce over the rope");
				break;
			case 5:
				System.out.println("It's five runs, poor fielding effort by the bowling team");
				break;
			case 6:
				System.out.println("It's a six!!!");
				break;
			default:
				System.out.println("Some technical error");
			}
		} else {
			System.out.println("It's a wicket!!!");
		}
		return ballResult;
	}
}
