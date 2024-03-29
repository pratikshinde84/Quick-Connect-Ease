package com.example.secureserver.ui.Power_Management;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.secureserver.R;

public class Power_Mgmt_Fragment extends Fragment {
    Button btn_power_off, btn_power_on;

    public static Power_Mgmt_Fragment newInstance() {
        return new Power_Mgmt_Fragment();
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root=inflater.inflate(R.layout.fragment_power_mgmt,container,false);

        return root;
    }

}