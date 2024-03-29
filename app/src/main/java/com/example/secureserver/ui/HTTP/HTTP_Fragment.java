package com.example.secureserver.ui.HTTP;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.secureserver.R;

public class HTTP_Fragment extends Fragment {


    public static HTTP_Fragment newInstance() {
        return new HTTP_Fragment();
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root=inflater.inflate(R.layout.fragment_http,container,false);

        return root;
    }

}