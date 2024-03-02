package br.com.goldenraspberryawards.worstmovie.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.goldenraspberryawards.worstmovie.service.MovieService;
import br.com.goldenraspberryawards.worstmovie.service.MovieService.ProducerWinningGap;

@RestController
public class MovieResource {

    @Autowired
    private MovieService movieService;

    @GetMapping("/gap")
    public ProducerWinningGap getProducerWithBiggestConsecutiveGap() {
        return movieService.getPiorFilmes();
    }
}