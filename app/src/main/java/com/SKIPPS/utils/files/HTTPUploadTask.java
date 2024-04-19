package com.SKIPPS.utils.files;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.EditText;
import android.widget.Toast;

import com.SKIPPS.utils.shared_data.SharedPreferenceManager;
import com.SKIPPS.utils.ssh.SSHManager;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.SftpException;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class HTTPUploadTask extends AsyncTask<String, Void, Void> {

    private final Context mContext;
    private final EditText mEditText;
    private Exception mException;
    private boolean mIsSuccess = true;

    public HTTPUploadTask(Context context, EditText editText) {
        mContext = context;
        mEditText = editText;
    }

    @Override
    protected Void doInBackground(String... params) {
        try {
            String password = SharedPreferenceManager.readString(mContext, "password", "");
            String filePath = params[0];
            String content = mEditText.getText().toString();

            Channel channel = SSHManager.getSession().openChannel("exec");
            String command = "echo '" + content + "' > " + filePath;
            ((ChannelExec) channel).setCommand("sudo -S -p '' " + command);
            OutputStream out = channel.getOutputStream();
            channel.connect();
            out.write((password + "\n").getBytes());
            out.flush();
            channel.disconnect();

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
            Toast.makeText(mContext, "File uploaded successfully", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(mContext, "Failed: " + mException.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
