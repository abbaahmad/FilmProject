
package com.flexisaf.fip.filmproject;

import java.sql.*;

public class ConnectionHandler{

    private final String host = "jdbc:postgresql://localhost:5432/filmlist";
    private final String username = "postgres";
    private String password = "";

    public ConnectionHandler(String password){
        this.password = password;
    }
    public Connection connect(){
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try{
            conn = DriverManager.getConnection(host, username, password);
            System.out.println("[INFO] Connected to server");
            String filmTableQuery ="create table if not exists film(id SERIAL NOT NULL PRIMARY KEY, name text NOT NULL," +
                    "genre text, description text, rating numeric, release_date date)" ;
            String userTableQuery = "create table if not exists users (id SERIAL NOT NULL PRIMARY KEY, username varchar(225) NOT NULL UNIQUE," +
                    "password varchar(225))";
            

            stmt = conn.createStatement();
            stmt.executeUpdate(filmTableQuery);
            stmt.executeUpdate(userTableQuery);
            //stmt.executeUpdate(reviewTableQuery);

        } catch(SQLException e){
            System.err.println("[ERROR] Caught an exception: "+e.getMessage());
        }
        return conn;
    }
}
