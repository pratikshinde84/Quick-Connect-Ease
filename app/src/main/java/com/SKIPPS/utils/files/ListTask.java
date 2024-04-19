package com.SKIPPS.utils.files;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.SKIPPS.utils.ssh.SSHManager;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.Session;

import java.util.Vector;

public class ListTask extends AsyncTask<String, Void, Vector<ChannelSftp.LsEntry>> {
    private Context context;
    private ListView listView;
    Exception e;
    private boolean isSuccess = true;

    public ListTask(Context context, ListView listView) {
        this.context = context;
        this.listView = listView;
    }

    @Override
    protected Vector<ChannelSftp.LsEntry> doInBackground(String... params) {
        String directoryPath = params[0];
        try {
            Session session = SSHManager.getSession();
            ChannelSftp channel = (ChannelSftp) session.openChannel("sftp");
            channel.connect();
            Vector<ChannelSftp.LsEntry> entries = channel.ls(directoryPath);
            channel.disconnect();
            return entries;
        } catch (Exception e) {
            e.printStackTrace();
            this.e = e;
            isSuccess = false;
            return null;
        }
    }

    @Override
    protected void onPostExecute(Vector<ChannelSftp.LsEntry> entries) {
        super.onPostExecute(entries);
        if (isSuccess) {
            if (entries != null) {
                ArrayAdapter<String> adapter = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1);
                for (ChannelSftp.LsEntry entry : entries) {
                    String entryName = entry.getFilename();
                    if (!entryName.equals(".") && !entryName.equals("..")) {
                        if (entry.getAttrs().isDir()) {
                            entryName += "/";
                        }
                        adapter.add(entryName);
                    }
                }
                listView.setAdapter(adapter);
            } else {
                Toast.makeText(context, "Failed to fetch directory contents", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(context, "Error:" + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

}
