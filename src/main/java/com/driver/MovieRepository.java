package com.driver;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.websocket.OnClose;
import java.security.KeyPair;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Objects;

@Repository
@Component
public class MovieRepository {
   // HashMap<Movie,Director> ListOfPair=new HashMap<>();
    HashMap<String,Movie> ListOfMoviesInDB=new HashMap<>();
    HashMap<Director,LinkedList<Movie>> ListOfDirectorsInDB=new HashMap<>();
    //Adding movie to DB
    public void addMovieToDB(Movie movie){
        ListOfMoviesInDB.put(movie.getName(), movie);
    }
    //Adding director to DB
    public void addDirectorToDB(Director director){
        LinkedList<Movie> movieListOfDirectors=new LinkedList<>();
        ListOfDirectorsInDB.put(director,movieListOfDirectors);
    }
    //Pair movie and director
    public void addMovieDirectorPairToDB(String movieName,String directorName){
        for(Director director:ListOfDirectorsInDB.keySet()){
            if(Objects.equals(director.getName(), directorName)){
                LinkedList<Movie> temp;
                temp=ListOfDirectorsInDB.get(director);
                temp.add(ListOfMoviesInDB.get(movieName));
                ListOfDirectorsInDB.put(director,temp);
                break;
            }
        }

    }

    public Movie getMovieByNameFromDB(String name) {
        return ListOfMoviesInDB.get(name);
    }
    public Director getDirectorByNameFromDB(String name) {
        for(Director director:ListOfDirectorsInDB.keySet()){
            if(Objects.equals(director.getName(), name)){
                return director;
            }
        }
        return null;
    }

    public LinkedList<Movie> findAllMoviesFromDB() {
        LinkedList<Movie> listOfMovies=new LinkedList<>();
        for(Movie movie:ListOfMoviesInDB.values()){
            listOfMovies.add(movie);
        }
        return listOfMovies;
    }

    public LinkedList<Movie> getMoviesByDirectorNameFromDB(String dName) {
    for(Director director:ListOfDirectorsInDB.keySet()){
        if(Objects.equals(director.getName(),dName)){
            return ListOfDirectorsInDB.get(director);
        }
    }
    return null;
    }

    public void deleteDirectorByNameFromDB(String dName) {
        for(Director director:ListOfDirectorsInDB.keySet()){
            if(Objects.equals(director.getName(),dName)){
                LinkedList<Movie> temp;
                temp=ListOfDirectorsInDB.get(director);
                for(Movie movie:temp){
                    ListOfMoviesInDB.remove(movie.getName());
                }
                ListOfDirectorsInDB.remove(director);
                break;
            }
        }
    }

    public void deleteAllDirectorsFromDB() {
        for(Director director:ListOfDirectorsInDB.keySet()){
            LinkedList<Movie> temp;
            temp=ListOfDirectorsInDB.get(director);
            for(Movie movie:temp){
                ListOfMoviesInDB.remove(movie.getName());
            }
        }
        ListOfDirectorsInDB.clear();
    }
    //
}
