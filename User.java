package com.flexisaf.fip.filmproject;

public class User{
    private String username;
    private String password;

    public String getUsername(){ return username; }
    public void setUsername(String username){ this.username = username; }
    public String getPassword(){ return password; }
    public void setPassword(String password){ this.password = password; }

    public User(){
        username = "";
        password = "";
    }
    public User(String username, String password){
        this.username = username;
        this.password = password;
    }

    public void resetPassword(String oldPassword, String newPassword){
        if (CharSequence.compare(newPassword,oldPassword) == 1){
            this.password = newPassword;
        }
        else{
            System.out.println("[ERROR] Current password is wrong. Please enter current password");
        }
    }
}
