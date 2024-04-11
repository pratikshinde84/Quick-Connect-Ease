package com.example.secureserver.ui.Power_Management;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.example.secureserver.R;

public class Power_Mgmt_Fragment extends Fragment {

    private Button btn_power_off;
    private Button btn_power_on;

    public static Power_Mgmt_Fragment newInstance() {
        return new Power_Mgmt_Fragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_power_mgmt, container, false);

        btn_power_off = root.findViewById(R.id.btn_power_off);
        btn_power_on = root.findViewById(R.id.btn_power_on);

        btn_power_off.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //piyush bhai power off ka code
            }
        });

        btn_power_on.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // piyush bhai power on ka code
            }
        });

        return root;
    }
}
