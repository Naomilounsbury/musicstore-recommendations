package com.example.musicstorerecommendations.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
@Table(name="track_recommendation")
public class TrackRecommendation {


        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name="track_recommendation_id")
        private Integer id;
        @NotNull(message="put in track id")
        @Column(name="track_id")
        private Integer trackId;
        @NotNull(message="put in user id")
        @Column(name="user_id")
        private Integer userId;
        @NotNull(message="put in number of likes")
        private Boolean liked;

        public TrackRecommendation() {
        }

        public TrackRecommendation(Integer trackId, Integer userId, Boolean liked) {
                this.trackId = trackId;
                this.userId = userId;
                this.liked = liked;
        }

        public TrackRecommendation(Integer id, Integer trackId, Integer userId, Boolean liked) {
                this.id = id;
                this.trackId = trackId;
                this.userId = userId;
                this.liked = liked;
        }

        public Integer getId() {
                return id;
        }

        public void setId(Integer id) {
                this.id = id;
        }

        public Integer getTrackId() {
                return trackId;
        }

        public void setTrackId(Integer trackId) {
                this.trackId = trackId;
        }

        public Integer getUserId() {
                return userId;
        }

        public void setUserId(Integer userId) {
                this.userId = userId;
        }

        public Boolean getLiked() {
                return liked;
        }

        public void setLiked(Boolean liked) {
                this.liked = liked;
        }

        @Override
        public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                TrackRecommendation that = (TrackRecommendation) o;
                return Objects.equals(id, that.id) && Objects.equals(trackId, that.trackId) && Objects.equals(userId, that.userId) && Objects.equals(liked, that.liked);
        }

        @Override
        public int hashCode() {
                return Objects.hash(id, trackId, userId, liked);
        }

        @Override
        public String toString() {
                return "TrackRecommendation{" +
                        "id=" + id +
                        ", trackId=" + trackId +
                        ", userId=" + userId +
                        ", liked=" + liked +
                        '}';
        }
}
