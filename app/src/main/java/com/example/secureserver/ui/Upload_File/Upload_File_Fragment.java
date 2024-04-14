package com.example.secureserver.ui.Upload_File;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.OpenableColumns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.SKIPPS.utils.files.UploadTask;
import com.example.secureserver.R;

import java.io.File;
import java.util.Collections;

public class Upload_File_Fragment extends Fragment {

    private static final int PICK_FILE_REQUEST_CODE = 100;

    Button btn_upload, btn_select;
    ImageView iv_img;
    ListView listView_upload;
    private Uri selectedFileUri;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_upload_file, container, false);

        btn_upload = root.findViewById(R.id.btn_upload);
        btn_select = root.findViewById(R.id.btn_select_file);
        listView_upload = root.findViewById(R.id.upload_listview);
        iv_img = root.findViewById(R.id.iv_img);
        btn_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFilePicker();
            }
        });
        btn_upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedFileUri != null) {
                    iv_img.setImageResource(R.drawable.baseline_downloading_24);
                    UploadTask uploadTask = new UploadTask(requireContext(), iv_img);
                    uploadTask.execute(selectedFileUri);
                } else {
                    Toast.makeText(requireContext(), "Please select a file first", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return root;
    }

    private void openFilePicker() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");
        startActivityForResult(intent, PICK_FILE_REQUEST_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_FILE_REQUEST_CODE && resultCode == Activity.RESULT_OK && data != null) {
            selectedFileUri = data.getData();

            if (selectedFileUri != null) {
                String filename = getFilenameFromUri(selectedFileUri);
                listView_upload.setAdapter(new ArrayAdapter<>(requireContext(), android.R.layout.simple_list_item_1, Collections.singletonList(filename)));

            } else {
                Toast.makeText(requireContext(), "Failed to retrieve file", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private String getFilenameFromUri(Uri uri) {
        String filename = null;
        if (uri != null) {
            if ("content".equalsIgnoreCase(uri.getScheme())) {
                Cursor cursor = null;
                try {
                    cursor = requireContext().getContentResolver().query(uri, null, null, null, null);
                    if (cursor != null && cursor.moveToFirst()) {
                        int displayNameIndex = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME);
                        if (displayNameIndex != -1) {
                            filename = cursor.getString(displayNameIndex);
                        }
                    }
                } finally {
                    if (cursor != null) {
                        cursor.close();
                    }
                }
            } else if ("file".equalsIgnoreCase(uri.getScheme())) {
                filename = new File(uri.getPath()).getName();
            }
        }
        return filename;
    }

}
