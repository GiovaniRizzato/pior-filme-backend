package br.com.goldenraspberryawards.worstmovie.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Movie implements Comparable<Movie>{
    private String title;
    private String producer;
    private int year;

    @Override
    public int compareTo(Movie other) {
        return this.year - other.year;
    }
}