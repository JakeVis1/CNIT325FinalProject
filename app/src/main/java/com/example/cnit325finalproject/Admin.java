package com.example.cnit325finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.testmap.R;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Admin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        Spinner UserDropdown = findViewById(R.id.spinner);

        Admin.this.getFilesDir();
        File file = new File(Admin.this.getFilesDir(), "Accounts.txt");
        AccountManager accountManager = new AccountManager();

        ArrayList<User> userList= accountManager.readAccounts(file);
        ArrayList<String> usernames = new ArrayList<String>();
        for (User user : userList) {
            usernames.add(user.getUsername());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item, usernames);
        UserDropdown.setAdapter(adapter);
    }



    public void createUser(View v)
    {
        Admin.this.getFilesDir();
        File file = new File(Admin.this.getFilesDir(), "Accounts.txt");
        if (!file.exists()) {
            try
            {
                file.createNewFile();

            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        CheckBox AdminCheckbox = (CheckBox)findViewById(R.id.checkBox2);
        EditText username = (EditText) findViewById(R.id.editText7);
        EditText password = (EditText) findViewById(R.id.editText8);
        AccountManager accountManager = new AccountManager();
        Authenticateable user;

        if(AdminCheckbox.isChecked()) {
            if(accountManager.CreateAccount(username.getText().toString(),
                    password.getText().toString(), true, file))
            {
                Context context = getApplicationContext();
                CharSequence text = "Account Creation Successful";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        } else
        {
            if(accountManager.CreateAccount(username.getText().toString(),
                    password.getText().toString(), false, file))
            {
                Context context = getApplicationContext();
                CharSequence text = "Account Creation Successful";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }


        }
    }

    public void deleteUser(View v)
    {
        AccountManager accountManager = new AccountManager();
        Spinner username = (Spinner) findViewById(R.id.spinner);

        Admin.this.getFilesDir();
        File file = new File(Admin.this.getFilesDir(), "Accounts.txt");
        User userToDelete =
                accountManager.RetreiveAccount(username.getSelectedItem().toString(), file);
        if(accountManager.DeleteAccount(userToDelete.getUsername(),
                userToDelete.getPassword(), userToDelete.getAdmin(), file))
        {
            Context context = getApplicationContext();
            CharSequence text = "Account Deletion Successful";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }

    }

    public void logout(View v)
    {
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }



}
