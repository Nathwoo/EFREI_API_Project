package com.allocine.allocine_api.server;

import com.allocine.allocine_api.dao.MovieDao;
import com.allocine.allocine_api.model.Movie;
import com.allocine.allocine_api.model.Session;


import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;
import java.util.Map;

@Path("/")
public class MovieResource {
    MovieDao movieDao = new MovieDao();

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response apiStatus() {
        return Response.ok().entity("API is online").build();
    }

    @POST
    @Path("/movies/post")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addMovie(Movie movie) {
        movieDao.addMovie(movie);
        return Response.status(Response.Status.CREATED)
                .entity(movie)
                .build();
    }

    @GET
    @Path("/movies/get")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllMovies() {
        List<Movie> movies = movieDao.getAllMovies();
        return Response.ok(movies).build();
    }

    @GET
    @Path("/movies/get/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMovieById(@PathParam("id") int id) {
        Movie movie = movieDao.getMovieById(id);
        if (movie == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(movie).build();
    }

    @GET
    @Path("/cinemas/get/{cinema}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMoviesByCinema(@PathParam("cinema") String cinema) {
        List<Movie> movies = movieDao.getMoviesByCinema(cinema);
        if (movies.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(movies).build();
    }

    //LOGIN

    @GET
    @Path("/login")
    @Produces(MediaType.TEXT_HTML)
    public Response getLoginPage() {
        String htmlContent = "";
        String pathFront = "../../../EFREI_API_Project-dev_add_movie/src/main/webapp/Front/";
        String path = pathFront + "login.html" ;
        try {
            htmlContent = new String(Files.readAllBytes(Paths.get(path)));
        } catch (IOException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error reading login.html").build();
        }
        return Response.ok(htmlContent).build();
    }

    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response login(Map<String, String> credentials) {
        String username = credentials.get("username");
        String password = credentials.get("password");

        if ("root".equals(username) && "root".equals(password)) {
            return Response.ok().build();
        } else {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }

    //Service 1
    @GET
    @Path("/Service1")
    @Produces(MediaType.TEXT_HTML)
    public Response getService1() {
        String htmlContent = "";
        String pathFront = "../../../EFREI_API_Project-dev_add_movie/src/main/webapp/Front/";
        String path = pathFront + "Service1.html" ;
        try {
            htmlContent = new String(Files.readAllBytes(Paths.get(path)));
        } catch (IOException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error reading login.html").build();
        }
        return Response.ok(htmlContent).build();
    }
    @POST
    @Path("/film")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addFilm(Movie newFilm) {
        try {
            movieDao.addMovie(newFilm);
            return Response.status(Response.Status.CREATED).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error adding new film").build();
        }
    }


    //Service 2
    @GET
    @Path("/Service2")
    @Produces(MediaType.TEXT_HTML)
    public Response getService2() {
        String htmlContent = "";
        String pathFront = "../../../EFREI_API_Project-dev_add_movie/src/main/webapp/Front/";
        String path = pathFront + "Service2.html" ;
        try {
            htmlContent = new String(Files.readAllBytes(Paths.get(path)));
        } catch (IOException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error reading login.html").build();
        }
        return Response.ok(htmlContent).build();
    }
    @GET
    @Path("/movies")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMoviesByCity(@QueryParam("city") String city) {
        List<Movie> movies = movieDao.getMoviesByCity(city);
        if (movies.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(movies).build();
    }


    //Service 3
    @GET
    @Path("/Service3")
    @Produces(MediaType.TEXT_HTML)
    public Response getService3() {
        String htmlContent = "";
        String pathFront = "../../../EFREI_API_Project-dev_add_movie/src/main/webapp/Front/";
        String path = pathFront + "Service3.html" ;
        try {
            htmlContent = new String(Files.readAllBytes(Paths.get(path)));
        } catch (IOException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error reading login.html").build();
        }
        return Response.ok(htmlContent).build();
    }

}


