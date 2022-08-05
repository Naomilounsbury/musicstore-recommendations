package com.example.musicstorerecommendations.controller;

import static org.junit.Assert.*;

import com.example.musicstorerecommendations.repository.ArtistRecommendationRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.musicstorerecommendations.models.ArtistRecommendation;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ArtistRecommendationController.class)
public class ArtistRecommendationControllerTest {
    @MockBean
    private ArtistRecommendationRepository repo;

    private ObjectMapper mapper =new ObjectMapper();

    @Autowired
    MockMvc mockMvc;

    @Before
    public void setUp()throws Exception{
        setUpProduceServiceMock();
    }
    public void setUpProduceServiceMock(){
        ArtistRecommendation orange =new ArtistRecommendation(1,1,1,true);
        ArtistRecommendation orangeWithoutId =new ArtistRecommendation(1,1,1,true);
        List<ArtistRecommendation> labelList= Arrays.asList(orange);
        doReturn(labelList).when(repo).findAll();
        doReturn(orange).when(repo).save(orangeWithoutId);

    }
    @Test
    public void getAllArtistRecommendationShouldReturnListAnd200()throws Exception{
        ArtistRecommendation orange =new ArtistRecommendation(1,1,1,true);
        List<ArtistRecommendation> artistRecommendationList= Arrays.asList(orange);
        String expectedJsonValue =mapper.writeValueAsString(artistRecommendationList);
        mockMvc.perform(get("/artistRecommendation"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(expectedJsonValue));

    }
    @Test
    public void createArtistRecommendationShouldReturnNewLabel()throws Exception{
        ArtistRecommendation outputArtistRecommendation=new ArtistRecommendation(1,1,1,true);
        ArtistRecommendation inputArtistRecommendation= new ArtistRecommendation(1,1,true);
        String outputArtistRecommendationJson=mapper.writeValueAsString(outputArtistRecommendation);
        String inputArtistRecommendationJson = mapper.writeValueAsString(inputArtistRecommendation);
        doReturn(outputArtistRecommendation).when(repo).save(inputArtistRecommendation);

        mockMvc.perform(post("/artistRecommendation")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(inputArtistRecommendationJson))
                .andDo(print())
                .andExpect(status().isCreated())            // Assert
                .andExpect(content().json(outputArtistRecommendationJson));  // Assert
    }
    @Test
    public void getOneArtistRecommendationShouldReturn()throws Exception{
        ArtistRecommendation artistRecommendation=new ArtistRecommendation(1,1,1,true);
        String expectedJsonValue=mapper.writeValueAsString(artistRecommendation);



        doReturn(Optional.of(artistRecommendation)).when(repo).findById(1);

        ResultActions result = mockMvc.perform(
                        get("/artistRecommendation/1"))
                .andExpect(status().isOk())
                .andExpect((content().json(expectedJsonValue))
                );
    };


    @Test
    public void shouldUpdateByIdAndReturn200StatusCode() throws Exception {
        ArtistRecommendation artistRecommendation = new ArtistRecommendation( 1,1,1,true);
        //ArtistRecommendation expectedValue =new ArtistRecommendation("orangey", "orange","orangeorange");
        String expectedJsonValue=mapper.writeValueAsString(artistRecommendation);
        mockMvc.perform(
                        put("/artistRecommendation/1")
                                .content(expectedJsonValue)
                                .contentType(MediaType.APPLICATION_JSON)

                )
                .andExpect(status().isOk());

    }
    @Test
    public void shouldDeleteByIdAndReturn200StatusCode() throws Exception {
        ArtistRecommendation artistRecommendation = new ArtistRecommendation( 1,1,1,true);
        mockMvc.perform(delete("/artistRecommendation/1")).andExpect(status().isNoContent());
    }




}