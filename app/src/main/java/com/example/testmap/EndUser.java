package com.example.testmap;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
/**
 * This class is used for users of the weather/directions side of teh application
 * Implements authenticateable and extends the User class.
 * @author Jake Visniski
 */
public class EndUser extends User implements Authenticateable {


    public EndUser(String username, String password)
    {
        super(username, password, false);
    }


    @Override
    public boolean CheckLogin(File file) {
        String login = getUsername() + "," + getPassword()+ "," + "User";
        try{
            //change folder path
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = reader.readLine();
            while(line != null){
                if(line.contains(login)){
                    return true;
                }
                line = reader.readLine();
            }
        }catch(IOException e){
            e.printStackTrace();
        }
        return false;
    }



}