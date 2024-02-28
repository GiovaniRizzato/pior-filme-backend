package br.com.goldenraspberryawards.model;

import lombok.Data;

@Data
public class Movie {
    private Long id;
    private String title;
    private int year;
    private String category;
}