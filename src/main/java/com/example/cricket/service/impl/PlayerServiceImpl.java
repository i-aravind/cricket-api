package com.example.cricket.service.impl;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.cricket.entity.PlayerEntity;
import com.example.cricket.model.PlayerModel;
import com.example.cricket.repository.PlayerRepository;
import com.example.cricket.service.PlayerService;

@Component
public class PlayerServiceImpl implements PlayerService{
	
	private PlayerRepository playerRepository;
	
	@Autowired
	public PlayerServiceImpl(PlayerRepository playerRepository) {
		this.playerRepository = playerRepository;
	}
	
	
	public Collection<PlayerModel> getPlayersByTeamCode(String teamCode){
		Collection<PlayerModel> playerModels = new ArrayList<>();
		for(PlayerEntity playerEntity : playerRepository.getPlayersByTeamCode(teamCode)) {
			PlayerModel playerModel = new PlayerModel();
			playerModel.setPlayerDbId(playerEntity.getPlayerDbId());
			playerModel.setPlayerName(playerEntity.getPlayerName());
			playerModel.setTeam(playerEntity.getTeam());
			playerModels.add(playerModel);
		}
		return playerModels;
	}
}
