package com.example.logintest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView tv_id, tv_pw;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("Login Result");

        Intent intent = getIntent();

        String userID = intent.getStringExtra("userID");
        String userPW = intent.getStringExtra("userPw");
        tv_id = findViewById(R.id.tv_id);
        tv_pw = findViewById(R.id.tv_pw);

        tv_id.setText(userID);
        tv_pw.setText(userPW);
    }
}