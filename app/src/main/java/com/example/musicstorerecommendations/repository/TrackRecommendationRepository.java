package com.example.musicstorerecommendations.repository;

import com.example.musicstorerecommendations.models.TrackRecommendation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrackRecommendationRepository extends JpaRepository<TrackRecommendation,Integer> {
}
