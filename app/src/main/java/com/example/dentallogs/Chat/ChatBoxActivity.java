package com.example.dentallogs.Chat;

import android.graphics.Color;
import android.os.Bundle;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dentallogs.Adapters.ChatBoxAdapter;
import com.example.dentallogs.Model.MessageModel;
import com.example.dentallogs.R;
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
    public String username;
    public String message;
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_box);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        messageText = findViewById(R.id.message);
        send = findViewById(R.id.send);
        username = (String) getIntent().getExtras().getString(ChatActivity.NICKNAME);
        try {
            String URL = "https://da1f736c.ngrok.io/";
            socket = IO.socket(URL);
            socket.connect();
            socket.emit("chatmessage", username);
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
            message = messageText.getText().toString().trim();
            //retrieve the nickname and the message content and fire the event messagedetection
            if (!messageText.getText().toString().isEmpty()) {
                socket.emit("messagedetection", messageText.getText().toString().trim());
                messageText.setText("");
                // make instance of message
                MessageModel messageModel = new MessageModel(message,username);
                //add the message to the messageList
                MessageList.add(messageModel);
                // add the new updated list to the adapter
                chatBoxAdapter = new ChatBoxAdapter(MessageList);
                // notify the adapter to update the recycler view
                chatBoxAdapter.notifyDataSetChanged();
                //set the adapter for the recycler view
                mRecyclerView.setAdapter(chatBoxAdapter);
                try  {
                    InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                } catch (Exception e) {

                }
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
        socket.on("receivemessage", args -> runOnUiThread(() -> {
            JSONObject data = (JSONObject) args[0];
            try {
                //extract data from fired event
//                String nickname = data.getString("username");
                String name = data.getString("senderName");
                String message = data.getString("message");
                // make instance of message
                MessageModel messageModel = new MessageModel(message, name);
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
        }));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        socket.disconnect();
    }
}