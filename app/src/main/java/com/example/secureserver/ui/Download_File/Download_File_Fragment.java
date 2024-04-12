package com.example.secureserver.ui.Download_File;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
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

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class Download_File_Fragment extends Fragment {
    private ListView listView,lv_recently_downloaded;
    TextView tv_file_path;
    private ArrayAdapter<String> adapter;
    private ArrayAdapter<String> arrayAdapter;
    private String[] items = {"Sushil", "Pratik", "Piyush", "Shubham", "Ishan" ,"Krishna","Sushil"};
    private String[] item={"Pratik"};

    private static final int SAVE_FILE_REQUEST_CODE = 101;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_download_file, container, false);

        tv_file_path=root.findViewById(R.id.text_path_download);
        lv_recently_downloaded=root.findViewById(R.id.downloaded_file_listview);
        listView = root.findViewById(R.id.download_listview);
        adapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_list_item_1, items);
        arrayAdapter=new ArrayAdapter<>(requireContext(),android.R.layout.simple_list_item_1,items);
        listView.setAdapter(adapter);
        lv_recently_downloaded.setAdapter(arrayAdapter);
        return root;
    }

//    @Override
//    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//        showSaveFileDialog();
//    }
//
//    private void showSaveFileDialog() {
//        Intent intent = new Intent(Intent.ACTION_CREATE_DOCUMENT);
//        intent.addCategory(Intent.CATEGORY_OPENABLE);
//        intent.setType("text/plain");
//        intent.putExtra(Intent.EXTRA_TITLE, "my_text_file.txt");
//        startActivityForResult(intent, SAVE_FILE_REQUEST_CODE);
//    }
//
//    @Override
//    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == SAVE_FILE_REQUEST_CODE && resultCode == Activity.RESULT_OK && data != null) {
//            Uri uri = data.getData();
//            if (uri != null) {
//                saveFile(uri);
//            } else {
//                Toast.makeText(requireContext(), "Failed to save file", Toast.LENGTH_SHORT).show();
//            }
//        }
//    }
//
//    private void saveFile(Uri uri) {
//        try {
//            OutputStream outputStream = requireContext().getContentResolver().openOutputStream(uri);
//            if (outputStream != null) {
//                String fileContent = "This is a sample text file content.";
//                outputStream.write(fileContent.getBytes());
//                outputStream.close();
//                Toast.makeText(requireContext(), "File saved successfully", Toast.LENGTH_SHORT).show();
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//            Toast.makeText(requireContext(), "Failed to save file", Toast.LENGTH_SHORT).show();
//        }
//    }
}
