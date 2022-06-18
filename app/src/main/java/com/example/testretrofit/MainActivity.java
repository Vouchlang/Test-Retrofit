package com.example.testretrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Tag;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private Button getData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getData = findViewById(R.id.getData);
        getData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Methods methods = RetrofitClient.getRetrofitInstance().create(Methods.class);
                Call<Model> call = methods.getAllData();

                call.enqueue(new Callback<Model>() {
                    @Override
                    public void onResponse(Call<Model> call, Response<Model> response) {

                        Log.e(TAG, "onResponse: code: " + response.code());

                        ArrayList<Model.data> data = response.body().getData();

                        for(Model.data data1 : data){
                            Log.e(TAG, "onResponse: id: " + data1.getId());
                            Log.e(TAG, "onResponse: email: " + data1.getEmail());
                            Log.e(TAG, "onResponse: first_name: " + data1.getFirst_name());
                            Log.e(TAG, "onResponse: last_name: " + data1.getLast_name());
                            Log.e(TAG, "onResponse: avatar: " + data1.getAvatar());
                        }

                    }

                    @Override
                    public void onFailure(Call<Model> call, Throwable t) {

                        Log.e(TAG, "onFailure: " + t.getMessage());

                    }
                });
            }
        });

    }
}