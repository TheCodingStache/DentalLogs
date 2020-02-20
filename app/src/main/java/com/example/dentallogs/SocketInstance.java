package com.example.dentallogs;

import android.app.Application;

import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;

import java.net.URISyntaxException;

public class SocketInstance extends Application {
    private Socket iSocket;
    private static final String URL = "http://your_socket_connection_url.com";
    private String authToken = "qdsdsfskfkfk";
    @Override
    public void onCreate() {
        super.onCreate();
        try {
            IO.Options opts = new IO.Options();
            opts.query = "auth_token=" + authToken;
            iSocket = IO.socket(URL, opts);

        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

    }

    public Socket getSocketInstance() {
        return iSocket;
    }
}