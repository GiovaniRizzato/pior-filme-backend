package br.com.goldenraspberryawards.worstmovie.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.goldenraspberryawards.worstmovie.model.Movie;
import br.com.goldenraspberryawards.worstmovie.service.MovieService;
import br.com.goldenraspberryawards.worstmovie.service.MovieService.ReturnClass;

import java.util.Collection;

@RestController
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping("/movies")
    public ReturnClass getPiorFilmes() {
        return movieService.getPiorFilmes();
    }
}
