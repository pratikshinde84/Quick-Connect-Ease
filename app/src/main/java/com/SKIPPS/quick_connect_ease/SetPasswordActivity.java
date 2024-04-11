package com.SKIPPS.quick_connect_ease;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.secureserver.R;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Set;

public class SetPasswordActivity extends AppCompatActivity {
    Button btn_save_password;
    TextInputLayout et_confirm_password;
    EditText enterPasswordEditText;

    TextInputLayout et_enter_password;
    EditText confirmPasswordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_password);

        et_confirm_password = findViewById(R.id.et_enter_password);
        enterPasswordEditText = et_confirm_password.getEditText();

        et_enter_password = findViewById(R.id.et_confirm_password);
        confirmPasswordEditText = et_enter_password.getEditText();

        btn_save_password = findViewById(R.id.btn_save_password);

        btn_save_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //piyush bhai password ko save karo ya fir uska balatkar karo
                Intent i = new Intent(SetPasswordActivity.this, LoginActivity.class);
                startActivity(i);
                finish();
            }
        });
    }
}