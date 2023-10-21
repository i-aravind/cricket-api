package com.example.cricket.service;

import org.springframework.stereotype.Service;

import com.example.cricket.entity.TeamEntity;
import com.example.cricket.model.TeamModel;

@Service
public interface TeamService {
	
	public TeamEntity getTeamEntityById(Integer teamDbId);
	
	public TeamModel getTeamEntityByCode(String teamCode);
}
