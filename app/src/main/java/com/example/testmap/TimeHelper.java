package com.example.testmap;

import android.os.AsyncTask;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.Scanner;

public class TimeHelper extends AsyncTask<Void, Void, String> {

    public String GetTime()
    {
        String time = "";
        try
        {
            Socket s = new Socket("10.192.13.108",8189);
            try
            {
                InputStream inStream = s.getInputStream();
                Scanner in = new Scanner(inStream);
                time = in.nextLine();

            }
            finally
            {
                s.close();
            }
        }
        catch(IOException ioexc)
        {
            ioexc.printStackTrace();
            return "Oops...";
        }
        return time;
    }

    @Override
    protected String doInBackground(Void... voids) {
        return GetTime();
    }
}
