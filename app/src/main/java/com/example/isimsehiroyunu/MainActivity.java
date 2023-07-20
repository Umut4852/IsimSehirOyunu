package com.example.isimsehiroyunu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private Button btnNormal, btnSureli, btncikis;
    private TextView txttarih;
    private Retrofit retrofit;
    private timeAPI timeAPI;
    private Call<timeTurkey> timeTurkeyCall;
    private String baseUrl = "https://worldtimeapi.org/api/timezone/";
    private timeTurkey timeTurkey;

    private void init() {
        txttarih = (TextView) findViewById(R.id.txttarih);
        btnNormal = (Button) findViewById(R.id.btnNormal);
        btnSureli = (Button) findViewById(R.id.btnSureli);
        btncikis = (Button) findViewById(R.id.btnCikis);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        setRetrofitSetting();

        btnNormal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, normalOyunActivity.class);
                startActivity(intent);
            }
        });
        btnSureli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, sureliOyunActivity.class);
                startActivity(intent);
            }
        });
        btncikis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btncikis();
            }
        });
    }

    private void setRetrofitSetting() {
        retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        timeAPI = retrofit.create(timeAPI.class);
        timeTurkeyCall = timeAPI.getTime();

        timeTurkeyCall.enqueue(new Callback<timeTurkey>() {
            @Override
            public void onResponse(Call<timeTurkey> call, Response<timeTurkey> response) {
                if (response.isSuccessful()) {
                    timeTurkey = response.body();

                    //retry mekanizması geliştirilecek

                    if (timeTurkey != null) {
                      //  txttarih.setText(timeTurkey.getDateTime());
                        txttarih.setText(timeTurkey.getDateTime().split("T")[0]);
                    }
                    else {
                        txttarih.setText("timeturkey null değer geldi");
                    }
                }else {
                    txttarih.setText("reponse do not update");
                }
            }

            @Override
            public void onFailure(Call<timeTurkey> call, Throwable t) {
                System.out.println(t.toString());
            }
        });
    }

    private void btncikis() {
        moveTaskToBack(true);
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(0);
    }

    @Override
    public void onBackPressed() {
        btncikis();
    }


}