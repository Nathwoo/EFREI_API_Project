package com.allocine.allocine_api.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.Date;

public class Session {
    private Date startTime;
    private String cinemaName;

    // Constructors, getters, and setters

    public Session(Date startTime, String cinemaName) {
        this.startTime = startTime;
        this.cinemaName = cinemaName;
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
}

