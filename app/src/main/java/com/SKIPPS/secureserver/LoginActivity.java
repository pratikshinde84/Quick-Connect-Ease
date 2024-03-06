package com.SKIPPS.secureserver;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.secureserver.R;
import com.jcraft.jsch.*;
public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }
}