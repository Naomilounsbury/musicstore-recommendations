package com.example.musicstorerecommendations.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
@Table(name="album_recommendation")
public class AlbumRecommendation {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name="album_recommendation_id")
        private Integer id;
        @NotNull(message="put in album id")
        @Column(name="album_id")
        private Integer albumId;
        @NotNull(message="put in user id")
        @Column(name="user_id")
        private Integer userId;
        @NotNull(message="put in number of likes")
        private Boolean liked;

        public AlbumRecommendation() {
        }

        public AlbumRecommendation(Integer albumId, Integer userId, Boolean liked) {
                this.albumId = albumId;
                this.userId = userId;
                this.liked = liked;
        }

        public AlbumRecommendation(Integer id, Integer albumId, Integer userId, Boolean liked) {
                this.id = id;
                this.albumId = albumId;
                this.userId = userId;
                this.liked = liked;
        }

        public Integer getId() {
                return id;
        }

        public void setId(Integer id) {
                this.id = id;
        }

        public Integer getAlbumId() {
                return albumId;
        }

        public void setAlbumId(Integer albumId) {
                this.albumId = albumId;
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
                AlbumRecommendation that = (AlbumRecommendation) o;
                return Objects.equals(id, that.id) && Objects.equals(albumId, that.albumId) && Objects.equals(userId, that.userId) && Objects.equals(liked, that.liked);
        }

        @Override
        public int hashCode() {
                return Objects.hash(id, albumId, userId, liked);
        }

        @Override
        public String toString() {
                return "AlbumRecommendation{" +
                        "id=" + id +
                        ", albumId=" + albumId +
                        ", userId=" + userId +
                        ", liked=" + liked +
                        '}';
        }
}
