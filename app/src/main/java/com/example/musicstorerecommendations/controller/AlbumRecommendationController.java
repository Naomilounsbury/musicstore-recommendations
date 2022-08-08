package com.example.musicstorerecommendations.controller;

import com.example.musicstorerecommendations.models.AlbumRecommendation;
import com.example.musicstorerecommendations.repository.AlbumRecommendationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;


import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

import java.time.LocalDate;
@RestController
public class AlbumRecommendationController {
    @Autowired
    private AlbumRecommendationRepository repo;


    @RequestMapping(value = "/albumRecommendation", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public AlbumRecommendation createAlbumRecommendation(@Valid @RequestBody AlbumRecommendation album) {
        return repo.save(album);
    }

    @RequestMapping(value = "/albumRecommendation", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public List<AlbumRecommendation> getAllAlbum(){
        return repo.findAll();
    }

    @RequestMapping(value = "/albumRecommendation/{id}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public AlbumRecommendation findOneAlbumRecommendation(@PathVariable Integer id){
        Optional<AlbumRecommendation> album = repo.findById(id);

        if (album.isPresent() == false) {

            throw new IllegalArgumentException("invalid id");

        } else {

            return album.get();
        }
    }
    @RequestMapping(value = "/albumRecommendation/{id}", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK)
    public AlbumRecommendation updateAlbumRecommendation(@Valid @RequestBody AlbumRecommendation album, @PathVariable Integer id){
        if (album.getId() == null) {
            album.setId(id);
        } else if (album.getId() != id) {
            throw new IllegalArgumentException("Ids don't match.");
        }
        return repo.save(album);

    }
    @RequestMapping(value = "/albumRecommendation/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOneAlbumRecommendation(@PathVariable Integer id) {
        repo.deleteById(id);

    }
}




