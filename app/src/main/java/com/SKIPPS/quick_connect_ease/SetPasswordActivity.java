package com.SKIPPS.quick_connect_ease;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import com.example.secureserver.R;
import com.google.android.material.textfield.TextInputLayout;

public class SetPasswordActivity extends AppCompatActivity {
    Button btn_save_password;
    EditText enterPasswordEditText;
    EditText confirmPasswordEditText;
    CheckBox cb_confirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_password);

        enterPasswordEditText = findViewById(R.id.et_enter_password_input);
        confirmPasswordEditText = findViewById(R.id.et_confirm_password_input);
        cb_confirm=findViewById(R.id.cb_confirm);

        btn_save_password = findViewById(R.id.btn_save_password);

        cb_confirm.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    enterPasswordEditText.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    confirmPasswordEditText.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
                else
                {
                    enterPasswordEditText.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    confirmPasswordEditText.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });
        btn_save_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String password = enterPasswordEditText.getText().toString();
                String confirmPassword = confirmPasswordEditText.getText().toString();

                if (enterPasswordEditText.getText().toString().isEmpty())
                {
                    enterPasswordEditText.setError("Please enter the password");
                }
                else if (password.length() < 8)
                {
                    enterPasswordEditText.setError("Password must be at least 8 characters long.");
                }
                else if (!password.matches(".*[a-zA-Z]+.*"))
                {
                    enterPasswordEditText.setError("Password must contain at least one letter.");
                }
                else if (!password.matches(".*\\d+.*"))
                {
                    enterPasswordEditText.setError("Password must contain at least one digit.");
                }
                else if (!password.matches(".*[@#$%^&+=]+.*"))
                {
                    enterPasswordEditText.setError("Password must contain at least one special character.");
                }
                else if (confirmPassword.isEmpty())
                {
                    confirmPasswordEditText.setError("Please enter the password again");
                }
                else if (!password.equals(confirmPassword))
                {
                    confirmPasswordEditText.setError("Passwords do not match.");
                }
                else
                {
                    enterPasswordEditText.setError(null);
                    confirmPasswordEditText.setError(null);
                    Intent i = new Intent(SetPasswordActivity.this, LoginActivity.class);
                    startActivity(i);
                    finish();
                }
            }
        });
    }
}
