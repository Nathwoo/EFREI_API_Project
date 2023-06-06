package com.allocine.allocine_api.server;

import com.allocine.allocine_api.dao.MovieDao;
import com.allocine.allocine_api.model.Logs;
import com.allocine.allocine_api.model.Movie;
import com.allocine.allocine_api.model.Session;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.io.IOException;
import java.security.Key;


import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/")
public class MovieResource {
    MovieDao movieDao = new MovieDao();
    String pathFront = "../../Users/nprov/Desktop/COURS_EFREI/M1/API & Webservices/API_Project/src/main/webapp/Front/";
    static String authToken = "";
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response apiStatus() {
        return Response.ok().entity("API is online").build();
    }

    @GET
    @Path("/login")
    @Produces(MediaType.TEXT_HTML)
    public Response getLoginPage() {
        String htmlContent = "";

        String path = pathFront + "login.html" ;
        try {
            htmlContent = new String(Files.readAllBytes(Paths.get(path)));
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
        // Perform login logic here
        if (isValidCredentials(logs.getUsername(), logs.getPassword())) {
            // Generate authentication token or session
            authToken = generateAuthToken();
            // Return the authentication token as response
            return Response.ok().entity(logs).build();
        } else {
            // Return an error response
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }
    private boolean isValidCredentials(String username, String password) {
        // Compare username and password with expected values
        // You can replace this with your own logic, such as validating against a database
        return username.equals("admin") && password.equals("password");
    }

    private String generateAuthToken() {
        // Generate a unique authentication token or session
        // This can be a random string or a JWT (JSON Web Token) implementation

        // Generate a secure signing key for JWT
        Key signingKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);

        // Build the JWT token with the desired claims
        String authToken = Jwts.builder()
                .setSubject("username") // Set the subject of the token (e.g., username)
                // You can include additional claims if needed, such as roles or permissions
                .signWith(signingKey) // Sign the token with the signing key
                .compact(); // Compact the token into its final string representation

        return authToken;
    }

    @GET
    @Path("/movies/post/")
    @Produces(MediaType.TEXT_HTML)
    public Response getMoviesPostPage() {
        String htmlContent = "";

        String path = pathFront + "Service1.html" ;
        try {
            htmlContent = new String(Files.readAllBytes(Paths.get(path)));
        } catch (IOException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error reading Service1.html").build();
        }
        return Response.ok(htmlContent).build();
    }
    @POST
    @Path("/movies/post/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addMovie(Movie movie) {
        movieDao.addMovie(movie);
        // Return a success response
        return Response.ok().entity(movie).build();
        /*
        if (verifyAuthToken(authToken)) {
            movieDao.addMovie(movie);
            // Return a success response
            return Response.ok().entity(movie).build();
        } else {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }*/

    }
    private boolean verifyAuthToken(String authToken) {
        try {
            // Parse the JWT token and validate its signature
            Jws<Claims> claimsJws = Jwts.parser()
                    .setSigningKey(getSigningKey()) // Provide the same signing key used for token generation
                    .parseClaimsJws(authToken);

            // The token signature is valid
            // You can perform additional checks or extract claims if needed
            // For example, you can access the subject (username) as follows:
            // String username = claimsJws.getBody().getSubject();

            return true;
        } catch (Exception e) {
            // The token is either invalid, expired, or has an invalid signature
            return false;
        }
    }
    private Key getSigningKey() {
        // Return the same signing key used for token generation
        // Make sure to securely store and retrieve the key
        // For simplicity, this example returns a new signing key each time,
        // but in practice, you should store and retrieve the key securely
        return Keys.secretKeyFor(SignatureAlgorithm.HS256);
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
}
