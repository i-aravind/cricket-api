package com.example.cricket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.cricket.entity.TeamEntity;

@Repository
public interface TeamRepository extends JpaRepository<TeamEntity, Integer>{

	
	@Query("SELECT t FROM TeamEntity t WHERE t.teamCode = :teamCode")
	TeamEntity getTeamByCode(String teamCode);
}
