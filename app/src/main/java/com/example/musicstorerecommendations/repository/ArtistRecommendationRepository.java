package com.example.musicstorerecommendations.repository;

import com.example.musicstorerecommendations.models.ArtistRecommendation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtistRecommendationRepository extends JpaRepository<ArtistRecommendation, Integer> {
}
