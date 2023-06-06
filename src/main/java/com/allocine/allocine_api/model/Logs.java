package com.allocine.allocine_api.model;

public class Logs {
    private String username;
    private String password;

    public Logs(){}

    public Logs(String username, String password){
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

}
