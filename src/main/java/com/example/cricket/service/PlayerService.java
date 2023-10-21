package com.example.cricket.service;

import java.util.Collection;

import org.springframework.stereotype.Service;

import com.example.cricket.model.PlayerModel;

@Service
public interface PlayerService {

	public Collection<PlayerModel> getPlayersByTeamCode(String teamCode);
}
