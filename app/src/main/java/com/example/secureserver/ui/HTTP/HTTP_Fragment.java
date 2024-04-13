package com.example.secureserver.ui.HTTP;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.secureserver.R;

public class HTTP_Fragment extends Fragment {

    ListView lv_http;
    private ArrayAdapter<String> adapter;
    String arr[]={"Pratik","Pratik","Pratik","Pratik","Pratik","Pratik","Pratik"};
    private static final int PICK_FILE_REQUEST_CODE = 100;

    private TextView textView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_http, container, false);
        lv_http=root.findViewById(R.id.http_listview);
        textView = root.findViewById(R.id.text_http_fragment);
        adapter=new ArrayAdapter<>(requireContext(),android.R.layout.simple_list_item_1,arr);
        lv_http.setAdapter(adapter);
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
//                textView.setText(filePath);
//            } else {
//                Toast.makeText(requireContext(), "Failed to retrieve file", Toast.LENGTH_SHORT).show();
//            }
//        }
//    }
}