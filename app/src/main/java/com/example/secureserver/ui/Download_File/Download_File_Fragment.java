package com.example.secureserver.ui.Download_File;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.SKIPPS.utils.files.DownloadTask;
import com.SKIPPS.utils.files.ListTask;
import com.example.secureserver.R;

public class Download_File_Fragment extends Fragment {
    private ListView listView;
    private static final int SAVE_FILE_REQUEST_CODE = 101;
    private TextView textPathDownload;
    private ImageButton btnPrevious;
    private ImageView iv_status;
    private TextView tv_recent;
    private String currentDirectory = "/home/piyush/";
    private String filenm;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_download_file, container, false);
        listView = root.findViewById(R.id.download_listview);
        textPathDownload = root.findViewById(R.id.text_path_download);
        btnPrevious = root.findViewById(R.id.btn_previous);
        tv_recent = root.findViewById(R.id.tv_recent);
        iv_status = root.findViewById(R.id.iv_img_download);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        textPathDownload.setText(currentDirectory);

        ListTask listTask = new ListTask(requireContext(), listView);
        listTask.execute(currentDirectory);

        listView.setOnItemClickListener((parent, view1, position, id) -> {
            String selectedItem = (String) parent.getItemAtPosition(position);
            if (isDirectory(selectedItem)) {
                String selectedDirectory = currentDirectory + selectedItem;
                new ListTask(requireContext(), listView).execute(selectedDirectory);
                currentDirectory = selectedDirectory;
                textPathDownload.setText(currentDirectory);
            } else {
                initiateDownload(selectedItem);
            }
        });

        btnPrevious.setOnClickListener(v -> {
            if (!currentDirectory.equals("/")) {
                currentDirectory = currentDirectory.substring(0, currentDirectory.lastIndexOf("/", currentDirectory.length() - 2) + 1);
                if (currentDirectory.isEmpty()) {
                    currentDirectory = "/";
                }
                textPathDownload.setText(currentDirectory);
                new ListTask(requireContext(), listView).execute(currentDirectory);
            }
        });

    }

    private boolean isDirectory(String item) {
        return item.endsWith("/");
    }

    private void initiateDownload(String selectedItem) {
        filenm = selectedItem;
        tv_recent.setText(selectedItem);
        showSaveFileDialog(selectedItem);
        iv_status.setImageResource(R.drawable.baseline_downloading_24);

        Toast.makeText(requireContext(), "Initiating download: " + selectedItem, Toast.LENGTH_SHORT).show();
    }

    private void showSaveFileDialog(String filename) {
        Intent intent = new Intent(Intent.ACTION_CREATE_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("*/*");
        intent.putExtra(Intent.EXTRA_TITLE, filename);
        startActivityForResult(intent, SAVE_FILE_REQUEST_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SAVE_FILE_REQUEST_CODE && resultCode == Activity.RESULT_OK && data != null) {
            Uri uri = data.getData();
            if (uri != null) {
                new DownloadTask(requireContext(), uri, iv_status).execute(currentDirectory + filenm);
                Toast.makeText(requireContext(), "Download Started", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(requireContext(), "Download Failed to start", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
