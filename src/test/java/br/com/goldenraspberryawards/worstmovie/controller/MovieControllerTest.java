package br.com.goldenraspberryawards.worstmovie.controller;

import java.io.File;
import java.io.FileInputStream;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
class MovieControllerTest {

    @Autowired
    private MockMvc mockMvc;

	@Test
	void getProducerWithBiggestConsecutiveGapTest() throws Exception {
        final File resourcesDirectory = new File("src/test/resources/csv/movies-test.csv");

        final Resource mockResource = Mockito.mock(Resource.class);
        final ResourceLoader resourceLoader = Mockito.mock(ResourceLoader.class);
        
        Mockito.when(resourceLoader.getResource(Mockito.anyString())).thenReturn(mockResource);
        Mockito.when(mockResource.getInputStream()).thenReturn(new FileInputStream(resourcesDirectory));

        mockMvc.perform(MockMvcRequestBuilders.get("/gap")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("producer").value("Craig Molina"))
            .andExpect(MockMvcResultMatchers.jsonPath("interval").value("19"))
            .andExpect(MockMvcResultMatchers.jsonPath("previousWin").value("1992"))
            .andExpect(MockMvcResultMatchers.jsonPath("followingWin").value("2011"));
	}
}
