package com.example.dentallogs;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dentallogs.Model.MessageModel;
import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class ChatBoxActivity extends AppCompatActivity {
    public RecyclerView mRecyclerView;
    public List<MessageModel> MessageList;
    public ChatBoxAdapter chatBoxAdapter;
    public EditText messageText;
    public Button send;
    //declare socket object
    private Socket socket;
    public String Nickname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_box);
        messageText = findViewById(R.id.message);
        send = findViewById(R.id.send);

        Nickname = getIntent().getExtras().getString(ChatActivity.NICKNAME);
        try {
            String URL = "http://192.168.2.66:5000";
            socket = IO.socket(URL);
            socket.connect();
            socket.emit("chatmessage", Nickname);
        } catch (URISyntaxException e) {
            e.printStackTrace();

        }
        MessageList = new ArrayList<>();
        mRecyclerView = findViewById(R.id.messagelist);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        // message send action
        send.setOnClickListener(v -> {
            //retrieve the nickname and the message content and fire the event messagedetection
            if (!messageText.getText().toString().isEmpty()) {
                socket.emit("messagedetection", Nickname, messageText.getText().toString());
                messageText.setText(" ");
            }
        });

        //implementing socket listeners
        socket.on("userjoinedthechat", args -> runOnUiThread(() -> {
            String data = (String) args[0];
            Toast.makeText(ChatBoxActivity.this, data, Toast.LENGTH_SHORT).show();
        }));
        socket.on("userdisconnect", args -> runOnUiThread(() -> {
            String data = (String) args[0];
            Toast.makeText(ChatBoxActivity.this, data, Toast.LENGTH_SHORT).show();

        }));
        socket.on("message", args -> runOnUiThread(new Runnable() {
            @Override
            public void run() {
                JSONObject data = (JSONObject) args[0];
                try {
                    //extract data from fired event
                    String nickname = data.getString("senderNickname");
                    String message = data.getString("message");
                    // make instance of message
                    MessageModel messageModel = new MessageModel(nickname, message);
                    //add the message to the messageList
                    MessageList.add(messageModel);
                    // add the new updated list to the adapter
                    chatBoxAdapter = new ChatBoxAdapter(MessageList);
                    // notify the adapter to update the recycler view
                    chatBoxAdapter.notifyDataSetChanged();
                    //set the adapter for the recycler view
                    mRecyclerView.setAdapter(chatBoxAdapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        socket.disconnect();
    }
}