package com.SKIPPS.quick_connect_ease;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.biometric.BiometricManager;
import androidx.biometric.BiometricPrompt;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.SKIPPS.utils.power.WOLTask;
import com.SKIPPS.utils.shared_data.SharedPreferenceManager;
import com.SKIPPS.utils.ssh.SSHInitTask;
import com.example.secureserver.R;
import com.google.android.material.textfield.TextInputEditText;

import java.util.concurrent.Executor;

public class LoginActivity extends AppCompatActivity {
    Button btn_login, btn_fingerprint;
    BiometricPrompt biometricPrompt;
    BiometricPrompt.PromptInfo promptInfo;
    TextInputEditText inputEditText;
    LinearLayout main_layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btn_login = findViewById(R.id.btn_login);
        btn_fingerprint = findViewById(R.id.btn_fingerprint);
        inputEditText = findViewById(R.id.et_app_pass);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
// uncomment kara, just testing sathi logic gayab karel ahie
//                String password = inputEditText.getText().toString();
//                String set_password = SharedPreferenceManager.readString(LoginActivity.this, "app_password", "");
//                if (set_password.equals(password)) {
                Intent i = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(i);
                finish();
                new SSHInitTask(LoginActivity.this).execute();

//                Toast.makeText(getApplicationContext(), "Login success", Toast.LENGTH_SHORT).show();
//
//                } else {
//                    Toast.makeText(getApplicationContext(), "Login Failed", Toast.LENGTH_SHORT).show();
//                }
            }
        });

        btn_fingerprint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BiometricManager biometricManager = BiometricManager.from(LoginActivity.this);
                switch (biometricManager.canAuthenticate()) {
                    case BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE:
                        Toast.makeText(getApplicationContext(), "Device does not have figerprint", Toast.LENGTH_SHORT).show();
                    case BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE:
                        Toast.makeText(getApplicationContext(), "Not working", Toast.LENGTH_SHORT).show();
                    case BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED:
                        Toast.makeText(getApplicationContext(), "Fingerprint is not set", Toast.LENGTH_SHORT).show();
                }

                Executor executor = ContextCompat.getMainExecutor(LoginActivity.this);

                biometricPrompt = new BiometricPrompt(LoginActivity.this, executor, new BiometricPrompt.AuthenticationCallback() {
                    @Override
                    public void onAuthenticationError(int errorCode, @NonNull CharSequence errString) {
                        super.onAuthenticationError(errorCode, errString);
                    }

                    @Override
                    public void onAuthenticationSucceeded(@NonNull BiometricPrompt.AuthenticationResult result) {
                        super.onAuthenticationSucceeded(result);
                        Toast.makeText(getApplicationContext(), "Login success", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(i);
                        finish();

                        new SSHInitTask(LoginActivity.this).execute();
                    }

                    @Override
                    public void onAuthenticationFailed() {
                        super.onAuthenticationFailed();
                    }
                });

                promptInfo = new BiometricPrompt.PromptInfo.Builder().setTitle("Quick Connect Ease")
                        .setDescription("Use Fingerprint to login")
                        .setDeviceCredentialAllowed(true).build();
                biometricPrompt.authenticate(promptInfo);
            }
        });

    }
}