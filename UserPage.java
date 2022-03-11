package com.flexisaf.fip.filmproject;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class UserPage{
    FilmDatabase filmDatabase;

    public UserPage(Connection conn){
        filmDatabase = new FilmDatabase(conn);
    }

    public void search(){
        System.out.println("Please select an option:");
        System.out.println("Search by:");
        System.out.println("1-Name\t 2-Genre\t 3-Rating\t 4-Date");
        Scanner in =  new Scanner(System.in);
        int choice = in.nextInt();
        if (choice == 1) {
            System.out.println("Enter search term (Name): ");
            String searchTerm = in.next();
            filmDatabase.search("name",searchTerm);
        }
        if (choice == 2){
            System.out.println("Enter search term (Genre): ");
            String searchTerm = in.next();
            filmDatabase.search("genre",searchTerm);
        }
        if (choice == 3){
            System.out.println("Enter search term (Rating): ");
            String searchTerm = in.next();
            filmDatabase.search("rating",searchTerm);
        }
        if (choice == 4){
            System.out.println("Enter search term (Date): ");
            String searchTerm = in.next();
            filmDatabase.search("release_date",searchTerm);
        }
    }
    public void viewFilms(){
        filmDatabase.viewFilms();
    }

}
