package com.allocine.allocine_api.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.Date;

public class Session {
    private Date startTime;
    private String cinemaName;
    private String city;
    // Constructors, getters, and setters

    public Session() {

    }

    public Session(Date startTime, String cinemaName, String city) {
        this.startTime = startTime;
        this.cinemaName = cinemaName;
        this.city = city;
    }

    // Getters and setters

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public String getCinemaName() {
        return cinemaName;
    }

    public void setCinemaName(String cinemaName) {
        this.cinemaName = cinemaName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}

