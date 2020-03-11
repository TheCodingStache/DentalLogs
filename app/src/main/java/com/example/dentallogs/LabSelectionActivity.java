package com.example.dentallogs;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.dentallogs.Adapters.LabAdapter;
import com.example.dentallogs.Model.Body;
import com.example.dentallogs.Model.ModelLogin;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class LabSelectionActivity extends AppCompatActivity {
    CircleImageView lab1;
    CircleImageView lab2;
    ProgressBar pb;
    private Socket socket;
    RecyclerView recyclerView;
    public RequestQueue requestQueue;
    List<Body> arrayList;
    private boolean doubleBackToExitPressedOnce = false;
    private String URL = "https://dentallogs.herokuapp.com/doctor/getTechnicians";
    private final String TAG = "gelaw";
    ModelLogin modelLogin = new ModelLogin();
    String username = modelLogin.getUsername();
    String socketID = modelLogin.getSocketID();
    SwipeRefreshLayout swipeRefreshLayout;
    Activity mActivity;
    LabAdapter myAdapter;

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
        mActivity = new Activity();
        setContentView(R.layout.activity_lab_selection);
        pb = new ProgressBar(this);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        recyclerView = findViewById(R.id.recyclerView);
        arrayList = new ArrayList<>();
        myAdapter = new LabAdapter(arrayList, this);
        recyclerView.setAdapter(myAdapter);
        swipeRefreshLayout = findViewById(R.id.swipe_container);
        swipeRefreshLayout.setRefreshing(false);
        retrieveTechnicians();
//        swipeRefreshLayout.setOnRefreshListener(this::retrieveTechnicians);
        swipeRefreshLayout.setOnRefreshListener(() -> {
            Snackbar.make(recyclerView, "Είναι ήδη ανανεωμένη η λίστα", Snackbar.LENGTH_SHORT).show();
            swipeRefreshLayout.setRefreshing(false);
        });
        try {
            String URL = "https://dentallogs.herokuapp.com/";
            socket = IO.socket(URL);
            socket.connect();
            socket.on("loginTechnician", args -> runOnUiThread(() -> {
                retrieveTechnicians();
                Snackbar.make(recyclerView, "Η λίστα ανανεώθηκε", Snackbar.LENGTH_SHORT).show();
//                JSONObject data = (JSONObject) args[0];
//                try {
//                    String name = data.getString("username");
//                    String message = data.getString("socketID");
//                    Snackbar.make(recyclerView, name + " " + message, Snackbar.LENGTH_LONG).show();
//                    Body body = null;
//                    int matchedItemIndex = -1;
//                    for(Body body1 : arrayList){
//                        if(body1.getUsername().equals(name)){
//                          Snackbar.make(recyclerView, "vrethike o komis molismenos", Snackbar.LENGTH_SHORT).show();
//                        } else {
//                            Snackbar.make(recyclerView, "  vrethike MOLISMENOS O KONTOS EPEIDI GAMITHIKE STO MONOKLINO ME TON KOMI", Snackbar.LENGTH_SHORT).show();
//                        }
//                    }
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
            }));
        } catch (URISyntaxException e) {
            e.printStackTrace();

        }
        Snackbar.make(recyclerView, R.string.message, Snackbar.LENGTH_LONG).show();
    }


    private void retrieveTechnicians() {
        String apiKey = "592a1600-7c1a-44d6-9215-88517da7558f";
        swipeRefreshLayout.setRefreshing(true);
        StringRequest stringRequestMovies = new StringRequest(Request.Method.GET, URL,
                response -> {
                    swipeRefreshLayout.setRefreshing(false);
                    GsonBuilder builder = new GsonBuilder();
                    Gson gson = builder.create();
                    arrayList = Arrays.asList(gson.fromJson(response, Body[].class));
                    setUpRecyclerView(arrayList);
                },
                error -> {
                }) {
            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> params = new HashMap<>();
                params.put("secret_api_key", apiKey);
                return params;
            }
        };
        requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequestMovies);
    }


    private void setUpRecyclerView(List<Body> model) {
        LabAdapter labAdapter = new LabAdapter(model, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(labAdapter);
        recyclerView.setItemAnimator(null);
    }
}
