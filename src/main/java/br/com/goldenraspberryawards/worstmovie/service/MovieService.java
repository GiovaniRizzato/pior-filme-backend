package br.com.goldenraspberryawards.worstmovie.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import br.com.goldenraspberryawards.worstmovie.model.Movie;
import jakarta.annotation.PostConstruct;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class MovieService {

	private final List<Movie> movies = new ArrayList<>();

    @Autowired
    private ResourceLoader resourceLoader;

    public ReturnClass getPiorFilmes() {  
        return this.getBiggestGap();
    }

    @PostConstruct
    public void init(){
        final Resource resource = this.resourceLoader.getResource("classpath:csv/movies.csv");
        try (BufferedReader br = new BufferedReader(new InputStreamReader(resource.getInputStream()))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                this.movies.add(new Movie(data[0], data[1], Integer.parseInt(data[2])));
            }
            Collections.sort(this.movies);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public class ReturnClass{
        public String producer;
        public int interval;
        public int previousWin;
        public int followingWin;
    }

    private ReturnClass getBiggestGap(){
        int biggestConsecutiveWinGaps = 0;
        final ReturnClass returnClass = new ReturnClass();
        for(int i=0; i<(this.movies.size() - 1); i++){
            final Movie previousWinEntry = this.movies.get(i);
            for(int j=(i+1); j<this.movies.size(); j++){
                final Movie followingWinEntry = this.movies.get(j);
                if(previousWinEntry.getProducer().equals(followingWinEntry.getProducer())){
                    final int yearGap = followingWinEntry.getYear() - previousWinEntry.getYear();
                    if(yearGap > biggestConsecutiveWinGaps){
                        returnClass.producer = previousWinEntry.getProducer();
                        returnClass.interval = yearGap;
                        returnClass.previousWin = previousWinEntry.getYear();
                        returnClass.followingWin = followingWinEntry.getYear();                        
                    }

                    break;
                }
            }
        }

        return returnClass;
    }
}