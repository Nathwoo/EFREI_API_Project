package com.allocine.allocine_api.client;

import com.allocine.allocine_api.model.Movie;
import com.allocine.allocine_api.model.Session;
import org.glassfish.jersey.client.ClientConfig;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Tester {
    public static void main(String[] args) {
        // Create a JAX-RS client instance
        ClientConfig config = new ClientConfig();
        Client client = ClientBuilder.newClient(config);
        WebTarget service = client.target("http://localhost:8080/ALLOCINE_API_war_exploded/api");

        // Test POST route to add a new movie
        System.out.println("Testing POST /movies");
        Movie newMovie = new Movie(3, "Movie 3", 150, "English",
                Arrays.asList("English", "French"), "Director 3",
                Arrays.asList("Actor 5", "Actor 6"), 18,
                new Date(), Arrays.asList(new Session(new Date(), "Cinema5")));

        Response postResponse = service.path("movies").path("post")
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.json(newMovie));

        if (postResponse.getStatus() == Response.Status.CREATED.getStatusCode()) {
            System.out.println("New movie added successfully");
        } else {
            System.out.println("Failed to add new movie");
        }
        System.out.println();

        // Test GET route to retrieve all movies
        System.out.println("Testing GET /movies");
        List<Movie> allMovies = service.path("movies").path("get")
                .request(MediaType.APPLICATION_JSON)
                .get(new GenericType<List<Movie>>() {});
        allMovies.forEach(movie -> System.out.println(movie.getTitle()));
        System.out.println();

        // Test GET route to retrieve a specific movie by ID
        int movieId = 1; // Specify the ID of the movie to retrieve
        System.out.println("Testing GET /movies/" + movieId);
        Movie movieById = service.path("movies").path("get").path(String.valueOf(movieId))
                .request(MediaType.APPLICATION_JSON)
                .get(Movie.class);
        if (movieById != null) {
            System.out.println(movieById.getTitle());
        } else {
            System.out.println("Movie with ID " + movieId + " not found.");
        }
        System.out.println();

        // Test GET route to retrieve movies for a specified cinema
        String cinema = "Cinema5"; // Specify the cinema to retrieve movies for
        System.out.println("Testing GET /movies/cinemas/" + cinema);
        List<Movie> moviesByCinema = service.path("cinemas").path("get").path(cinema)
                .request(MediaType.APPLICATION_JSON)
                .get(new GenericType<List<Movie>>() {});
        if (!moviesByCinema.isEmpty()) {
            moviesByCinema.forEach(movie -> System.out.println(movie.getTitle()));
        } else {
            System.out.println("No movies found for Cinema " + cinema);
        }

        // Close the client instance
        client.close();
    }
}






