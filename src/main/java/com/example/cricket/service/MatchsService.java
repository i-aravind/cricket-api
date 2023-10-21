package com.example.cricket.service;

import org.springframework.stereotype.Service;

import com.example.cricket.model.MatchsModel;

@Service
public interface MatchsService {
	
	public MatchsModel startMatch(MatchsModel matchsModel);

}
