package com.example.secureserver.ui.Upload_File;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.secureserver.R;

public class Upload_File_Fragment extends Fragment {

    private static final int PICK_FILE_REQUEST_CODE = 100;

    private TextView textView;
    Button btn_upload;
    ListView listView_upload;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_upload_file, container, false);
        //textView = root.findViewById(R.id.text_home);
        btn_upload=root.findViewById(R.id.btn_upload);
        listView_upload=root.findViewById(R.id.upload_listview);

        btn_upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //teri file upload kar bhai
            }
        });
        return root;
    }

//    @Override
//    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//        openFileDialog();
//    }
//
//    private void openFileDialog() {
//        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
//        intent.setType("*/*");
//        startActivityForResult(intent, PICK_FILE_REQUEST_CODE);
//    }
//
//    @Override
//    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == PICK_FILE_REQUEST_CODE && resultCode == Activity.RESULT_OK && data != null) {
//            Uri selectedFileUri = data.getData();
//
//            if (selectedFileUri != null) {
//                String filePath = selectedFileUri.getPath();
////                textView.setText(filePath);
//            } else {
//                Toast.makeText(requireContext(), "Failed to retrieve file", Toast.LENGTH_SHORT).show();
//            }
//        }
//    }
}
