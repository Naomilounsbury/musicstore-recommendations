package com.example.musicstorerecommendations.controller;

import static org.junit.Assert.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.musicstorerecommendations.models.AlbumRecommendation;
import com.example.musicstorerecommendations.repository.AlbumRecommendationRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(AlbumRecommendationController.class)
@AutoConfigureMockMvc(addFilters = false)
public class AlbumRecommendationControllerTest {
    @MockBean
    private AlbumRecommendationRepository repo;

    @Autowired
    private ObjectMapper mapper;


    @Autowired
    MockMvc mockMvc;

    //    @Before
//    public void setUp()throws Exception{
//        setUpProduceServiceMock();
//    }
    public void setUpProduceServiceMock(){
        AlbumRecommendation orange =new AlbumRecommendation(1,1,1,true);
        AlbumRecommendation orangeWithoutId =new AlbumRecommendation(1,1,true);
        List<AlbumRecommendation> albumRecommendationList= Arrays.asList(orange);
        doReturn(albumRecommendationList).when(repo).findAll();
        doReturn(orange).when(repo).save(orangeWithoutId);

    }
    @Test
    public void getAllAlbumRecommendationsShouldReturnListAnd200()throws Exception{
        AlbumRecommendation orange =new AlbumRecommendation(1,1,1,true);
        List<AlbumRecommendation> albumRecommendationList= Arrays.asList(orange);
        String expectedJsonValue =mapper.writeValueAsString(albumRecommendationList);
        doReturn(albumRecommendationList).when(repo).findAll();
        mockMvc.perform(MockMvcRequestBuilders.get("/albumRecommendation"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(expectedJsonValue));

    }

    @Test
    public void createAlbumRecommendationShouldReturnNewLabel()throws Exception{
        AlbumRecommendation outputAlbumRecommendation=new AlbumRecommendation(1,1,1,true);
        AlbumRecommendation inputAlbumRecommendation= new AlbumRecommendation(1,1,true);
        String outputAlbumRecommendationJson=mapper.writeValueAsString(outputAlbumRecommendation);
        String inputAlbumRecommendationJson = mapper.writeValueAsString(inputAlbumRecommendation);
        when(repo.save(inputAlbumRecommendation)).thenReturn(outputAlbumRecommendation);

        mockMvc.perform(MockMvcRequestBuilders.post("/albumRecommendation")
                        .content(inputAlbumRecommendationJson)
                        .contentType(MediaType.APPLICATION_JSON))

                .andDo(print())
                .andExpect(status().isCreated())            // Assert
                .andExpect(content().json(outputAlbumRecommendationJson));  // Assert
    }
    @Test
    public void getOneArtistShouldReturn()throws Exception{
        AlbumRecommendation albumRecommendation=new AlbumRecommendation(1,1,1,true);
        String expectedJsonValue=mapper.writeValueAsString(albumRecommendation);

        doReturn(Optional.of(albumRecommendation)).when(repo).findById(111);

        ResultActions result = mockMvc.perform(
                        MockMvcRequestBuilders.get("/albumRecommendation/111"))
                .andExpect(status().isOk())
                .andExpect((content().json(expectedJsonValue))
                );
    };


    @Test
    public void shouldUpdateByIdAndReturn200StatusCode() throws Exception {
        AlbumRecommendation albumRecommendation = new AlbumRecommendation(1,1,1,true);

        String expectedJsonValue=mapper.writeValueAsString(albumRecommendation);
        mockMvc.perform(
                        put("/albumRecommendation/1")
                                .content(expectedJsonValue)
                                .contentType(MediaType.APPLICATION_JSON)

                )
                .andExpect(status().isOk());

    }
    @Test
    public void shouldDeleteByIdAndReturn200StatusCode() throws Exception {
        AlbumRecommendation albumRecommendation = new AlbumRecommendation( 1,1,1,true);
        mockMvc.perform(MockMvcRequestBuilders.delete("/albumRecommendation/1")).andExpect(status().isNoContent());
    }



}