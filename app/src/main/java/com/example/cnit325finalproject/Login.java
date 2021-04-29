package com.example.cnit325finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

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
            if(((Administrator) user).CheckLogin(file))
            {
                Intent intent = new Intent(this, Admin.class);
                startActivity(intent);
            }
        } else
        {
            if(((EndUser) user).CheckLogin(file))
            {
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
            }
        }

    }
}
