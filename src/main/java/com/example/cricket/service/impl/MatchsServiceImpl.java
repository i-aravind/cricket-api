package com.example.cricket.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.cricket.entity.MatchsEntity;
import com.example.cricket.model.MatchsModel;
import com.example.cricket.repository.MatchsRepository;
import com.example.cricket.service.MatchsService;

@Component
public class MatchsServiceImpl implements MatchsService {

	private MatchsRepository matchsRepository;

	@Autowired
	public MatchsServiceImpl(MatchsRepository matchsRepository) {
		this.matchsRepository = matchsRepository;
	}

	public MatchsModel startMatch(MatchsModel matchsModel) {
		MatchsEntity matchsEntity = new MatchsEntity();
		matchsEntity.setTeam1(matchsModel.getTeam1());
		matchsEntity.setTeam2(matchsModel.getTeam2());
		MatchsEntity response = matchsRepository.save(matchsEntity);
		MatchsModel responseModel = new MatchsModel();
		responseModel.setMatchsDbId(response.getMatchsDbId());
		responseModel.setTeam1(response.getTeam1());
		responseModel.setTeam2(response.getTeam2());
		return responseModel;
	}
}
