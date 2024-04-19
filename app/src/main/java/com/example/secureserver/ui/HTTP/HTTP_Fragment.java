package com.example.secureserver.ui.HTTP;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.SKIPPS.quick_connect_ease.NotepadActivity;
import com.SKIPPS.utils.files.ListTask;
import com.example.secureserver.R;
import com.google.android.material.badge.BadgeUtils;

public class HTTP_Fragment extends Fragment {

    private ListView lv_http;
    private ImageButton btnPrevious;
    private ArrayAdapter<String> adapter;
    private TextView textView;
    private String currentDirectory = "/var/www/html/";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_http, container, false);
        lv_http = root.findViewById(R.id.http_listview);
        textView = root.findViewById(R.id.text_http_fragment);
        btnPrevious = root.findViewById(R.id.btn_previous_http);

        ListTask listTask = new ListTask(requireContext(), lv_http);
        listTask.execute(currentDirectory);

        lv_http.setOnItemClickListener((parent, view1, position, id) -> {
            String selectedItem = (String) parent.getItemAtPosition(position);
            if (isDirectory(selectedItem)) {
                String selectedDirectory = currentDirectory + selectedItem;
                new ListTask(requireContext(), lv_http).execute(selectedDirectory);
                currentDirectory = selectedDirectory;
            } else {
                Intent intent = new Intent(requireContext(), NotepadActivity.class);
                intent.putExtra("file_path", currentDirectory + selectedItem);
                startActivity(intent);
            }
        });
        btnPrevious.setOnClickListener(v -> {
            if (!currentDirectory.equals("/")) {
                currentDirectory = currentDirectory.substring(0, currentDirectory.lastIndexOf("/", currentDirectory.length() - 2) + 1);
                if (currentDirectory.isEmpty()) {
                    currentDirectory = "/";
                }
                new ListTask(requireContext(), lv_http).execute(currentDirectory);
            }
        });

        return root;
    }

    private boolean isDirectory(String item) {
        return item.endsWith("/");
    }
}
