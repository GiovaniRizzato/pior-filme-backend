package br.com.goldenraspberryawards.worstmovie.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import br.com.goldenraspberryawards.worstmovie.model.Movie;
import br.com.goldenraspberryawards.worstmovie.model.ProducerWinningGap;
import br.com.goldenraspberryawards.worstmovie.model.DTO.ProducerMinMaxGap;
import br.com.goldenraspberryawards.worstmovie.repository.MovieRepository;
import jakarta.annotation.PostConstruct;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Value("classpath:csv/movielist.csv")
    private Resource resource;

    public ProducerMinMaxGap getBiggestProducerWinningGap () {  
        return this.searchProducerWinningGaps();
    }

    @PostConstruct
    public void init (){
        try (BufferedReader br = new BufferedReader(new InputStreamReader(resource.getInputStream()))) {
            String line;
            br.readLine();//Skip the header
            while ((line = br.readLine()) != null) {
                final Movie movie = new Movie();
                String[] data = line.split(";");
                movie.setYear(Integer.parseInt(data[0]));
                movie.setTitle(data[1]);
                movie.setStudios(Set.of((data[2].trim().split("\\s*(,|and)\\s*"))));
                movie.setProducers(Set.of((data[3].trim().split("\\s*(,|and)\\s*"))));
                if(data.length > 4){
                    movie.setWinner(data[4].toLowerCase().equals("yes"));
                } else {
                    movie.setWinner(false);
                }   

                this.movieRepository.save(movie);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Collection<Movie> getAllMovies() {
        return this.movieRepository.findAll();
    }

    private ProducerMinMaxGap searchProducerWinningGaps (){
        int smallestConsecutiveWinGap = Integer.MAX_VALUE;
        int biggestConsecutiveWinGap = 0;
        final List<ProducerWinningGap> biggestProducerWinningGap = new LinkedList<>();
        final List<ProducerWinningGap> smallestProducerWinningGap = new LinkedList<>();
        final List<Movie> movies = this.movieRepository.findAllByOrderByYearAsc();
        for (int i=0; i<(movies.size() - 1); i++){     
            final Movie previousWinEntry = movies.get(i);
            if (!previousWinEntry.getWinner()){
                continue;
            }
            for (String producer: previousWinEntry.getProducers()){
                for (int j=(i+1); j<movies.size(); j++){
                    final Movie followingWinEntry = movies.get(j);
                    if (!followingWinEntry.getWinner()){
                        continue;
                    }
                    if (followingWinEntry.getProducers().contains(producer)) {
                        final int yearGap = followingWinEntry.getYear() - previousWinEntry.getYear();
                        if (yearGap >= biggestConsecutiveWinGap){
                            if (yearGap > biggestConsecutiveWinGap){
                                biggestConsecutiveWinGap = yearGap;
                                biggestProducerWinningGap.clear();
                            }

                            final ProducerWinningGap newProducer = new ProducerWinningGap();
                            newProducer.setProducer(producer);
                            newProducer.setInterval(yearGap);
                            newProducer.setPreviousWin(previousWinEntry.getYear());
                            newProducer.setFollowingWin(followingWinEntry.getYear());
                            biggestProducerWinningGap.add(newProducer);
                        }
    
                        if (yearGap <= smallestConsecutiveWinGap){
                            if (yearGap < smallestConsecutiveWinGap){
                                smallestConsecutiveWinGap = yearGap;
                                smallestProducerWinningGap.clear();
                            }
                            
                            final ProducerWinningGap newProducer = new ProducerWinningGap();
                            newProducer.setProducer(producer);
                            newProducer.setInterval(yearGap);
                            newProducer.setPreviousWin(previousWinEntry.getYear());
                            newProducer.setFollowingWin(followingWinEntry.getYear());
                            smallestProducerWinningGap.add(newProducer);                    
                        }
    
                        break;
                    }
                }
            }
        }

        return new ProducerMinMaxGap(smallestProducerWinningGap, biggestProducerWinningGap);
    }
}
