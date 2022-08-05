package com.example.musicstorerecommendations.controller;

import static org.junit.Assert.*;



import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.musicstorerecommendations.models.TrackRecommendation;
import com.example.musicstorerecommendations.repository.TrackRecommendationRepository;
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
@WebMvcTest(TrackRecommendationController.class)
@AutoConfigureMockMvc(addFilters = false)
public class TrackRecommendationControllerTest {
    @MockBean
    private TrackRecommendationRepository repo;

    @Autowired
    private ObjectMapper mapper;


    @Autowired
    MockMvc mockMvc;

    //        @Before
//    public void setUp()throws Exception{
//        setUpProduceServiceMock();
//    }
    public void setUpProduceServiceMock(){
        TrackRecommendation orange =new TrackRecommendation(1,1,1, true);
        TrackRecommendation orangeWithoutId =new TrackRecommendation(1,1,1,true);
        List<TrackRecommendation> trackRecommendationList= Arrays.asList(orange);
        doReturn(trackRecommendationList).when(repo).findAll();
        doReturn(orange).when(repo).save(orangeWithoutId);

    }
    @Test
    public void getAllTrackRecommendationsShouldReturnListAnd200()throws Exception{
        TrackRecommendation orange =new TrackRecommendation(1,1,1,true);
        List<TrackRecommendation> trackRecommendationList= Arrays.asList(orange);
        String expectedJsonValue =mapper.writeValueAsString(trackRecommendationList);
        doReturn(trackRecommendationList).when(repo).findAll();
        mockMvc.perform(MockMvcRequestBuilders.get("/trackRecommendation"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(expectedJsonValue));

    }


    @Test
    public void createTrackRecommendationShouldReturnNewLabel()throws Exception{
        TrackRecommendation outputTrackRecommendation=new TrackRecommendation(1,1,1,true);
        TrackRecommendation inputTrackRecommendation= new TrackRecommendation(1,1,true);
        String outputTrackRecommendationJson=mapper.writeValueAsString(outputTrackRecommendation);
        String inputTrackRecommendationJson = mapper.writeValueAsString(inputTrackRecommendation);
        when(repo.save(inputTrackRecommendation)).thenReturn(outputTrackRecommendation);
        //doReturn(outputTrackRecommendation).when(repo).save(inputTrackRecommendation);
        mockMvc.perform(post("/trackRecommendation")
                        .content(inputTrackRecommendationJson)
                        .contentType(MediaType.APPLICATION_JSON))

                .andDo(print())
                .andExpect(status().isCreated())            // Assert
                .andExpect(content().json(outputTrackRecommendationJson));  // Assert
    }
    @Test
    public void getOneArtistShouldReturn()throws Exception{
        TrackRecommendation artist=new TrackRecommendation(1,1,1,true);
        String expectedJsonValue=mapper.writeValueAsString(artist);

        doReturn(Optional.of(artist)).when(repo).findById(1);

        ResultActions result = mockMvc.perform(
                        MockMvcRequestBuilders.get("/trackRecommendation/1"))
                .andExpect(status().isOk())
                .andExpect((content().json(expectedJsonValue))
                );
    };


    @Test
    public void shouldUpdateByIdAndReturn200StatusCode() throws Exception {
        TrackRecommendation artist = new TrackRecommendation( 1,1,1,true);
        //Artist expectedValue =new Artist("orangey", "orange","orangeorange");
        String expectedJsonValue=mapper.writeValueAsString(artist);
        mockMvc.perform(
                        put("/trackRecommendation/1")
                                .content(expectedJsonValue)
                                .contentType(MediaType.APPLICATION_JSON)

                )
                .andExpect(status().isOk());

    }
    @Test
    public void shouldDeleteByIdAndReturn200StatusCode() throws Exception {
        TrackRecommendation artist = new TrackRecommendation( 1,1,1,true);
        mockMvc.perform(MockMvcRequestBuilders.delete("/trackRecommendation/1")).andExpect(status().isNoContent());
    }



}