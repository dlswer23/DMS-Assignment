package com.example.githubapi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class SearchActivity extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
         final TextView resultSearch= findViewById(R.id.result_search);
         Button Button= findViewById(R.id.checkbutton);
        final EditText userEt = findViewById(R.id.checkbox);
        final OkHttpClient client = new OkHttpClient();

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://api.github.com/users/" + userEt.getText().toString();
                final Request request = new Request.Builder()
                        .url(url)
                        .build();

                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                        if (response.isSuccessful()) {
                            final String myResponse = response.body().string();

                            ObjectMapper mapper = new ObjectMapper();
                            mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
                            final List<HashMap<String, Object>> test =
                                    mapper.readValue(myResponse, new TypeReference<List<Map<String, Object>>>() {
                                    });

                            SearchActivity.this.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    for (HashMap<String, Object> map : test) {
                                        resultSearch.setText(
                                                "이름: " + map.get("login") + "\n" +
                                                        "깃허브 주소: " + map.get("url") + "\n" +
                                                        "팔로워 수: " + map.get("followers") + "\n" +
                                                        "팔로잉 수: " + map.get("following") + "\n"
                                        );
                                    }
                                }
                            });
                        }

                    }
                });
            }
        };
        Button.setOnClickListener(onClickListener);



    }
    public class User{
        public String login;
        public  String url;
        public  int followers;
        public  int following;
    }


}