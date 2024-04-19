package com.SKIPPS.utils.files;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.EditText;
import android.widget.Toast;

import com.SKIPPS.utils.shared_data.SharedPreferenceManager;
import com.SKIPPS.utils.ssh.SSHManager;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

public class HTTPDownloadTask extends AsyncTask<String, Void, Void> {

    private final Context mContext;
    private final EditText mEditText;
    private Exception mException;
    private boolean mIsSuccess = true;

    public HTTPDownloadTask(Context context, EditText editText) {
        mContext = context;
        mEditText = editText;
    }

    @Override
    protected Void doInBackground(String... params) {
        try {
            String password = SharedPreferenceManager.readString(mContext, "password", "");
            String filePath = params[0];
            Channel channel = SSHManager.getSession().openChannel("exec");
            String command = "cat " + filePath;
            ((ChannelExec) channel).setCommand("sudo -S -p '' " + command);
            InputStream in = channel.getInputStream();
            OutputStream out = channel.getOutputStream();
            ((ChannelExec) channel).setErrStream(System.err);
            channel.connect();
            out.write((password + "\n").getBytes());
            out.flush();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            StringBuilder content = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
            reader.close();
            out.close();
            in.close();
            channel.disconnect();

            if (mEditText != null) {
                mEditText.post(() -> mEditText.setText(content.toString()));
            }
        } catch (Exception e) {
            e.printStackTrace();
            mException = e;
            mIsSuccess = false;
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        if (mIsSuccess) {
            Toast.makeText(mContext, "File downloaded successfully", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(mContext, "Failed: " + mException.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
