package com.gnt.moviesapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import com.gnt.utils.Session;

public class GreetingActivity extends AppCompatActivity {

    Context c;
    Session sessionData;
    //Fist screen of application. Gives user the choice either to log in or register
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_greeting);

        //Initialize button for register
        final Button register = findViewById(R.id.register);
        register.setOnClickListener((l)->{
            Intent registerintent = new Intent(GreetingActivity.this, RegisterActivity.class);
            GreetingActivity.this.startActivity(registerintent);
            finish();
        });

        //Initialize button for log in
        final Button login = (Button) findViewById(R.id.login);
        login.setOnClickListener((l)->{
            Intent loginintent = new Intent(GreetingActivity.this, LoginActivity.class);
            GreetingActivity.this.startActivity(loginintent);
            finish();
        });
    }

    /** OnBackPressed does not let user to go back to HomeActivity and just minimizes the app **/
    @Override
    public void onBackPressed()
    {
        moveTaskToBack(true);
    }
}
