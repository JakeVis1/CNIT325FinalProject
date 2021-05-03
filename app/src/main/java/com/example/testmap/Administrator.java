package com.example.testmap;

import com.example.testmap.Authenticateable;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * This is the Administrator Object class that inherits from the User Superclass
 * This class is primarily utilized for the constructor setting isAdmin to true.
 * This class would also allow for future expansion of admin capabilities.
 * @author Jake Visniski
 */
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
