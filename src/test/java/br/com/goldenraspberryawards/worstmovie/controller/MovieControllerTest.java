package br.com.goldenraspberryawards.worstmovie.controller;


import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
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
        mockMvc.perform(MockMvcRequestBuilders.get("/movies?projection=min-max-win-interval-for-producers")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("min").isArray())
            .andExpect(MockMvcResultMatchers.jsonPath("min[0].producer").value("Menahem Golan"))
            .andExpect(MockMvcResultMatchers.jsonPath("min[0].interval").value("1"))
            .andExpect(MockMvcResultMatchers.jsonPath("min[0].previousWin").value("1986"))
            .andExpect(MockMvcResultMatchers.jsonPath("min[0].followingWin").value("1987"))
            .andExpect(MockMvcResultMatchers.jsonPath("min[1].producer").value("Joel Silver"))
            .andExpect(MockMvcResultMatchers.jsonPath("min[1].interval").value("1"))
            .andExpect(MockMvcResultMatchers.jsonPath("min[1].previousWin").value("1990"))
            .andExpect(MockMvcResultMatchers.jsonPath("min[1].followingWin").value("1991"))
            .andExpect(MockMvcResultMatchers.jsonPath("max").isArray())
            .andExpect(MockMvcResultMatchers.jsonPath("max[0].producer").value("Matthew Vaughn"))
            .andExpect(MockMvcResultMatchers.jsonPath("max[0].interval").value("13"))
            .andExpect(MockMvcResultMatchers.jsonPath("max[0].previousWin").value("2002"))
            .andExpect(MockMvcResultMatchers.jsonPath("max[0].followingWin").value("2015"));
	}

    @Test
	void getErrorWhileTryingInvalidParameterTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/movies?projection=wrong-type")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isBadRequest());
	}
}
