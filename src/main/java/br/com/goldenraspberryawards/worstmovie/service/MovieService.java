package br.com.goldenraspberryawards.worstmovie.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.goldenraspberryawards.worstmovie.model.Movie;

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
                this.movies.add(new Movie(data[0], data[1], Integer.parseInt(data[2])));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Collection<Movie> getPiorFilmes() {
        return this.movies;
    }
}
