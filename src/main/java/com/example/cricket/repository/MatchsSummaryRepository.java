package com.example.cricket.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.cricket.entity.MatchsSummaryEntity;

@Repository
public interface MatchsSummaryRepository extends JpaRepository<MatchsSummaryEntity, Long>{

	@Query("SELECT s FROM MatchsSummaryEntity s WHERE s.matchs.matchsDbId = :matchsDbId")
	Collection<MatchsSummaryEntity> getScoreCard(Integer matchsDbId);
	
}
