package com.flexisaf.fip.filmproject;

import java.sql.Connection;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // write your code here
        ConnectionHandler handler = new ConnectionHandler(args[0]);
        Connection conn = handler.connect();
        Login loginPage = new Login(conn);
        boolean correctLogin = false;
        UserPage userPage = new UserPage(conn);
        System.out.println("Choose an option please: ");
        System.out.println("1-Login as User\t 2-Sign Up");
        Scanner in = new Scanner(System.in);
        int choice = in.nextInt();
        in.nextLine();
        switch (choice) {
            case 1 -> {        //Login as User
                System.out.println("Enter login: ");
                String enteredLogin = in.nextLine();
                //in.nextLine();
                System.out.println("Enter password: ");
                String enteredPassword = in.nextLine();
                try {
                    correctLogin = loginPage.checkLogin(enteredLogin, enteredPassword);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            case 2 -> {            //Sign Up
                System.out.println("Enter login:");
                String enteredLogin = in.nextLine();
                System.out.println("Enter password");
                String enteredPassword = in.nextLine();
                loginPage.addUser(enteredLogin, enteredPassword);
                try {
                    correctLogin = loginPage.checkLogin(enteredLogin, enteredPassword);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        if(correctLogin) {
            while (true) {
                System.out.println("1-View films\t 2-Search films\t");
                System.out.println("Enter 'quit' to exit");
                String input = in.nextLine();
                if (input.equalsIgnoreCase("quit")) break;
                choice = Integer.parseInt(input);
                if (choice == 1)
                    userPage.viewFilms();
                if (choice == 2)
                    userPage.search();
                System.out.println();
            }
            try{in.close();} catch (Exception ignored){}
        }
        else
            System.out.println("Sorry, you're not allowed");
        loginPage.saveUsersInfo();
        System.out.println("Done");
    }
}
