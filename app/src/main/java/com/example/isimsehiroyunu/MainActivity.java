package com.example.isimsehiroyunu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btnNormal, btnSureli, btncikis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnNormal = (Button) findViewById(R.id.btnNormal);
        btnSureli = (Button) findViewById(R.id.btnSureli);
        btncikis = (Button) findViewById(R.id.btnCikis);

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