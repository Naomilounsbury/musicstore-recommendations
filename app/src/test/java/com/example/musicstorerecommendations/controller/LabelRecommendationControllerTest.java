package com.example.musicstorerecommendations.controller;

import static org.junit.Assert.*;
import com.example.musicstorerecommendations.repository.LabelRecommendationRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.musicstorerecommendations.models.LabelRecommendation;

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
@WebMvcTest(LabelRecommendationController.class)
public class LabelRecommendationControllerTest {
    @MockBean
    private LabelRecommendationRepository repo;

    private ObjectMapper mapper =new ObjectMapper();

    @Autowired
    MockMvc mockMvc;

    @Before
    public void setUp()throws Exception{
        setUpProduceServiceMock();
    }
    public void setUpProduceServiceMock(){
        LabelRecommendation orange =new LabelRecommendation(1,1,1,true);
        LabelRecommendation orangeWithoutId =new LabelRecommendation(1,1,true);
        List<LabelRecommendation> labelRecommendationList= Arrays.asList(orange);
        doReturn(labelRecommendationList).when(repo).findAll();
        doReturn(orange).when(repo).save(orangeWithoutId);

    }
    @Test
    public void getAllLabelRecommendationShouldReturnListAnd200()throws Exception{
        LabelRecommendation orange =new LabelRecommendation(1,1,1,true);
        List<LabelRecommendation> labelRecommendationList= Arrays.asList(orange);
        String expectedJsonValue =mapper.writeValueAsString(labelRecommendationList);
        mockMvc.perform(get("/labelRecommendation"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(expectedJsonValue));

    }
    @Test
    public void createLabelRecommendationShouldReturnNewLabelRecommendation()throws Exception{
        LabelRecommendation outputProduce=new LabelRecommendation(1,1,1,true);
        LabelRecommendation inputProduce= new LabelRecommendation(1,1,true);
        String outputProduceJson=mapper.writeValueAsString(outputProduce);
        String inputProduceJson = mapper.writeValueAsString(inputProduce);

        mockMvc.perform(post("/labelRecommendation")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(inputProduceJson))
                .andDo(print())
                .andExpect(status().isCreated())            // Assert
                .andExpect(content().json(outputProduceJson));  // Assert
    }
    @Test
    public void getOneLabelRecommendationShouldReturn()throws Exception{
        LabelRecommendation labelRecommendation=new LabelRecommendation(1,1,1,true);
        String expectedJsonValue=mapper.writeValueAsString(labelRecommendation);



        doReturn(Optional.of(labelRecommendation)).when(repo).findById(1);

        ResultActions result = mockMvc.perform(
                        get("/labelRecommendation/1"))
                .andExpect(status().isOk())
                .andExpect((content().json(expectedJsonValue))
                );
    }


    @Test
    public void shouldUpdateByIdAndReturn200StatusCode() throws Exception {
        LabelRecommendation labelRecommendation = new LabelRecommendation( 1,1,1,true);
        String expectedJsonValue=mapper.writeValueAsString(labelRecommendation);
        mockMvc.perform(
                        put("/labelRecommendation/1")
                                .content(expectedJsonValue)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk());

    }
    @Test
    public void shouldDeleteByIdAndReturn200StatusCode() throws Exception {
        LabelRecommendation labelRecommendation = new LabelRecommendation( 1,1,1,true);
        mockMvc.perform(delete("/labelRecommendation/1")).andExpect(status().isNoContent());
    }



}