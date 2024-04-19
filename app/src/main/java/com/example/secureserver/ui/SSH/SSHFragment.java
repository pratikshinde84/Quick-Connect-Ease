package com.example.secureserver.ui.SSH;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.SKIPPS.utils.ssh.SSHManager;
import com.example.secureserver.R;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class SSHFragment extends Fragment {

    private EditText commandInputEditText;
    private TextView outputTextView;
    private Button sendCommandButton;

    private Session session;
    private Channel channel;
    private InputStream inputStream;
    private OutputStream outputStream;

    private Handler handler;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_ssh, container, false);

        commandInputEditText = root.findViewById(R.id.edittext_command_input);
        outputTextView = root.findViewById(R.id.textview_output);
        sendCommandButton = root.findViewById(R.id.button_send_command);

        handler = new Handler(Looper.getMainLooper());

        sendCommandButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String command = commandInputEditText.getText().toString().trim();
                if (!command.isEmpty()) {
                    executeCommand(command);
                } else {
                    Toast.makeText(getContext(), "Please enter a command", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return root;
    }

    private void executeCommand(final String command) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    session = SSHManager.getSession();
                    if (session == null || !session.isConnected()) {
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                outputTextView.setText("Session not available or not connected.\n");
                            }
                        });
                        return;
                    }
                    channel = session.openChannel("exec");
                    ((com.jcraft.jsch.ChannelExec) channel).setCommand(command);
                    channel.setInputStream(null);
                    ((com.jcraft.jsch.ChannelExec) channel).setErrStream(System.err);
                    inputStream = channel.getInputStream();
                    outputStream = channel.getOutputStream();
                    channel.connect();

                    byte[] buffer = new byte[1024];
                    int bytesRead;
                    final StringBuilder commandOutput = new StringBuilder();
                    while ((bytesRead = inputStream.read(buffer)) != -1) {
                        commandOutput.append(new String(buffer, 0, bytesRead));
                    }
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            outputTextView.setText(commandOutput.toString());
                        }
                    });
                } catch (JSchException | IOException e) {
                    e.printStackTrace();
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            outputTextView.setText("Error executing command: " + e.getMessage() + "\n");
                        }
                    });
                }
            }
        }).start();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (channel != null) {
            channel.disconnect();
        }
        try {
            if (inputStream != null) {
                inputStream.close();
            }
            if (outputStream != null) {
                outputStream.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
