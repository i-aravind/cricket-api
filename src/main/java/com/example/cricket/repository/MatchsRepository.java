package com.example.cricket.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.cricket.entity.MatchsEntity;

public interface MatchsRepository extends JpaRepository<MatchsEntity, Integer>{

}
