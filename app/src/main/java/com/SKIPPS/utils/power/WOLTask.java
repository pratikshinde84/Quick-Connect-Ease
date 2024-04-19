package com.SKIPPS.utils.power;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;


public class WOLTask extends AsyncTask<String, Void, Void> {

    private Context mContext;
    private Exception e;

    private boolean isSuccess = true;

    public WOLTask(Context context) {
        mContext = context;
    }

    @Override
    protected Void doInBackground(String... params) {
        String macAddress = params[0];
        String hostname = params[1];
        MagicPacket magicPacket = new MagicPacket(macAddress);
        try {
            for (int i = 0; i < 10; i++) {
                WOLManager.send(magicPacket, hostname);
            }
        } catch (Exception e) {
            this.e = e;
            isSuccess = false;
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        if (isSuccess)
            Toast.makeText(mContext, "Wake up successfully signals sent", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(mContext, "Failed:" + e.getMessage(), Toast.LENGTH_SHORT).show();
    }
}
