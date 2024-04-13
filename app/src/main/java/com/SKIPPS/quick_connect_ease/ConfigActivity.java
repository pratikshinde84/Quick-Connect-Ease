package com.SKIPPS.quick_connect_ease;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.secureserver.R;

public class ConfigActivity extends AppCompatActivity {
    EditText et_ip,et_mac,et_port,et_username,et_password;
    Button btn_fetch,btn_adv_settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);
        et_ip=findViewById(R.id.et_ip_add);
        et_mac=findViewById(R.id.et_mac_add);
        et_port=findViewById(R.id.et_port_no);
        et_username=findViewById(R.id.et_username);
        et_password=findViewById(R.id.et_password);
        btn_fetch=findViewById(R.id.btn_fetch);
        btn_adv_settings=findViewById(R.id.btn_adv_settings);

        btn_fetch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //fetch kar bhai
            }
        });
        btn_adv_settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(ConfigActivity.this,AdvSettingsActivity.class);
                startActivity(i);
                finish();
            }
        });
    }
}