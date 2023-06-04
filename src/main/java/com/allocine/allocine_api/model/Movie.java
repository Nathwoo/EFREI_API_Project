package com.allocine.allocine_api.model;


import java.util.Date;
import java.util.List;

public class Movie {
    private int id;
    private String title;
    private int duration;
    private String language;
    private List<String> subtitlesLanguages;
    private String director;
    private List<String> mainActors;
    private int minimalAge;
    private Date releaseDate;
    private List<Session> sessions;

    // Constructors, getters, and setters

    public Movie() {

    }

    public Movie(int id, String title, int duration, String language, List<String> subtitlesLanguages,
                 String director, List<String> mainActors, int minimalAge, Date releaseDate,
                 List<Session> sessions) {
        this.id = id;
        this.title = title;
        this.duration = duration;
        this.language = language;
        this.subtitlesLanguages = subtitlesLanguages;
        this.director = director;
        this.mainActors = mainActors;
        this.minimalAge = minimalAge;
        this.releaseDate = releaseDate;
        this.sessions = sessions;
    }

    // Getters and setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public List<String> getSubtitlesLanguages() {
        return subtitlesLanguages;
    }

    public void setSubtitlesLanguages(List<String> subtitlesLanguages) {
        this.subtitlesLanguages = subtitlesLanguages;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public List<String> getMainActors() {
        return mainActors;
    }

    public void setMainActors(List<String> mainActors) {
        this.mainActors = mainActors;
    }

    public int getMinimalAge() {
        return minimalAge;
    }

    public void setMinimalAge(int minimalAge) {
        this.minimalAge = minimalAge;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public List<Session> getSessions() {
        return sessions;
    }

    public void setSessions(List<Session> sessions) {
        this.sessions = sessions;
    }
}

