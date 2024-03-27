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
	void getMovieListTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/movies")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("movies").isArray())
            .andExpect(MockMvcResultMatchers.jsonPath("movies[0].title").value("Hercules"))
            .andExpect(MockMvcResultMatchers.jsonPath("movies[0].year").value("1983"))
            .andExpect(MockMvcResultMatchers.jsonPath("movies[0].studios").isArray())
            .andExpect(MockMvcResultMatchers.jsonPath("movies[0].studios[0]").value("MGM"))
            .andExpect(MockMvcResultMatchers.jsonPath("movies[0].studios[1]").value("United Artists"))
            .andExpect(MockMvcResultMatchers.jsonPath("movies[0].studios[2]").value("Cannon Films"))
            .andExpect(MockMvcResultMatchers.jsonPath("movies[0].producers").isArray())
            .andExpect(MockMvcResultMatchers.jsonPath("movies[0].producers[0]").value("Menahem Golan"))
            .andExpect(MockMvcResultMatchers.jsonPath("movies[0].producers[1]").value("Yoram Globus"))
            .andExpect(MockMvcResultMatchers.jsonPath("movies[0].winner").value("false"))
            .andExpect(MockMvcResultMatchers.jsonPath("movies[1].title").value("Tough Guys Don't Dance"))
            .andExpect(MockMvcResultMatchers.jsonPath("movies[1].year").value("1987"))
            .andExpect(MockMvcResultMatchers.jsonPath("movies[1].studios").isArray())
            .andExpect(MockMvcResultMatchers.jsonPath("movies[1].studios[0]").value("Cannon Films"))
            .andExpect(MockMvcResultMatchers.jsonPath("movies[1].producers").isArray())
            .andExpect(MockMvcResultMatchers.jsonPath("movies[1].producers[0]").value("Menahem Golan"))
            .andExpect(MockMvcResultMatchers.jsonPath("movies[1].producers[1]").value("Yoram Globus"))
            .andExpect(MockMvcResultMatchers.jsonPath("movies[1].winner").value("true"));
	}

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
