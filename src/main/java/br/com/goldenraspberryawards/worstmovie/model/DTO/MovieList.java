package br.com.goldenraspberryawards.worstmovie.model.DTO;

import java.util.Collection;

import br.com.goldenraspberryawards.worstmovie.model.Movie;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieList implements DTO{
    private Collection<Movie> movies; 
}