package br.com.goldenraspberryawards.worstmovie.model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Movie{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)     
    private Integer id;

    private Integer year;
    private String title;
    private Boolean winner;

    @ElementCollection
    private Set<String> studios = new HashSet<>();
    @ElementCollection
    private Set<String> producers = new HashSet<>();
}