package com.example.secureserver.ui.SSH;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.secureserver.R;

public class SSHFragment extends Fragment {

    private EditText terminal;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_ssh, container, false);
        terminal = root.findViewById(R.id.edittext_terminal_output);
        terminal.append("Connecting...\n");


        return root;
    }

}
