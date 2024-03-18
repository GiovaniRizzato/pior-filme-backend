package br.com.goldenraspberryawards.worstmovie.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.goldenraspberryawards.worstmovie.model.ProducerMinMaxGap;
import br.com.goldenraspberryawards.worstmovie.service.MovieService;

@RestController
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping("/movies")
    public ResponseEntity<ProducerMinMaxGap> getMovies(@RequestParam String projection) {
        if(projection.equals("min-max-win-interval-for-producers")) {
            return ResponseEntity
                .status(HttpStatus.OK)                 
                .body(movieService.getBiggestProducerWinningGap());
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}
