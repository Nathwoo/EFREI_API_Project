package com.allocine.allocine_api.server;

import com.allocine.allocine_api.dao.MovieDao;
import com.allocine.allocine_api.model.Logs;
import com.allocine.allocine_api.model.Movie;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.io.IOException;
import java.security.Key;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/")
public class MovieResource {
    MovieDao movieDao = new MovieDao();
    //String pathFront = "../../Users/nprov/Desktop/COURS_EFREI/M1/API & Webservices/API_Project/src/main/webapp/Front/";
    String pathFront = "../../../EFREI_API_Project/src/main/webapp/Front/";
    static String authToken = "";
    Key signingKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    //------------------------------------------------------------------------------------------------------------------------------------------//
    //------------------------------------------------------------------------------------------------------------------------------------------//
    // LOGIN
    @GET
    @Path("/login")
    @Produces(MediaType.TEXT_HTML)
    public Response getLoginPage() {
        String htmlContent = "";

        String path = pathFront + "login.html" ;
        try {
            byte[] fileContentBytes = Files.readAllBytes(Paths.get(path));
            htmlContent = new String(fileContentBytes, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error reading login.html").build();
        }
        return Response.ok(htmlContent).build();
    }

    @Path("/login")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(Logs logs) {
        if (isValidCredentials(logs.getUsername(), logs.getPassword())) {
            authToken = generateAuthToken();
            return Response.ok().entity(logs).build();
        } else {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }
    //------------------------------------------------------------------------------------------------------------------------------------------//
    //------------------------------------------------------------------------------------------------------------------------------------------//
    // Service 1

    @GET
    @Path("Service1")
    @Produces(MediaType.TEXT_HTML)
    public Response getMoviesPostPage() {
        String htmlContent = "";
        String path = pathFront + "Service1.html" ;
        try {
            byte[] fileContentBytes = Files.readAllBytes(Paths.get(path));
            htmlContent = new String(fileContentBytes, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error reading Service1.html").build();
        }
        return Response.ok(htmlContent).build();
    }
    @POST
    @Path("Service1")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addMovie(Movie movie) {
        if (verifyAuthToken(authToken)) {
            movieDao.addMovie(movie);
            return Response.ok().entity(movie).build();
        } else {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }
    //------------------------------------------------------------------------------------------------------------------------------------------//
    //------------------------------------------------------------------------------------------------------------------------------------------//
    //Service 2
    @GET
    @Path("/Service2")
    @Produces(MediaType.TEXT_HTML)
    public Response getService2() {
        String htmlContent = "";
        String path = pathFront + "Service2.html" ;
        try {
            byte[] fileContentBytes = Files.readAllBytes(Paths.get(path));
            htmlContent = new String(fileContentBytes, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error reading Service2.html").build();
        }
        return Response.ok(htmlContent).build();
    }
    @GET
    @Path("/Service2/city")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMoviesByCity(@QueryParam("city") String city) {
        List<Movie> movies = movieDao.getMoviesByCity(city);
        if (movies.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(movies).build();
    }

    //------------------------------------------------------------------------------------------------------------------------------------------//
    //------------------------------------------------------------------------------------------------------------------------------------------//
    //Service 3
    @GET
    @Path("/Service3")
    @Produces(MediaType.TEXT_HTML)
    public Response getService3() {
        String htmlContent = "";
        String path = pathFront + "Service3.html" ;
        try {
            byte[] fileContentBytes = Files.readAllBytes(Paths.get(path));
            htmlContent = new String(fileContentBytes, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error reading Service3.html").build();
        }
        return Response.ok(htmlContent).build();
    }

    @GET
    @Path("/Service3/title")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMoviesByTitle(@QueryParam("title") String title) {
        List<Movie> movies = movieDao.getAllMovies();
        List<Movie> matchingMovies = new ArrayList<>();
        for (Movie movie: movies) {
            String title_movie = movie.getTitle();
            if (title_movie.equals(title)) {
                matchingMovies.add(movie);
            }
        }
        if (!matchingMovies.isEmpty()) {
            return Response.ok(matchingMovies).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }
    //------------------------------------------------------------------------------------------------------------------------------------------//
    //------------------------------------------------------------------------------------------------------------------------------------------//
    // utils
    private boolean isValidCredentials(String username, String password) {
        return username.equals("root") && password.equals("root");
    }

    private String generateAuthToken() {
        String authToken = Jwts.builder()
                .setSubject("username")
                .signWith(signingKey)
                .compact();

        return authToken;
    }

    private boolean verifyAuthToken(String authToken) {
        try {
            Jws<Claims> claimsJws = Jwts.parser()
                    .setSigningKey(signingKey)
                    .parseClaimsJws(authToken);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    //------------------------------------------------------------------------------------------------------------------------------------------//
    //------------------------------------------------------------------------------------------------------------------------------------------//
    // Other Road

    @GET
    @Path("/styles.css")
    @Produces("text/css")
    public Response getCSS() {
        String cssContent = "";
        String path = pathFront + "styles.css" ;
        try {
            cssContent = new String(Files.readAllBytes(Paths.get(path)));
        } catch (IOException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error reading styles.css").build();
        }
        return Response.ok(cssContent).build();
    }

    @GET
    @Path("/allmovie")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMovies() {
        List<Movie> movies = movieDao.getAllMovies();
        if (!movies.isEmpty()) {
            return Response.ok(movies).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response apiStatus() {
        return Response.ok().entity("API is online").build();
    }

    @DELETE
    @Path("/allmovie/del")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response delMovies(Movie movieToDelete) {
        List<Movie> movies = movieDao.getAllMovies();
        for (Movie movie : movies) {
            if (movie.getId() == movieToDelete.getId()
                    && movie.getTitle().equals(movieToDelete.getTitle())
                    && movie.getReleaseDate().equals(movieToDelete.getReleaseDate())
                    && movie.getDirector().equals(movieToDelete.getDirector())) {
                movieDao.deleteMovie(movie);
                return Response.ok().build();
            }
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    //------------------------------------------------------------------------------------------------------------------------------------------//
    //------------------------------------------------------------------------------------------------------------------------------------------//


}
