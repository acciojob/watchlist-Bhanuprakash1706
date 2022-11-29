package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.LinkedList;
import java.util.List;

@Service
@Component
public class MovieService {
    @Autowired
    MovieRepository movieRepository;
    public void addAllMovies(Movie movie){
        movieRepository.addMovieToDB(movie);
    }
    public void addAllDirectors(Director director){
        movieRepository.addDirectorToDB(director);
    }

    public void addAllMovieDirectorPair(String mName, String dName) { movieRepository.addMovieDirectorPairToDB(mName,dName);}

    public Movie getAllMoviesByName(String name) { return movieRepository.getMovieByNameFromDB(name);}

    public Director getAllDirectorsByName(String name) { return movieRepository.getDirectorByNameFromDB(name);}

    public List<String> findAllMovies() { return movieRepository.findAllMoviesFromDB();}

    public List<String> getAllMoviesByDirectorName(String dName) { return movieRepository.getMoviesByDirectorNameFromDB(dName);}

    public void deleteAllDirectorsByName(String dName) { movieRepository.deleteDirectorByNameFromDB(dName);}

    public void deleteAllDirectors() { movieRepository.deleteAllDirectorsFromDB();}
}
