package com.example.dentallogs;

import android.graphics.Color;
import android.os.Bundle;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dentallogs.Adapters.LabAdapter;
import com.example.dentallogs.Model.TechList;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class LabSelectionActivity extends AppCompatActivity {
    CircleImageView lab1;
    CircleImageView lab2;
    RecyclerView recyclerView;
    ArrayList<TechList> arrayList;
    LabAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_selection);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        arrayList = new ArrayList<>();
        arrayList.add(new TechList("Dimitris", "Kozani", R.drawable.dms));
        arrayList.add(new TechList("Vaggelis", "Kozani", R.drawable.vagg));
        arrayList.add(new TechList("Χρηστάρα Κόμης", "Λαρισάρα και Παστεκάρα", R.drawable.chriskomis));
        myAdapter = new LabAdapter(arrayList, this);
        LabAdapter labAdapter = new LabAdapter(arrayList, this);
        recyclerView.setAdapter(labAdapter);
        Snackbar.make(recyclerView, R.string.message, Snackbar.LENGTH_LONG).show();

    }
}
