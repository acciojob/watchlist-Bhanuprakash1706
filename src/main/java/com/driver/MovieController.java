package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;

@RestController
public class MovieController {
    @Autowired
    MovieService movieService;

    //Add a movie
    @PostMapping("/movies/add-movie")
    public ResponseEntity<String> addMovie(@RequestBody() Movie movie){
        movieService.addAllMovies(movie);
        return new ResponseEntity("Added successfully",HttpStatus.CREATED);
    }
    //Add a director
    @PostMapping("/movies/add-director")
    public ResponseEntity<String> addDirector(@RequestBody() Director director){
        movieService.addAllDirectors(director);
        return new ResponseEntity("Added successfully",HttpStatus.CREATED);
    }
    //Add pair of movie and director
    @PutMapping("/movies/add-movie-director-pair")
    public ResponseEntity addMovieDirectorPair(@RequestParam("mn") String mName,@RequestParam("dn") String dName){
        movieService.addAllMovieDirectorPair(mName,dName);
        return new ResponseEntity(HttpStatus.CREATED);
    }
    @GetMapping("/movies/get-movie-by-name/{name}")
    public ResponseEntity<Movie>  getMovieByName(@PathVariable("name") String name){
        return new ResponseEntity(movieService.getAllMoviesByName(name),HttpStatus.ACCEPTED);
    }
    @GetMapping("/movies/get-director-by-name/{name}")
    public ResponseEntity<Director> getDirectorByName(@PathVariable("name") String name){
        return new ResponseEntity(movieService.getAllDirectorsByName(name), HttpStatus.ACCEPTED);
    }
    @GetMapping("/movies/get-movies-by-director-name/{director}")
    public ResponseEntity<LinkedList<Movie>> getMoviesByDirectorName(@PathVariable("director") String dName){
        return new ResponseEntity(movieService.getAllMoviesByDirectorName(dName),HttpStatus.ACCEPTED);
    }
    @GetMapping("/movies/get-all-movies")
    public ResponseEntity<LinkedList<Movie>> findAllMovies(){
        return new ResponseEntity(movieService.findAllMovies(),HttpStatus.ACCEPTED);
    }
    @DeleteMapping("/movies/delete-director-by-name")
    public ResponseEntity deleteDirectorByName(@RequestParam("dName") String dName){
        movieService.deleteAllDirectorsByName(dName);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }
    @DeleteMapping("/movies/delete-all-directors")
    public ResponseEntity<String> deleteAllDirectors(){
        movieService.deleteAllDirectors();
        return new ResponseEntity("Deleted",HttpStatus.ACCEPTED);
    }
}
