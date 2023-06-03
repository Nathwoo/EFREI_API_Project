package com.allocine.allocine_api.server;

import com.allocine.allocine_api.dao.MovieDao;
import com.allocine.allocine_api.model.Movie;


import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/")
public class MovieResource {
    MovieDao movieDao = new MovieDao();

    @POST
    @Path("/post")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addMovie(Movie movie) {
        // Logic to add the movie to your database or perform any other actions
        // Assuming you have a MovieDao class to handle data access
        movieDao.addMovie(movie);

        // Return a success response
        return Response.status(Response.Status.CREATED)
                .entity(movie)
                .build();
    }

    @GET
    @Path("/get")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllMovies() {
        List<Movie> movies = movieDao.getAllMovies();
        return Response.ok(movies).build();
    }

    @GET
    @Path("/movies/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMovieById(@PathParam("id") int id) {
        Movie movie = movieDao.getMovieById(id);
        if (movie == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(movie).build();
    }

    @GET
    @Path("/cinemas/{cinema}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMoviesByCinema(@PathParam("cinema") String cinema) {
        List<Movie> movies = movieDao.getMoviesByCinema(cinema);
        if (movies.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(movies).build();
    }
}
