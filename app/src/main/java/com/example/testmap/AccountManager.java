package com.example.testmap;
import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

public class AccountManager {
    public boolean CreateAccount(String username, String password, boolean isAdmin, File file){
        try {
            //change folder path

            FileWriter writer = new FileWriter(file, true);
            TimeHelper helper = new TimeHelper();
            String time = "";
            try
            {
                time = new TimeHelper().execute().get();
            } catch (ExecutionException e)
            {
                time = "TimeServerFault";
                e.printStackTrace();
            } catch (InterruptedException e)
            {
                time = "TimeServerFault";
                e.printStackTrace();
            }

            System.out.println(time);
            if(isAdmin)
            {

                writer.write(username + "," + password +  "," + "Administrator" +
                        "," + time + "\n");
            } else
            {
                writer.write(username + "," + password +  "," + "User" + "," + time +"\n");
            }

            writer.close();
            return true;
            //display notification to user that account is created
        } catch (IOException e) {
            e.printStackTrace();
            return false;

        }
    }


    //finds instance of the username and password and deletes it
    public boolean DeleteAccount(String username, String password, boolean isAdmin, File file){

        String login;
        if(isAdmin)
        {
            login = username + "," + password + "," + "Administrator";
        }
        else
        {
            login = username + "," + password + "," + "User";
        }


        //turn text file into a string
        String input;
        try{
            Scanner s = new Scanner(file);
            StringBuffer sb = new StringBuffer();
            while(s.hasNextLine()){
                input = s.nextLine();
                sb.append(input);
            }
            String filecontent = sb.toString();
            filecontent = filecontent.replaceAll(login, "");

            PrintWriter writer = new PrintWriter(file);
            writer.append(filecontent);
            writer.flush();
        }catch(IOException e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public ArrayList<User> readAccounts(File file)
    {
        ArrayList<User> retList = new ArrayList<User>();
        String input;
        try{
            Scanner s = new Scanner(file);

            while(s.hasNextLine()){
                input = s.nextLine();
                String username = input.split(",")[0];
                String password = input.split(",")[1];
                String admin = input.split(",")[2];
                boolean isAdmin;
                if(admin.equals("Administrator"))
                {
                    isAdmin = true;
                } else
                {
                    isAdmin = false;
                }
                retList.add(new User(username, password, isAdmin));

            }
        }catch(IOException e) {
            e.printStackTrace();
        }
        return retList;
    }

    public User RetreiveAccount(String username, File file)
    {

        String account = "";
        try{

            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = reader.readLine();

            while(line != null){
                if(line.contains(username)){
                    account =  line;
                    break;
                }
                line = reader.readLine();
            }
        }catch(IOException e){
            e.printStackTrace();
        }

        String tempUsername = account.split(",")[0];
        String password = account.split(",")[1];
        String admin = account.split(",")[2];


        if(admin.equals("Administrator"))
        {

            return new Administrator(tempUsername, password);
        } else
        {

            return new EndUser(tempUsername, password);
        }
    }

}
