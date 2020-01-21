package com.example.dentallogs;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import de.hdodenhof.circleimageview.CircleImageView;

public class LabSelectionActivity extends AppCompatActivity {
    CircleImageView lab1;
    CircleImageView lab2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_selection);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        lab1 = findViewById(R.id.lab1);
        lab2 = findViewById(R.id.lab2);
        lab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent lab1 = new Intent(LabSelectionActivity.this, MenuSelectionActivity.class);
                startActivity(lab1);
            }
        });
        lab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent lab2 = new Intent(LabSelectionActivity.this, MenuSelectionActivity.class);
            }
        });
    }
}
