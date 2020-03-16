package com.example.dentallogs;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dentallogs.Adapters.HistoryAdapter;
import com.example.dentallogs.Model.ModelHistory;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class HistoryActivity extends AppCompatActivity {

    private Socket socket;
    RecyclerView recyclerView;
    List<ModelHistory> arrayList;
    HistoryAdapter mAdapter;
    RecyclerView.LayoutManager mLayoutManager;
    private final String TAG = "LOGGING";
    private String SHARED_PREFS = "sharedPrefs";
    String name;
    String lastName;
    String doctorName;
    String date;
    String sex;
    String faceSchema;
    String category;
    String subCategory;
    String selected;
    String color;
    String denture;
    String comment;
    JsonObject jsonObject;
    ModelHistory modelHistory;
    RecyclerView.Adapter historyAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        jsonObject = new JsonObject();
        arrayList = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerHistory);
        try {
            String URL = "https://dentallogs.herokuapp.com/";
            socket = IO.socket(URL);
            socket.connect();
            emitData();
            socket.on("returnDoctorsHistory", args -> runOnUiThread(() -> {
                JSONArray data = (JSONArray) args[0];
                for (int i = 0; i < data.length(); i++) {
                    try {
                        JSONObject jsonObject = data.getJSONObject(i);
                        JSONObject items = jsonObject.getJSONObject("incident");
                        name = items.getString("firstName");
                        lastName = items.getString("lastName");
                        date = items.getString("arrivalDate");
                        sex = items.getString("sex");
                        faceSchema = items.getString("faceSchema");
                        category = items.getString("category");
                        subCategory = items.getString("subcategory");
                        color = items.getString("color");
                        denture = items.getString("denture");
                        comment = items.getString("comments");
                        Log.d(TAG, "DATA\n" + name + "\n" + lastName + "\n" + date + "\n");
                        mLayoutManager = new LinearLayoutManager(this);
                        recyclerView.setLayoutManager(mLayoutManager);
                        recyclerView.setAdapter(historyAdapter);
                        modelHistory = new ModelHistory(name, lastName, date, sex, faceSchema, category, subCategory, color, denture, comment);
                        arrayList.add(modelHistory);
                        if (arrayList.size() > 0) {
                            mAdapter = new HistoryAdapter(arrayList, this);
                            recyclerView.setAdapter(mAdapter);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                Log.d(TAG, "HISTORY: " + data + "\n");
            }));
        } catch (URISyntaxException e) {
            e.printStackTrace();

        }
    }

    private void emitData() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        doctorName = sharedPreferences.getString("doctorName", "");
        String tech = sharedPreferences.getString("technician", "");
        jsonObject.addProperty("technician", tech);
        jsonObject.addProperty("doctor", doctorName);
        socket.emit("getDoctorsHistoryAndroid", jsonObject);
    }
}
