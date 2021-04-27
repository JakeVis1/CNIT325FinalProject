/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab2;
import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.util.Scanner;
/**
 *
 * @author Sydney Kim
 */
public class AccountManager {
    public void CreateAccount(String username, String password){
        try {
            //change folder path
            File file = new File("C:\\Users\\Sydney Kim\\Documents\\test\\accounts.txt");
            FileWriter writer = new FileWriter(file);
            writer.write("Username: " +username+ "|Password: " + password + "\n");
            writer.close();
            //display notification to user that account is created
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    
    //returns true if account exists
    //returns false if no account exists OR if login credentials are put incorrectly
    public boolean CheckAccount(String username, String password){
        String login = "Username: " +username+ "|Password: " + password;
        try{
            //change folder path
            BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\Sydney Kim\\Documents\\test\\accounts.txt"));
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
    
    //finds instance of the username and password and deletes it
    public void DeleteAccount(String username, String password){
        String login = "Username: " +username+ "|Password: " + password;
        
        //turn text file into a string
        String input = null;
        try{
        Scanner s = new Scanner(new File("C:\\Users\\Sydney Kim\\Documents\\test\\accounts.txt"));
        StringBuffer sb = new StringBuffer();
        while(s.hasNextLine()){
            input=s.nextLine();
            sb.append(input);
        }
        String filecontent = sb.toString();
        filecontent = filecontent.replaceAll(login, "");
        
        PrintWriter writer = new PrintWriter(new File("C:\\Users\\Sydney Kim\\Documents\\test\\accounts.txt"));
        writer.append(filecontent);
        writer.flush();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args){
        System.out.println("hi");
        AccountManager am = new AccountManager();
        am.CreateAccount("mea", "password");
        System.out.println(am.CheckAccount("mea", "password"));
        am.DeleteAccount("mea", "password");
        
    }
}
