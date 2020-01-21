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
        lab1 = findViewById(R.id.lab1);
        lab2 = findViewById(R.id.lab2);
        lab1.setOnClickListener(v -> {
            Intent lab1 = new Intent(LabSelectionActivity.this, MenuSelectionActivity.class);
            startActivity(lab1);
        });
        lab2.setOnClickListener(v -> {
            Intent lab2 = new Intent(LabSelectionActivity.this, MenuSelectionActivity.class);
        });
    }
}
