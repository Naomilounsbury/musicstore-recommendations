package com.example.musicstorerecommendations.repository;

import com.example.musicstorerecommendations.models.LabelRecommendation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LabelRecommendationRepository extends JpaRepository<LabelRecommendation,Integer> {
}
