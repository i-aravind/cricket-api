package com.example.cricket.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.cricket.entity.PlayerEntity;

@Repository
public interface PlayerRepository extends JpaRepository<PlayerEntity, Integer> {
	
	@Query("SELECT p FROM PlayerEntity p WHERE p.team.teamCode = :teamCode")
	Collection<PlayerEntity> getPlayersByTeamCode(String teamCode);

}
