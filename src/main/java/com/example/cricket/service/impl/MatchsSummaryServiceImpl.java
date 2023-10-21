package com.example.cricket.service.impl;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.cricket.entity.MatchsSummaryEntity;
import com.example.cricket.model.MatchsSummaryModel;
import com.example.cricket.repository.MatchsSummaryRepository;
import com.example.cricket.service.MatchsSummaryService;

@Component
public class MatchsSummaryServiceImpl implements MatchsSummaryService	{

	private MatchsSummaryRepository matchsSummaryRepository;
	
	@Autowired
	public MatchsSummaryServiceImpl(MatchsSummaryRepository matchsSummaryRepository) {
		this.matchsSummaryRepository = matchsSummaryRepository;
	}
	
	@Override
	public MatchsSummaryModel recordMatchsSummary(MatchsSummaryModel matchsSummaryModel) {
		MatchsSummaryEntity matchsSummaryEntity = new MatchsSummaryEntity();
		matchsSummaryEntity.setMatchsSummaryDbId(matchsSummaryModel.getMatchsSummaryDbId());
		matchsSummaryEntity.setMatchs(matchsSummaryModel.getMatchs());
		matchsSummaryEntity.setTeam(matchsSummaryModel.getTeam());
		matchsSummaryEntity.setPlayer(matchsSummaryModel.getPlayer());
		matchsSummaryEntity.setScore(matchsSummaryModel.getScore());
		matchsSummaryEntity.setBallsConsumed(matchsSummaryModel.getBallsConsumed());
		
		MatchsSummaryEntity responseEntity = matchsSummaryRepository.save(matchsSummaryEntity);
		
		MatchsSummaryModel responseModel = new MatchsSummaryModel();
		responseModel.setMatchsSummaryDbId(responseEntity.getMatchsSummaryDbId());
		responseModel.setMatchs(responseEntity.getMatchs());
		responseModel.setTeam(responseEntity.getTeam());
		responseModel.setPlayer(responseEntity.getPlayer());
		responseModel.setScore(responseEntity.getScore());
		responseModel.setBallsConsumed(responseEntity.getBallsConsumed());
		return responseModel;
	}

	@Override
	public Collection<MatchsSummaryModel> getScoreCard(Integer matchsDbId) {
		Collection<MatchsSummaryModel> responseModels = new ArrayList<MatchsSummaryModel>();
		
		Collection<MatchsSummaryEntity> responseEntitys = matchsSummaryRepository.getScoreCard(matchsDbId);
		
		for(MatchsSummaryEntity responseEntity :responseEntitys) {
			MatchsSummaryModel responseModel = new MatchsSummaryModel();
			responseModel.setMatchsSummaryDbId(responseEntity.getMatchsSummaryDbId());
			responseModel.setMatchs(responseEntity.getMatchs());
			responseModel.setTeam(responseEntity.getTeam());
			responseModel.setPlayer(responseEntity.getPlayer());
			responseModel.setScore(responseEntity.getScore());
			responseModel.setBallsConsumed(responseEntity.getBallsConsumed());
			responseModels.add(responseModel);
		}
		return responseModels;
	}
	
}
