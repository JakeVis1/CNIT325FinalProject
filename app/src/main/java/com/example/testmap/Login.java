package com.example.testmap;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
/**
 * This class is the code behind for the login screen
 * @author Jake Visniski
 */
public class Login extends AppCompatActivity {

    //Boilerplate method for instantiating GUI layout
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    //This method is the OnClick for the login button
    public void LoginUser(View v)
    {
        Login.this.getFilesDir();

        File file = new File(Login.this.getFilesDir(), "Accounts.txt");
        if (!file.exists()) {

            try
            {
                file.createNewFile();
                FileWriter writer = new FileWriter(file);
                writer.write("Developer" + "," + "Password" +  "," + "Administrator" + "\n");
                writer.close();
            } catch (IOException e)
            {
                e.printStackTrace();
            }

        }
        CheckBox AdminCheckbox = (CheckBox)findViewById(R.id.checkBox);
        EditText username = (EditText) findViewById(R.id.editText6);
        EditText password = (EditText) findViewById(R.id.editText4);
        Authenticateable user;

        if(AdminCheckbox.isChecked()) {
            user = new Administrator(username.getText().toString(),
                    password.getText().toString());
        } else
        {
            user = new EndUser(username.getText().toString(),
                    password.getText().toString());
        }

        if(((User) user).getAdmin())
        {
            if(user.CheckLogin(file))
            {
                Intent intent = new Intent(this, Admin.class);
                startActivity(intent);
            }
        } else
        {
            if(user.CheckLogin(file))
            {
                Intent intent = new Intent(this, MapsActivity.class);
                startActivity(intent);
            }
            else {
                Toast.makeText(this, "User does not exist. Please try again.", Toast.LENGTH_LONG).show();
            }
        }

    }
}
