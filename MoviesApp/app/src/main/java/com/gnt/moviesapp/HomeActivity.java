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
import com.gnt.utils.Session;
import com.gnt.utils.Utils;

public class HomeActivity extends AppCompatActivity {

    private Context c;
    private Toolbar toolbar;
    private Session sessionData;
    private UserDto user;
    private FragmentManager manager;
    private FragmentTransaction transaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        c=this;
        user = new UserDto();
        sessionData = Utils.getSessionData(HomeActivity.this);
        checkUserSession();
        user.setUsername(sessionData.getUsername());
        user.setToken(sessionData.getToken());

        toolbar = findViewById(R.id.menuToolbar);
        setSupportActionBar(toolbar);
        manager = getFragmentManager();
        transaction = manager.beginTransaction();

        handleMainToolbar();

        if(savedInstanceState == null) {
            startHomeFragment();
        }
    }

    protected void onResume() {
        super.onResume();

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

    private void checkUserSession(){
        if (!sessionData.getUserLoggedInState()) {
            Utils.logout(this);
            return;
        }
        if ((getIntent().getFlags() & Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT) != 0) {
            finish();
            return;
        }

        /** Check if token is expired **/
        if(Utils.isTokenExpired(user.getToken())){
            Toast.makeText(c, "Session is expired", Toast.LENGTH_SHORT).show();
            Utils.logout(this);
            finish();
            return;
        }
    }

    @Override
    protected void onRestart() {
        resetActivity();
        //adapter.notifyDataSetChanged();
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

    private void resetActivity() {
        Session sessionData = Utils.getSessionData(HomeActivity.this);
        if (!sessionData.getUserLoggedInState()) {
            Intent intent = new Intent(this, GreetingActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        }
    }
}
