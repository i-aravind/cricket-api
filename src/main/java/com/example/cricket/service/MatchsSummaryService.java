package com.example.cricket.service;

import java.util.Collection;

import org.springframework.stereotype.Service;

import com.example.cricket.model.MatchsSummaryModel;

@Service
public interface MatchsSummaryService {

	public MatchsSummaryModel recordMatchsSummary(MatchsSummaryModel matchsSummaryModel);
	
	public Collection<MatchsSummaryModel> getScoreCard(Integer matchsDbId);
}
