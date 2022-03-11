package com.flexisaf.fip.filmproject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class Login {
    private List<User> userList;
    HashSet<User> userSet;
    Map<String,String> userData;
    protected static boolean isInUserList;
    //ConnectionHandler handler;
    Connection conn;

    public Login(Connection conn)
    {
        userList = new ArrayList<>();
        userSet = new HashSet<>();
        userData = new HashMap<>();
        isInUserList = false;
        this.conn = conn;
        loadUserInfo();
    }

    public void addUser(String username, String password){
        User newUser = new User(username, password);
        userSet.add(newUser);
        if(userSet.contains(newUser))
            userData.put(newUser.getUsername(),newUser.getPassword());
    }

    public boolean checkLogin(String userLogin, String userPassword) throws Exception {
        boolean correctLogin = false;
        boolean correctPassword = false;

        if(userData.containsKey(userLogin)) {
            correctLogin = true;
            if (userData.get(userLogin).equals(userPassword))
                correctPassword = true;
            else
                correctPassword = false;
        }
        else
            correctLogin = false;

        if(correctLogin && !correctPassword)
            throw new Exception("Wrong password");
        if(!correctLogin)
            throw new Exception("Wrong login");
        if(!correctLogin && !correctPassword)
            throw new Exception("Wrong login and password");
        boolean result = correctPassword && correctLogin;
        return result;
    }


    public List<User> getUserList(){return userList;}

    private void loadUserInfo(){
        //Connection conn = null;
        ResultSet rs = null;
        PreparedStatement stmt = null;
        try{
            //conn = handler.connect();
            stmt = conn.prepareStatement("select * from users");
            rs = stmt.executeQuery();
            while(rs.next()){
                addUser(rs.getString("username"),rs.getString("password"));
            }
        } catch (SQLException e){
            System.err.println("Caught error: "+e.getMessage());
        } finally {
            try{ rs.close();} catch(Exception ignored) {}
            try{ stmt.close();} catch (Exception ignored) {}
        }
    }
    public void saveUsersInfo(){
        for (Map.Entry<String, String> stringStringEntry : userData.entrySet()) {
            insertData(stringStringEntry.getKey(), stringStringEntry.getValue());
        }
    }
    private void insertData(String key, String value){
        PreparedStatement stmt = null;
        try{
            //conn = handler.connect();
            stmt = conn.prepareStatement("insert into users (username, password) values(?, ?) on conflict (username) do nothing");
            stmt.setString(1,key);
            stmt.setString(2,value);
            stmt.executeUpdate();
        } catch (SQLException e){
            System.err.println("Caught error: "+e.getMessage());
        } finally {
            try{ stmt.close();} catch (Exception ignored) {}
        }
    }
}

