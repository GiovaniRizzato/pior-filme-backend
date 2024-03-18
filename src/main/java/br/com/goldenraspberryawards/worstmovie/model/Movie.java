package br.com.goldenraspberryawards.worstmovie.model;

import java.util.Collection;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Movie implements Comparable<Movie>{
    private int year;
    private String title;
    private Collection<String> studios;
    private Collection<String> producers;
    private boolean isWinner;

    @Override
    public int compareTo(Movie other) {
        return this.year - other.year;
    }
}