package com.SKIPPS.quick_connect_ease;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.SKIPPS.utils.shared_data.SharedPreferenceManager;
import com.example.secureserver.R;

public class ConfigActivity extends AppCompatActivity {
    EditText et_ip, et_mac, et_port, et_username, et_password;
    Button btn_save, btn_adv_settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);
        et_ip = findViewById(R.id.et_ip_add);
        et_mac = findViewById(R.id.et_mac_add);
        et_port = findViewById(R.id.et_port_no);
        et_username = findViewById(R.id.et_username);
        et_password = findViewById(R.id.et_password);
        btn_save = findViewById(R.id.btn_save);
        btn_adv_settings = findViewById(R.id.btn_adv_settings);

        String ip = SharedPreferenceManager.readString(ConfigActivity.this, "ip_address", "");
        String mac = SharedPreferenceManager.readString(ConfigActivity.this, "mac_address", "");
        String port = SharedPreferenceManager.readString(ConfigActivity.this, "port", "");
        String username = SharedPreferenceManager.readString(ConfigActivity.this, "username", "");
        String password = SharedPreferenceManager.readString(ConfigActivity.this, "password", "");

        et_ip.setText(ip);
        et_mac.setText(mac);
        et_port.setText(port);
        et_username.setText(username);
        et_password.setText(password);

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ip = et_ip.getText().toString();
                String mac = et_mac.getText().toString();
                String port = et_port.getText().toString();
                String username = et_username.getText().toString();
                String password = et_password.getText().toString();

                SharedPreferenceManager.writeString(ConfigActivity.this, "ip_address", ip);
                SharedPreferenceManager.writeString(ConfigActivity.this, "mac_address", mac);
                SharedPreferenceManager.writeString(ConfigActivity.this, "port", port);
                SharedPreferenceManager.writeString(ConfigActivity.this, "username", username);
                SharedPreferenceManager.writeString(ConfigActivity.this, "password", password);

                Toast.makeText(ConfigActivity.this, "Settings saved successfully", Toast.LENGTH_SHORT).show();
            }
        });

        btn_adv_settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ConfigActivity.this, AdvSettingsActivity.class);
                startActivity(i);
                finish();
            }
        });
    }
}