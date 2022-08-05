package com.example.musicstorerecommendations.repository;

import com.example.musicstorerecommendations.models.AlbumRecommendation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlbumRecommendationRepository extends JpaRepository<AlbumRecommendation,Integer> {
}
