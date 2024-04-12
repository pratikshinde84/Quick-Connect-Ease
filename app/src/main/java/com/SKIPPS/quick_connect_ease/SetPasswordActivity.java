package com.SKIPPS.quick_connect_ease;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.secureserver.R;
import com.google.android.material.textfield.TextInputLayout;

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

        et_confirm_password = findViewById(R.id.et_confirm_password);
        enterPasswordEditText = et_confirm_password.getEditText();

        et_enter_password = findViewById(R.id.et_enter_password);
        confirmPasswordEditText = et_enter_password.getEditText();

        btn_save_password = findViewById(R.id.btn_save_password);

        btn_save_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String password = enterPasswordEditText.getText().toString();
                String confirmPassword = confirmPasswordEditText.getText().toString();

                if (!isPasswordLengthValid(password))
                {
                    et_enter_password.setError("Password must be at least 8 characters long.");
                }
                else if (!containsLetter(password))
                {
                    et_enter_password.setError("Password must contain at least one letter.");
                }
                else if (!containsDigit(password))
                {
                    et_enter_password.setError("Password must contain at least one digit.");
                } else if (!containsSpecialCharacter(password))
                {
                    et_enter_password.setError("Password must contain at least one special character.");
                }
                else if (!password.equals(confirmPassword))
                {
                    et_confirm_password.setError("Passwords do not match.");
                }
                else
                {
                    Intent i = new Intent(SetPasswordActivity.this, LoginActivity.class);
                    startActivity(i);
                    finish();
                }
            }
        });
    }
    private boolean isPasswordLengthValid (String password){
        return password.length() >= 8;
    }

    private boolean containsLetter (String password){
        return password.matches(".*[a-zA-Z]+.*");
    }

    private boolean containsDigit (String password){
        return password.matches(".*\\d+.*");
    }

    private boolean containsSpecialCharacter (String password){
        return password.matches(".*[@#$%^&+=]+.*");
    }
}