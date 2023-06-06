package com.allocine.allocine_api.dao;

import com.allocine.allocine_api.model.Movie;
import com.allocine.allocine_api.model.Session;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class MovieDao {
    private static List<Movie> movies = new ArrayList<>();

    public void addMovie(Movie movie) { movies.add(movie); }

    public List<Movie> getAllMovies() {
        return movies;
    }

    public Movie getMovieById(int id) {
        for (Movie movie : movies) {
            if (movie.getId() == id) {
                return movie;
            }
        }
        return null;
    }
    public List<Movie> getMoviesByCity(String city){
        List<Movie> moviesByCity = new ArrayList<>();
        for (Movie movie : movies) {
            List<Session> sessions = movie.getSessions();
            for (Session session : sessions) {
                if (session.getCity().equals(city)) {
                    moviesByCity.add(movie);
                }
            }
        }
        return moviesByCity;

    }

    public List<Movie> getMoviesByCinema(String cinema) {
        List<Movie> moviesByCinema = new ArrayList<>();
        for (Movie movie : movies) {
            List<Session> sessions = movie.getSessions();
            for (Session session : sessions) {
                if (session.getCinemaName().equals(cinema)) {
                    moviesByCinema.add(movie);
                    break;
                }
            }
        }
        return moviesByCinema;
    }

    public void updateMovie(Movie updatedMovie) {
        for (Movie movie : movies) {
            if (movie.getId() == updatedMovie.getId()) {
                movie.setTitle(updatedMovie.getTitle());
                movie.setDirector(updatedMovie.getDirector());
                // Update other fields as needed
                break;
            }
        }
    }

    public void deleteMovie(int id) {
        movies.removeIf(movie -> movie.getId() == id);
    }
}

