package com.SKIPPS.utils.ssh;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.SKIPPS.utils.shared_data.SharedPreferenceManager;

public class SSHInitTask extends AsyncTask<Void, Void, Void> {

    private final Context mContext;
    private Exception e;
    private boolean isSuccess = true;

    public SSHInitTask(Context context) {

        mContext = context;
    }


    @Override
    protected Void doInBackground(Void... voids) {
        try {
            String ip = SharedPreferenceManager.readString(mContext, "ip_address", "");
            int port = Integer.parseInt(SharedPreferenceManager.readString(mContext, "port", ""));
            String username = SharedPreferenceManager.readString(mContext, "username", "");
            String password = SharedPreferenceManager.readString(mContext, "password", "");
            SSHManager.createSession(ip, username, password, port);
            SSHManager.connect();

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
            Toast.makeText(mContext, "Auto Config Success", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(mContext, "Auto Config Failed:" + e.getMessage(), Toast.LENGTH_SHORT).show();
    }

}
