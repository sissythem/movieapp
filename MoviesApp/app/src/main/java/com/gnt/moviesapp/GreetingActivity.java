package com.gnt.moviesapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

public class GreetingActivity extends AppCompatActivity {

    private Button registerBtn;
    private Button loginBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_greeting);
        initButtons();
        createListenersForButtons();
    }

    private void initButtons(){
        registerBtn = findViewById(R.id.register);
        loginBtn = findViewById(R.id.login);
    }

    private void createListenersForButtons(){
        registerBtn.setOnClickListener((l)->{
            GreetingActivity.this.startActivity(new Intent(GreetingActivity.this, RegisterActivity.class));
            finish();
        });

        //Initialize button for log in

        loginBtn.setOnClickListener((l)->{
            GreetingActivity.this.startActivity(new Intent(GreetingActivity.this, LoginActivity.class));
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
