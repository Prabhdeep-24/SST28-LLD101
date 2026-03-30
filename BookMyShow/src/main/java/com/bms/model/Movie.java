package com.bms.model;

public class Movie {
    private String id;
    private String title;
    private String language;
    private String genre;
    private int durationInMins;

    public Movie(String id, String title, String language, String genre, int durationInMins) {
        this.id = id;
        this.title = title;
        this.language = language;
        this.genre = genre;
        this.durationInMins = durationInMins;
    }

    public String getId() { return id; }
    public String getTitle() { return title; }
    public String getLanguage() { return language; }
    public String getGenre() { return genre; }
    public int getDurationInMins() { return durationInMins; }
}
