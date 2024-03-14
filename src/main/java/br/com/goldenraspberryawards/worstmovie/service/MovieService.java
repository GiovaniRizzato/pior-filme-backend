package br.com.goldenraspberryawards.worstmovie.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import br.com.goldenraspberryawards.worstmovie.model.Movie;
import br.com.goldenraspberryawards.worstmovie.model.ProducerWinningGap;
import jakarta.annotation.PostConstruct;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class MovieService {
	private final List<Movie> movies = new ArrayList<>();

    @Value("classpath:csv/movies.csv")
    private Resource resource;

    public ProducerWinningGap getBiggestProducerWinningGap() {  
        return this.searchBiggestProducerWinningGap();
    }

    @PostConstruct
    public void init(){
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

    private ProducerWinningGap searchBiggestProducerWinningGap(){
        int biggestConsecutiveWinGaps = 0;
        final ProducerWinningGap producerWinningGap = new ProducerWinningGap();
        for(int i=0; i<(this.movies.size() - 1); i++){
            final Movie previousWinEntry = this.movies.get(i);
            for(int j=(i+1); j<this.movies.size(); j++){
                final Movie followingWinEntry = this.movies.get(j);
                if(previousWinEntry.getProducer().equals(followingWinEntry.getProducer())){
                    final int yearGap = followingWinEntry.getYear() - previousWinEntry.getYear();
                    if(yearGap > biggestConsecutiveWinGaps){
                        biggestConsecutiveWinGaps = yearGap;
                        producerWinningGap.setProducer(previousWinEntry.getProducer());
                        producerWinningGap.setInterval(yearGap);
                        producerWinningGap.setPreviousWin(previousWinEntry.getYear());
                        producerWinningGap.setFollowingWin(followingWinEntry.getYear());                        
                    }

                    break;
                }
            }
        }

        return producerWinningGap;
    }
}
