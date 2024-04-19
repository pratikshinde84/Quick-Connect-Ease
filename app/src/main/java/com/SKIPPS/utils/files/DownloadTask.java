package com.SKIPPS.utils.files;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;


import com.SKIPPS.utils.ssh.SSHManager;
import com.example.secureserver.R;
import com.jcraft.jsch.ChannelSftp;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

public class DownloadTask extends AsyncTask<String, Void, Void> {
    private Context context;
    private Uri destinationUri;
    private Exception e;
    private ImageView iv;
    private boolean isSuccess = true;

    public DownloadTask(Context context, Uri destinationUri, ImageView iv) {
        this.context = context;
        this.destinationUri = destinationUri;
        this.iv = iv;
    }


    @Override
    protected Void doInBackground(String... params) {
        String pathfilename = params[0];
        try {
            ChannelSftp channel = (ChannelSftp) SSHManager.getSession().openChannel("sftp");
            channel.connect();
            InputStream inputStream = channel.get(pathfilename);
            ByteArrayOutputStream byteOutputStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                byteOutputStream.write(buffer, 0, bytesRead);
            }
            byte[] bytes = byteOutputStream.toByteArray();

            byteOutputStream.close();
            inputStream.close();


            if (bytes != null) {
                FileOutputStream outputStream = (FileOutputStream) context.getContentResolver().openOutputStream(destinationUri);
                if (outputStream != null) {
                    outputStream.write(bytes);
                    outputStream.close();
                }
            }
            channel.disconnect();
        } catch (Exception e) {
            this.e = e;
            isSuccess = false;
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void unused) {
        super.onPostExecute(unused);
        if (isSuccess) {
            Toast.makeText(context, "File downloaded successfully", Toast.LENGTH_SHORT).show();
            iv.setImageResource(R.drawable.baseline_cloud_done_24);
        } else {
            Toast.makeText(context, "Error:" + e.getMessage(), Toast.LENGTH_SHORT).show();
            iv.setImageResource(R.drawable.baseline_cancel_24);
        }
    }
}
