package com.example.cnit325finalproject;

import com.example.cnit325finalproject.Authenticateable;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Administrator extends User implements Authenticateable {



    public Administrator(String username, String password)
    {
        super(username, password, true);
    }
    @Override
    public boolean CheckLogin(File file) {
        String login = getUsername() + "," + getPassword() + "," + "Administrator";

        try{
            //change folder path
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = reader.readLine();
            while(line != null){
                if(line.equals(login)){
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
