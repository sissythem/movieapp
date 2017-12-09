package com.gnt.moviesapp;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageButton;

import com.gnt.utils.Session;
import com.gnt.utils.Utils;

public class HomeActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private Session sessionData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        toolbar = findViewById(R.id.menuToolbar);
        setSupportActionBar(toolbar);
        handleMainToolbar();

        if(savedInstanceState == null) {
            startHomeFragment();
        }
    }

    protected void onResume() {
        super.onResume();
        sessionData = Utils.getSessionData(HomeActivity.this);
    }

    public void handleMainToolbar(){
        ImageButton ibhome = this.findViewById(R.id.home);
        ImageButton ibmovies = this.findViewById(R.id.movies);
        ImageButton ibshows = this.findViewById(R.id.shows);
        ImageButton ibuser = this.findViewById(R.id.user);

        ibhome.setOnClickListener((l)->startHomeFragment());
        ibmovies.setOnClickListener((l)->startMoviesFragment());
        ibshows.setOnClickListener((l)->startShowsFragment());
        ibuser.setOnClickListener((l)->startUserFragment());
    }

    private void startHomeFragment(){
        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fragmentLayout, HomeFragment.newInstance());
        transaction.commit();
    }

    private void startMoviesFragment() {
        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fragmentLayout, MoviesFragment.newInstance());
        transaction.commit();
    }

    private void startShowsFragment() {
        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fragmentLayout, ShowsFragment.newInstance());
        transaction.commit();
    }

    private void startUserFragment() {
        if (!sessionData.getUserLoggedInState()) {
            FragmentManager manager = getFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.replace(R.id.fragmentLayout, LoginFragment.newInstance());
            transaction.commit();
        }
        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fragmentLayout, UserFragment.newInstance());
        transaction.commit();
    }

}
