package com.example.cnit325finalproject;

public class User {
    private String Username;
    private String Password;
    private boolean IsAdmin;

    public User(String username, String password, boolean isAdmin)
    {
        this.Username = username;
        this.Password = password;
        this.IsAdmin = isAdmin;
    }

    public String getUsername() {
        return Username;
    }

    public String getPassword() {
        return Password;
    }

    public boolean getAdmin()
    {
        return IsAdmin;
    }
}
