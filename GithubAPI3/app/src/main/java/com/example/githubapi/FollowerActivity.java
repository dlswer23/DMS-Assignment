package com.example.githubapi;



import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;



public class FollowerActivity extends AppCompatActivity {

    private TextView result_follower;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_follower);

        result_follower =  findViewById(R.id.result_follower);

        OkHttpClient client = new OkHttpClient();
        String url ="https://api.github.com/users/dlswer23/followers";
        Request request = new Request.Builder()
                .url(url)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    final String myResponse = response.body().string();

                    ObjectMapper mapper = new ObjectMapper();
                    final List<HashMap<String, Object>> test =
                            mapper.readValue(myResponse, new TypeReference<List<Map<String, Object>>>() {});

                    FollowerActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            for (HashMap<String, Object> map : test) {
                                result_follower.setText(result_follower.getText() +
                                        "이름: " + map.get("login") + "\n" +
                                        "주소: " + map.get("url") + "\n\n"
                                );
                            }
                        }
                    });
                }
            }
        });

    }
}