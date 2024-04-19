package com.SKIPPS.utils.power;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.SKIPPS.utils.shared_data.SharedPreferenceManager;
import com.SKIPPS.utils.ssh.SSHManager;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;

import java.io.OutputStream;

public class SleepTask extends AsyncTask<Void, Void, Void> {

    private final Context mContext;
    private Exception e;
    private boolean isSuccess = true;

    public SleepTask(Context context) {

        mContext = context;
    }


    @Override
    protected Void doInBackground(Void... voids) {
        try {
            String password = SharedPreferenceManager.readString(mContext, "password", "");
            Channel channel;
            channel = SSHManager.getSession().openChannel("exec");
            String command = "systemctl suspend";
            ((ChannelExec) channel).setCommand("sudo -S -p '' " + command);
            OutputStream out = channel.getOutputStream();
            channel.connect();
            out.write((password + "\n").getBytes());
            out.flush();
            channel.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
            this.e = e;
            isSuccess = false;
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        if (isSuccess)
            Toast.makeText(mContext, "Server put to sleep/standby", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(mContext, "Failed:" + e.getMessage(), Toast.LENGTH_SHORT).show();
    }

}
