package br.com.goldenraspberryawards.controller;

import org.springframework.beans.factory.annotation.Autowired;
import br.com.goldenraspberryawards.model.Movie;
import br.com.goldenraspberryawards.service.MovieService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Collection;

@RestController
public class MovieController {

    private final MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/pior-filmes")
    public Collection<Movie> getPiorFilmes() {
        return movieService.getPiorFilmes();
    }
}
