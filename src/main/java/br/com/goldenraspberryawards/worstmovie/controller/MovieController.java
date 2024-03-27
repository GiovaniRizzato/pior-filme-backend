package br.com.goldenraspberryawards.worstmovie.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.goldenraspberryawards.worstmovie.model.DTO.DTO;
import br.com.goldenraspberryawards.worstmovie.model.DTO.MovieList;
import br.com.goldenraspberryawards.worstmovie.service.MovieService;

@RestController
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping("/movies")
    public ResponseEntity<DTO> getMovies(@RequestParam(required = false) String projection) {
        if (projection == null) {
            return ResponseEntity
                .status(HttpStatus.OK)                 
                .body(new MovieList(movieService.getAllMovies()));
        } else {
            switch (projection) {
                case "min-max-win-interval-for-producers": {
                    return ResponseEntity
                        .status(HttpStatus.OK)                 
                        .body(movieService.getBiggestProducerWinningGap());
                }
                default: {
                    return ResponseEntity.badRequest().build();
                }  
            }
        }        
    }
}
