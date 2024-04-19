package com.example.secureserver.ui.Power_Management;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.SKIPPS.quick_connect_ease.ConfigActivity;
import com.SKIPPS.utils.power.SleepTask;
import com.SKIPPS.utils.power.WOLTask;
import com.SKIPPS.utils.shared_data.SharedPreferenceManager;
import com.example.secureserver.R;


public class Power_Mgmt_Fragment extends Fragment {

    private Button btn_power_off;
    private Button btn_power_on;
    private Button btn_auto_sleep;
    private static boolean isAutoSleep = false;
    TextView tv_con;

    public static Power_Mgmt_Fragment newInstance() {
        return new Power_Mgmt_Fragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_power_mgmt, container, false);

        btn_power_off = root.findViewById(R.id.btn_power_off);
        btn_power_on = root.findViewById(R.id.btn_power_on);
        btn_auto_sleep = root.findViewById(R.id.btn_auto_sleep);
        tv_con = root.findViewById(R.id.tv_con);
        String hostname = SharedPreferenceManager.readString(getContext(), "ip_address", "");
        String username = SharedPreferenceManager.readString(getContext(), "username", "");

        if (hostname.isEmpty() || username.isEmpty()) {
            tv_con.setText("Configuration required");
        } else {
            tv_con.setText("Server at:\n" + username + "@" + hostname);
        }
        btn_power_off.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new SleepTask(Power_Mgmt_Fragment.this.getContext()).execute();
            }
        });

        btn_power_on.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String macAddress = SharedPreferenceManager.readString(getContext(), "mac_address", "");
                String broadcastAddress = SharedPreferenceManager.readString(getContext(), "ip_address", "");
                WOLTask task = new WOLTask(Power_Mgmt_Fragment.this.getContext());
                task.execute(macAddress, broadcastAddress);
            }
        });
        btn_auto_sleep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isAutoSleep = !isAutoSleep;
                if (isAutoSleep) {
                    Toast.makeText(root.getContext(), "Server will auto sleep after work", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(root.getContext(), "Server will not sleep after work", Toast.LENGTH_SHORT).show();
                }

            }
        });

        return root;
    }

    public static boolean getAutoSleep() {
        return isAutoSleep;
    }
}
