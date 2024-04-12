package com.SKIPPS.quick_connect_ease;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.secureserver.R;

public class NotepadActivity extends AppCompatActivity {
    Button btn_upload;
    EditText et_notepad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notepad);
        et_notepad=findViewById(R.id.et_notepad);
        btn_upload=findViewById(R.id.btn_upload_notepad);

        btn_upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //html file upload karo yaha se
            }
        });
    }
}