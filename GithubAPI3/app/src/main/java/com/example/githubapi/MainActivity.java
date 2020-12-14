package com.example.githubapi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;


public class MainActivity extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



    }

    public void button(View v){
        Intent intent = new Intent(getBaseContext(), UserActivity.class);

        startActivity(intent);

    }
        public void button2(View v){
            Intent intent = new Intent(getBaseContext(), FollowerActivity.class);

            startActivity(intent);

    }
    public void button3(View v){
        Intent intent = new Intent(getBaseContext(), FollowingActivity.class);

        startActivity(intent);

    }

}