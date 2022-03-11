package com.flexisaf.fip.filmproject;

import javax.xml.transform.Result;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FilmDatabase{
    List<Film> filmList;
    private final int viewMax;    //maximum number of films to show
    //ConnectionHandler handler;
    Connection conn;

    public FilmDatabase(Connection conn){
        filmList = new ArrayList<>();
        selectedFilm = new Film();
        reviewList = new ArrayList<>();
        viewMax = 30;
        //this.handler = handler;
        this.conn = conn;
    }
    public void addFilm(String id, String name, String description,
                        String genre, Double rating, LocalDate releaseDate){
        filmList.add(new Film());
        filmList.get(filmList.size() -1).addFilm(id, name, description, genre, rating, releaseDate);
    }
    public void search(String searchParam, String searchTerm){
        //Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            if(searchParam.equals("release_date")){
                String query = String.format("select name, genre,description, rating,release_date from film where %s = to_date(?, 'YYYY-MM-DD')",
                        searchParam);
                stmt = conn.prepareStatement(query);
                stmt.setDate(1, java.sql.Date.valueOf(searchTerm));
            }
            else {
                String query = String.format("select name, genre,description, rating,release_date from public.film where %s = ?",
                        searchParam);
                stmt = conn.prepareStatement(query
                        //"select name, genre,description, rating,release_date from public.film where ? = ?"
                        //"select * from public.film where name = ?"
                );
                if (searchParam.equals("rating")) stmt.setDouble(1, Double.parseDouble(searchTerm));
                else stmt.setString(1, searchTerm);
            }
            rs = stmt.executeQuery();
            while(rs.next()){
                System.out.printf("Name: %s,\nGenre: %s,\nDescription: %s,\nRating: %s,\nRelease Date: %s",
                rs.getString("name"),
                rs.getString("genre"),
                rs.getString("description"),
                rs.getString("rating"),
                rs.getString("release_date"));
            }
        } catch(SQLException e){
            System.err.println("Caught error: "+e.getMessage());
        } finally{
            try{
                if (rs!=null) rs.close();
            } catch (Exception ignored) {};
            try{
                if (stmt != null) stmt.close();
            }catch (Exception ignored) {};
        }
    }
    public void searchForReview(String fName){
        //Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            //conn = handler.connect();
            stmt = conn.prepareStatement(
                    "select review, createdBy, createdOn, userRating from reviews where filmId = " +
                            "(select id from public.film where name = ?)"
            );
            stmt.setString(1, fName);
            rs = stmt.executeQuery();
            while(rs.next()){
                System.out.printf("Name: %s,\nReview: %s,\nCreatedBy: %s,\nCreatedOn: %s,\nuserRating: %s",
                        fName,
                        rs.getString("review"),
                        rs.getString("createdBy"),
                        rs.getString("createdOn"),
                        rs.getString("userRating"));
            }
        } catch(SQLException e){
            System.err.println("Caught error: "+e.getMessage());
        } finally{
            try{
                if (rs!=null) rs.close();
            } catch (Exception ignored) {};
            try{
                if (stmt != null) stmt.close();
            }catch (Exception ignored) {};
            try{
                if (conn != null) conn.close();
            } catch (Exception ignored) {};
        }
    }
    public void viewFilms(){
        //Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            //conn = handler.connect();
            stmt = conn.prepareStatement(
                    "select id, name, genre,description, rating,release_date from film limit ?"
            );
            stmt.setInt(1, viewMax);
            rs = stmt.executeQuery();
            while(rs.next()){
                System.out.printf("|--------Details of Film %s--------|\n",rs.getString("id"));
                System.out.printf("Name: %s,\nGenre: %s,\nDescription: %s,\nRating: %s,\nRelease Date: %s\n",
                rs.getString("name"),
                rs.getString("genre"),
                rs.getString("description"),
                rs.getString("rating"),
                rs.getString("release_date"));
                System.out.printf("|------------End of Film %s--------|\n",rs.getString("id"));
            }
        } catch(SQLException e){
            System.err.println("Caught error: "+e.getMessage());
        } finally{
            try{
                if (rs!=null) rs.close();
            } catch (Exception ignored) {};
            try{
                if (stmt != null) stmt.close();
            }catch (Exception ignored) {};
         }
    }
}
