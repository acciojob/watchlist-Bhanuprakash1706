package com.driver;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.websocket.OnClose;
import java.security.KeyPair;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

@Repository
@Component
public class MovieRepository {
   // HashMap<Movie,Director> ListOfPair=new HashMap<>();
    HashMap<String,Movie> ListOfMoviesInDB=new HashMap<>();
    HashMap<Director,List<String>> ListOfDirectorsInDB=new HashMap<>();
    //Adding movie to DB
    public void addMovieToDB(Movie movie){
        ListOfMoviesInDB.put(movie.getName(), movie);
    }
    //Adding director to DB
    public void addDirectorToDB(Director director){
        List<String> movieListOfDirectors=new LinkedList<>();
        ListOfDirectorsInDB.put(director,movieListOfDirectors);
    }
    //Pair movie and director
    public void addMovieDirectorPairToDB(String movieName,String directorName){
        for(Director director:ListOfDirectorsInDB.keySet()){
            if(Objects.equals(director.getName(), directorName)){
                List<String> temp;
                temp=ListOfDirectorsInDB.get(director);
                temp.add(movieName);
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

    public List<String> findAllMoviesFromDB() {
        LinkedList<String> listOfMovies=new LinkedList<>();
        for(Movie movie:ListOfMoviesInDB.values()){
            listOfMovies.add(movie.getName());
        }
        return listOfMovies;
    }

    public List<String> getMoviesByDirectorNameFromDB(String dName) {
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
                List<String> temp;
                temp=ListOfDirectorsInDB.get(director);
                for(String movie:temp){
                    ListOfMoviesInDB.remove(movie);
                }
                ListOfDirectorsInDB.remove(director);
                break;
            }
        }
    }

    public void deleteAllDirectorsFromDB() {
        for(Director director:ListOfDirectorsInDB.keySet()){
            List<String> temp;
            temp=ListOfDirectorsInDB.get(director);
            for(String movie:temp){
                ListOfMoviesInDB.remove(movie);
            }
        }
        ListOfDirectorsInDB.clear();
    }
    //
}
