package com.SKIPPS.utils.ssh;

import com.jcraft.jsch.*;

public class SSHManager {

    private static Session session;

    public static Session getSession() {
        return session;
    }

    public static void setSession(Session session) {
        SSHManager.session = session;
    }

    public static void createSession(String host, String username, String password, int port) throws Exception {
        JSch jsch = new JSch();
        session = jsch.getSession(username, host, port);
        session.setPassword(password);
        session.setConfig("StrictHostKeyChecking", "no");
    }

    public static void connect() throws Exception {
        if (session != null && !session.isConnected()) {
            session.connect();
        }
    }

    public static void disconnect() {
        if (session != null && session.isConnected()) {
            session.disconnect();
        }
    }
}
