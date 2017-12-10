package com.gnt.moviesapp;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.widget.ImageButton;
import android.widget.Toast;

import com.gnt.appobjects.UserDto;
import com.gnt.utils.UserSessionManager;
import com.gnt.utils.Utils;

import java.util.HashMap;

public class HomeActivity extends AppCompatActivity {

    private Context c;
    private Toolbar toolbar;
    private UserSessionManager sessionManager;
    private UserDto user;
    private FragmentManager manager;
    private FragmentTransaction transaction;
    private ImageButton ibhome, ibmovies, ibshows, ibuser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        init();

        toolbar = findViewById(R.id.menuToolbar);
        setSupportActionBar(toolbar);
        manager = getFragmentManager();
        transaction = manager.beginTransaction();

        handleMainToolbar();

        if(savedInstanceState == null) {
            startHomeFragment();
        }
    }

    private void init(){
        c=this;
        user = new UserDto();
        sessionManager = new UserSessionManager(HomeActivity.this);
        sessionManager.checkLogin();
        getUserData();
    }

    public void handleMainToolbar(){
        initToolbarButtons();
        ibhome.setOnClickListener((l)->startHomeFragment());
        ibmovies.setOnClickListener((l)->startMoviesFragment());
        ibshows.setOnClickListener((l)->startShowsFragment());
        ibuser.setOnClickListener((l)->startUserFragment());
    }

    private void initToolbarButtons(){
        ibhome = this.findViewById(R.id.home);
        ibmovies = this.findViewById(R.id.movies);
        ibshows = this.findViewById(R.id.shows);
        ibuser = this.findViewById(R.id.user);
    }

    private void startHomeFragment(){
        transaction.replace(R.id.fragmentLayout, HomeFragment.newInstance(user.getUsername(), user.getToken()));
        transaction.commit();
    }

    private void startMoviesFragment() {
        transaction.replace(R.id.fragmentLayout, MoviesFragment.newInstance()).commit();
    }

    private void startShowsFragment() {
        transaction.replace(R.id.fragmentLayout, ShowsFragment.newInstance()).commit();
    }

    private void startUserFragment() {
        transaction.replace(R.id.fragmentLayout, UserFragment.newInstance()).commit();
    }

    private void getUserData(){
        HashMap<String, String> userData = sessionManager.getUserDetails();
        user.setUsername(userData.get(UserSessionManager.KEY_NAME));
        user.setToken(userData.get(UserSessionManager.KEY_TOKEN));
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onRestart() {
        init();
        super.onRestart();
    }

    /** When the app is minimized and then reused, user can continue from where he left the app */
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            moveTaskToBack(true);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }

}
