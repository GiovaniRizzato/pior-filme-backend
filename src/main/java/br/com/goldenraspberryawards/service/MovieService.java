package br.com.goldenraspberryawards.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.goldenraspberryawards.model.Movie;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;

@Service
public class MovieService {

	private final Collection<Movie> movies = new ArrayList<>();

    @Transactional
    public void loadMoviesFromCSV(InputStream inputStream) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                Movie movie = new Movie();
                movie.setTitle(data[0]);
                movie.setYear(Integer.parseInt(data[1]));
                movie.setCategory(data[2]);
                this.movies.add(movie);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Collection<Movie> getPiorFilmes() {
        return this.movies;
    }
}
