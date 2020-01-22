package com.example.hotel_personapp.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.hotel_personapp.R;

public class MainActivity extends AppCompatActivity {

    Button mbtnlogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mbtnlogin = findViewById(R.id.btn_login);
        mbtnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = null;
                intent = new Intent(MainActivity.this,IndexActivity.class);
                startActivity(intent);
            }
        });
    }
}
