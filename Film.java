package com.flexisaf.fip.filmproject;

import java.time.LocalDate;
import java.util.Dictionary;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Film {
    private String fId;
    private String fName;
    private String fGenre;
    private String fDescription;
    private Double fRating;
    private LocalDate fReleaseDate;

    public String getId(){
        return this.fId;
    }
    public void setId(String newId){
        this.fId = newId;
    }
    
    public String getName(){
        return this.fName;
    }
    public void setName(String newName){
        this.fName = newName;
    }
    public String getGenre(){
        return this.fGenre;
    }
    public void setGenre(String newGenre){
        this.fGenre = newGenre;
    }
    public String getDescription(){
        return this.fDescription;
    }
    public void setDescription(String newDescription){
        this.fDescription = newDescription;
    }
    public Double getRating(){
        return this.fRating;
    }
    public void setRating(Double newRating){
        this.fRating = newRating;
    }
    public LocalDate getReleaseDate(){
        return this.fReleaseDate;
    }
    public void setReleaseDate(LocalDate newDate){
        this.fReleaseDate = newDate;
    }

    public void addFilm(String id, String name, String description,
                        String genre, Double rating, LocalDate releaseDate){
        this.fName = name;
        this.fId = id;
        this.fGenre = genre;
        this.fReleaseDate = releaseDate;
        this.fRating = rating;
        this.fDescription = description;
    }

    public void addFilm(Dictionary<String, String> values) {
        this.fId = values.get("id");
        this.fName = values.get("name");
        this.fDescription = values.get("description");
        this.fGenre = values.get("genre");
        this.fRating = Double.valueOf(values.get("rating"));
        this.fReleaseDate = LocalDate.parse(values.get("releaseDate"));
    }
}
