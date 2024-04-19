package com.SKIPPS.utils.files;

import android.content.Context;

import com.SKIPPS.utils.shared_data.SharedPreferenceManager;
import com.example.secureserver.R;

import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.OpenableColumns;
import android.widget.ImageView;
import android.widget.Toast;

import com.SKIPPS.utils.ssh.SSHManager;
import com.jcraft.jsch.ChannelSftp;

import java.io.File;
import java.io.InputStream;

public class UploadTask extends AsyncTask<Uri, Void, Void> {
    private Context context;
    private ImageView imageView;
    private Exception e;
    private boolean isSuccess = true;

    public UploadTask(Context context, ImageView imageView) {
        this.context = context;
        this.imageView = imageView;
    }

    @Override
    protected Void doInBackground(Uri... uris) {
        if (uris.length > 0) {
            Uri selectedFileUri = uris[0];
            try {
                String filename = getFilenameFromUri(selectedFileUri);

                InputStream inputStream = context.getContentResolver().openInputStream(selectedFileUri);
                if (inputStream != null) {
                    ChannelSftp channel = (ChannelSftp) SSHManager.getSession().openChannel("sftp");
                    channel.connect();
                    String remotePath = SharedPreferenceManager.readString(context, "path", "/home/");
                    String remoteFileName = filename;

                    channel.cd(remotePath);
                    channel.put(inputStream, remoteFileName);

                    channel.disconnect();
                    inputStream.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
                this.e = e;
                isSuccess = false;
            }
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        if (isSuccess) {
            imageView.setImageResource(R.drawable.baseline_cloud_done_24);
            Toast.makeText(context, "File uploaded successfully", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Error" + e.getMessage(), Toast.LENGTH_SHORT).show();
            imageView.setImageResource(R.drawable.baseline_cancel_24);
        }
    }

    private String getFilenameFromUri(Uri uri) {
        String filename = "";
        if (uri.getScheme().equals("content")) {
            Cursor cursor = context.getContentResolver().query(uri, null, null, null, null);
            if (cursor != null && cursor.moveToFirst()) {
                int nameIndex = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME);
                if (nameIndex != -1) {
                    filename = cursor.getString(nameIndex);
                }
                cursor.close();
            }
        } else if (uri.getScheme().equals("file")) {
            filename = new File(uri.getPath()).getName();
        }
        return filename;
    }
}
