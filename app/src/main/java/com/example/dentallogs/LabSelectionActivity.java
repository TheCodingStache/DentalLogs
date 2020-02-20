package com.example.dentallogs;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.dentallogs.Adapters.LabAdapter;
import com.example.dentallogs.Model.Body;
import com.example.dentallogs.Model.ModelLogin;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class LabSelectionActivity extends AppCompatActivity {
    CircleImageView lab1;
    CircleImageView lab2;
    ProgressBar pb;

    RecyclerView recyclerView;
    public RequestQueue requestQueue;
    List<Body> arrayList;
    private boolean doubleBackToExitPressedOnce = false;
    private String URL = "https://93f7c150.ngrok.io/doctor/getTechnicians";
    private final String TAG = "gelaw";

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            finishActivity(R.layout.activity_main);
            finish();
        }
        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Πηγαίνετε πίσω μία ακόμη φορά για να αποσυνδεθείτε", Toast.LENGTH_SHORT).show();
        new Handler().postDelayed(() -> doubleBackToExitPressedOnce = false, 2000);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_selection);
        pb = new ProgressBar(this);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        recyclerView = findViewById(R.id.recyclerView);
        arrayList = new ArrayList<>();
        retrieveTechnicians();
        Snackbar.make(recyclerView, R.string.message, Snackbar.LENGTH_LONG).show();

    }

    private void retrieveTechnicians() {

        StringRequest stringRequestMovies = new StringRequest(Request.Method.GET, URL,
                (Response.Listener<String>) response -> {
                    pb.setVisibility(View.GONE);
                    GsonBuilder builder = new GsonBuilder();
                    Gson gson = builder.create();
                    arrayList = Arrays.asList(gson.fromJson(response, Body[].class));

                    setUpRecyclerView(arrayList);

                }, (Response.ErrorListener) error -> {
            Log.d(TAG, "retrieveTechnicians: ");

        });


        requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequestMovies);
    }

    private void setUpRecyclerView(List<Body> model) {
        LabAdapter labAdapter = new LabAdapter(model, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(labAdapter);

    }

}
