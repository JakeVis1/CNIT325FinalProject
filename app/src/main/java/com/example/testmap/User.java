package com.example.testmap;
/**
 * This class is hte superclass of EndUser and Administrator.
 * The class is used primarily for code reuse purposes.
 * @author Jake Visniski
 */
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
