package com.SKIPPS.quick_connect_ease;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.SKIPPS.utils.files.HTTPDownloadTask;
import com.SKIPPS.utils.files.HTTPUploadTask;
import com.example.secureserver.R;

public class NotepadActivity extends AppCompatActivity {
    Button btn_upload;
    EditText et_notepad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notepad);
        et_notepad = findViewById(R.id.et_notepad);
        btn_upload = findViewById(R.id.btn_upload_notepad);

        // Retrieve the file path from the intent's extras
        String filePath = getIntent().getStringExtra("file_path");
        if (filePath != null) {
            // Initiate the HTTPDownloadTask to download the content of the file
            new HTTPDownloadTask(NotepadActivity.this, et_notepad).execute(filePath);
        }

        // Set a click listener for the upload button
        btn_upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the file path from the intent's extras
                String filePath = getIntent().getStringExtra("file_path");
                if (filePath != null) {
                    // Initiate the HTTPUploadTask to upload the content of the EditText
                    new HTTPUploadTask(NotepadActivity.this, et_notepad).execute(filePath);
                }
            }
        });
    }
}
