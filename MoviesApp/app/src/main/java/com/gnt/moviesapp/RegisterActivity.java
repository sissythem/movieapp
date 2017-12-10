package com.gnt.moviesapp;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.gnt.appobjects.User;
import com.gnt.utils.RetrofitCalls;

import java.time.LocalDate;
import java.util.Calendar;

public class RegisterActivity extends AppCompatActivity {

    boolean success;
    private Context c;

    private EditText firstname, lastname, age, email, username, password, confirmpassword;
    private TextView birthdate, loginlink;
    private ImageButton btnBirthDate;
    private Button bregister;
    private Calendar cal;
    private int mYear, mMonth, mDay;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initRegisterForm();
    }

    private void initRegisterForm(){
        c = this;
        /** Mandatory fields to be completed by user in order to register **/
        firstname = findViewById(R.id.firstname);
        lastname = findViewById(R.id.lastname);
        birthdate = findViewById(R.id.tvBirthDate);
        btnBirthDate = findViewById(R.id.btnBirthDate);
        age = findViewById(R.id.age);
        email = findViewById(R.id.username);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        confirmpassword = findViewById(R.id.confirmpassword);
        bregister = findViewById(R.id.register);
        loginlink = findViewById(R.id.loginlink);

        firstname.setSelected(false);
        lastname.setSelected(false);
        age.setSelected(false);
        email.setSelected(false);
        username.setSelected(false);
        password.setSelected(false);
        confirmpassword.setSelected(false);

        linkToLoginActivity();
        addListenerForCalendar();
        bregister.setOnClickListener((v) -> registerUser());
    }

    private void linkToLoginActivity(){
        loginlink.setOnClickListener((l) -> {
            Intent loginintent = new Intent(RegisterActivity.this, LoginActivity.class);
            RegisterActivity.this.startActivity(loginintent);
        });
    }

    private void addListenerForCalendar(){
        btnBirthDate.setOnClickListener((v) -> {
            if (v == btnBirthDate) {
                // Get Current Date
                cal = Calendar.getInstance();
                mYear = cal.get(Calendar.YEAR);
                mMonth = cal.get(Calendar.MONTH);
                mDay = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(RegisterActivity.this,
                        (DatePicker view, int year, int monthOfYear, int dayOfMonth)->
                                birthdate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year), mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });
    }

    private void registerUser() {

        String[] userInput = getUserInput();
        boolean completed = completedMandatoryFields(userInput);
        if(!completed) {
            Toast.makeText(c, "Please fill in all fields!", Toast.LENGTH_SHORT).show();
            return;
        }
        boolean passMatch = checkPasswordMatch(userInput[6], userInput[7]);
        if(!passMatch){
            Toast.makeText(c, "Passwords do not match, please try again!", Toast.LENGTH_SHORT).show();
            return;
        }
        int age = Integer.parseInt(userInput[3]);
        LocalDate bdate = LocalDate.parse(userInput[2]);
        PostResult(userInput[0], userInput[1], userInput[5], userInput[6], userInput[4], age, bdate);
        /** Check if user exists already **/
        if(!success){
            Toast.makeText(c, "Registration failed, please try again!", Toast.LENGTH_SHORT).show();
        }
        else{
            /** Successful POST request, user will be redirected to LoginActivity **/
            Toast.makeText(c, "You have been successfully registered, please login", Toast.LENGTH_SHORT).show();
            RegisterActivity.this.startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            finish();
        }
    }

    private String[] getUserInput(){
        String[] userInput = new String[8];
        userInput[0]       = firstname.getText().toString();
        userInput[1]       = lastname.getText().toString();
        userInput[2]       = birthdate.getText().toString();
        userInput[3]       = age.getText().toString();
        userInput[4]       = email.getText().toString();
        userInput[5]       = username.getText().toString();
        userInput[6]       = password.getText().toString();
        userInput[7]       = confirmpassword.getText().toString();
        return userInput;
    }

    private boolean completedMandatoryFields(String[] userInput){
        if (userInput[5].length() == 0 || userInput[0].length() == 0 || userInput[1].length() == 0 || userInput[3].length() == 0
                || userInput[4].length() == 0 || userInput[6].length() == 0 || userInput[7].length() == 0 || userInput[2].length() == 0) {
            return false;
        }
        return true;
    }

    private boolean checkPasswordMatch(String password, String confirmPass){
        if (!password.equals(confirmPass)) {
            return false;
        }
        return true;
    }

    /**
     * Send user input for posting in the database and create a new user
     **/
    public void PostResult(String firstname, String lastname, String username, String password, String email, int age, LocalDate bdate) {
        User UserParameters = new User(age, email, firstname, lastname, password, username, bdate);
        RetrofitCalls retrofitCalls = new RetrofitCalls();
        success = retrofitCalls.postUser(UserParameters);
    }

    @Override
    public void onBackPressed() {
        Intent greetingIntent = new Intent(this, GreetingActivity.class);
        startActivity(greetingIntent);
        super.onBackPressed();
    }
}
