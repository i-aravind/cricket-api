package com.example.cricket.controller;

import java.util.Collection;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.cricket.model.MatchsModel;
import com.example.cricket.model.MatchsSummaryModel;
import com.example.cricket.model.PlayerModel;
import com.example.cricket.model.TeamModel;
import com.example.cricket.service.MatchsService;
import com.example.cricket.service.MatchsSummaryService;
import com.example.cricket.service.PlayerService;
import com.example.cricket.service.TeamService;
import com.example.cricket.util.CricketUtils;

@RestController
public class MatchController {
	
	private TeamService teamService;
	
	private PlayerService playerService;
	
	private MatchsService matchsService;
	
	private MatchsSummaryService matchsSummaryService;
	
	private CricketUtils cricketUtils;
	
	@Autowired
	public MatchController(TeamService teamService, PlayerService playerService, MatchsService matchsService, MatchsSummaryService matchsSummaryService, CricketUtils cricketUtils) {
		this.teamService = teamService;
		this.playerService = playerService;
		this.matchsService = matchsService;
		this.matchsSummaryService = matchsSummaryService;
		this.cricketUtils = cricketUtils;
	}
	
	@GetMapping(value = "/team/{teamCode}")
	public TeamModel getTeamByCode(@PathVariable String teamCode) {
		return teamService.getTeamEntityByCode(teamCode);
	}
	
	@GetMapping(value = "/player")
	public Collection<PlayerModel> getPlayersByTeamId(@RequestParam Map<String, String> request) {
		return playerService.getPlayersByTeamCode(request.get("teamCode"));
	}
	
	@PostMapping(value = "/startMatch")
	public MatchsModel startMatch(@RequestBody MatchsModel matchsModel) {
		return matchsService.startMatch(matchsModel);
	}
	
	@PostMapping(value = "/postSummary")
	public MatchsSummaryModel postSummary(@RequestBody MatchsSummaryModel matchsSummaryModel) {
		return matchsSummaryService.recordMatchsSummary(matchsSummaryModel);
	}
	
	@GetMapping(value = "/getScoreCard/{matchsDbId}")
	public Collection<MatchsSummaryModel> getScoreCard(@PathVariable Integer matchsDbId) {
		return matchsSummaryService.getScoreCard(matchsDbId);
	}

	@PutMapping(value = "/playMatch")
	public void playMatch() {
		cricketUtils.playMatch();
	}
	
}
