package com.example.nafees.matcode;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void Profile(View v){ startActivity(new Intent(MainActivity.this,Profile.class)); }

    public void Solve (View v){
        startActivity(new Intent(MainActivity.this,Problems.class));
    }

    public void Code (View v){
        startActivity(new Intent(MainActivity.this,Editor.class));
    }

    public void Leaderboard(View v){
        startActivity(new Intent(MainActivity.this,Leaderboard.class));
    }
}
