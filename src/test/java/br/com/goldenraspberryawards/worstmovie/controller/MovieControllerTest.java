package br.com.goldenraspberryawards.worstmovie.controller;

import java.io.File;
import java.io.FileInputStream;

import javax.imageio.IIOException;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
class MovieControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private Resource mockResource;

	@Test
	void getProducerWithBiggestConsecutiveGapTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/gap")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("producer").value("Craig Molina"))
            .andExpect(MockMvcResultMatchers.jsonPath("interval").value("19"))
            .andExpect(MockMvcResultMatchers.jsonPath("previousWin").value("1992"))
            .andExpect(MockMvcResultMatchers.jsonPath("followingWin").value("2011"));
	}
}
