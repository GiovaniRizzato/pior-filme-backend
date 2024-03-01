package br.com.goldenraspberryawards.worstmovie.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.goldenraspberryawards.worstmovie.model.Movie;
import br.com.goldenraspberryawards.worstmovie.service.MovieService;

import java.util.Collection;

@RestController
public class MovieController {

    private final MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/movies")
    public Collection<Movie> getPiorFilmes() {
        return movieService.getPiorFilmes();
    }
}
