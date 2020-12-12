package com.example.githubapi;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class UserActivity extends AppCompatActivity {

private TextView mTestViewResult;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        mTestViewResult = findViewById(R.id.ID);


        OkHttpClient client = new OkHttpClient();
        String url ="https://api.github.com/users/dlswer23";
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
                            final HashMap<String, Object> info = mapper.readValue(myResponse, new TypeReference<Map<String, Object>>() {});

                            info.get("login");
                            info.get("name");
                            info.get("company");
                            info.get("url");

                            System.out.println(info.get("login"));
                            System.out.println(info.get("name"));
                            System.out.println(info.get("company"));
                            System.out.println(info.get("url"));


                            UserActivity.this.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    mTestViewResult.setText(
                                            "로그인: " + info.get("login").toString() + "\n" +
                                            "이름: " + info.get("name").toString() + "\n" +
                                                    "소속: " + info.get("company").toString() + "\n" +
                                                    "URL: " + info.get("url").toString()

                                    );
                                }
                            });
                        }
                    }
            });
    }
}