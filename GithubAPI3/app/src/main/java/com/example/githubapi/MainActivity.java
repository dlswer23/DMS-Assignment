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

        button = (button)findViewById(R.id.button)


    }
    public void button(View v){
        Intent intent = new Intent(getBaseContext(), UserActivity.class);

        startActivity(intent);

    }
        public void button2(View v){
            Intent intent = new Intent(getBaseContext(), FollowerActivity.class);

            startActivity(intent);

    }
    public void button3n(View v){
        Intent intent = new Intent(getBaseContext(), FollowerUrlActivity.class);

        startActivity(intent);

    }
}