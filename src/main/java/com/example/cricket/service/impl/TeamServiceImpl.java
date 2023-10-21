package com.example.cricket.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.cricket.entity.TeamEntity;
import com.example.cricket.model.TeamModel;
import com.example.cricket.repository.TeamRepository;
import com.example.cricket.service.TeamService;

@Component
public class TeamServiceImpl implements TeamService{
	
	private TeamRepository teamRepository;
	
	@Autowired
	public TeamServiceImpl(TeamRepository teamRepository) {
		this.teamRepository = teamRepository;
	}

	@Override
	public TeamEntity getTeamEntityById(Integer teamDbId) {
		return teamRepository.getReferenceById(teamDbId);
	}
	
	public TeamModel getTeamEntityByCode(String teamCode) {
		TeamEntity teamEntity = teamRepository.getTeamByCode(teamCode);
		TeamModel teamModel = new TeamModel();
		teamModel.setTeamDbId(teamEntity.getTeamDbId());
		teamModel.setTeamCode(teamEntity.getTeamCode());
		teamModel.setTeamName(teamEntity.getTeamName());
		return teamModel;
	}

}
