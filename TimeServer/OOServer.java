/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.*;
import java.net.*;
import java.time.Clock;
import java.time.Instant;
import java.util.*;
import java.time.LocalTime;


/**
 * Main class for the Time Server.
 * The server uses the local device time and sends the timestamp when a connection is established
 * The server also logs every transaction in a log file.
 * @author Sydney Kim
 */
public class OOServer {
    InputStream inStream;
    OutputStream outStream;
    Scanner in;
    PrintWriter out;
    Logger logger;

    public void MyServer(int IP){
        try {
            ServerSocket s = new ServerSocket(IP); //the server socket
            boolean over = false;
            while (!over) //put in a loop that keeps running
            {
                Socket incoming = s.accept(); //accept a connection from a client
                try {
                    OutputStream outStream = incoming.getOutputStream(); // the OUTPUT stream handler
                    LocalTime l = LocalTime.now();
                    PrintWriter out = new PrintWriter(outStream, true); // sends output

                    Clock clock = Clock.systemDefaultZone();
                    Instant instant = clock.instant();

                    out.println(instant.toString());
                    // This is where the polymorphic behavior from the logger class comes into play.
                    logger = new TextLogger();
                    logger.LogTransaction(instant.toString());



                } catch (Exception exc1) {
                    exc1.printStackTrace();
                }
            }
        } catch (Exception exc2) {
            exc2.printStackTrace();
        }
    }
    public static void main(String[] args){
        OOServer server = new OOServer();
        server.MyServer(8189);
    }

}