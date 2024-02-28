package br.com.goldenraspberryawards.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Movie {
    private String title;
    private String producer;
    private int year;
}