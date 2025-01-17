package com.example.dentallogs.Chat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.example.dentallogs.R;

public class ChatActivity extends AppCompatActivity {

    private EditText nickname;
    public static final String NICKNAME = "dmspallas";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        //call UI component  by id
        Button btn = findViewById(R.id.enterchat);
        nickname = findViewById(R.id.nickname);

        btn.setOnClickListener(v -> {
            //if the nickname is not empty go to chat box activity and add the nickname to the intent extra
            if(!nickname.getText().toString().isEmpty()){
                Intent i  = new Intent(ChatActivity.this,ChatBoxActivity.class);
                //retrieve nickname from text view and add it to intent extra
                i.putExtra(NICKNAME,nickname.getText().toString());

                startActivity(i);
            }
        });

    }
}
