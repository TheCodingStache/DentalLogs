package com.example.dentallogs;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.dentallogs.Chat.ChatActivity;
import com.example.dentallogs.Model.AtomikoVasiko;
import com.example.dentallogs.Model.ColorModel;
import com.example.dentallogs.Model.Face;
import com.example.dentallogs.Model.Job;
import com.example.dentallogs.Model.Sex;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Calendar;

public class SpinnerSelectionActivity extends AppCompatActivity {
    protected static final int CAMERA_REQUEST = 0;
    protected static final int GALLERY_PICTURE = 1;
    ArrayAdapter<String> firstSpinnerAdapter;
    ArrayAdapter<String> secondSpinnerAdapter;
    ArrayAdapter<String> thirdSpinnerAdapter;
    ArrayAdapter<String> forthSpinnerAdapter;
    private String item;
    private Sex sex;
    private Face face;
    private Job job;
    SharedPreferences sharedPreferences;
    private Socket socket;
    private AtomikoVasiko mAtomikoVasiko;
    private ColorModel colorModel;
    private ImageView photo;
    private static final int GalleryPick = 1;
    Calendar mCalendar;
    DatePickerDialog mDatePickerDialog;
    RelativeLayout mRelativeLayout;
    ImageView back;
    ArrayList<String> kiniti;
    ArrayList<String> akiniti;
    ArrayList<String> thermo;
    ArrayList<String> akriliki;
    ArrayList<String> allo;
    ArrayList<String> pmma;
    ArrayList<String> metallo;
    ArrayList<String> zirkonia;
    Boolean isOpen = false;
    String username;
    String socketID;
    private String URL = "https://dentallogs.herokuapp.com/";
    EditText comment;
    String senderSocketID;
    String senderUsername;
    String _id;
    String doctorName;
    EditText patientName;
    EditText patientFullName;
    String doctorID;
    private String SHARED_PREFS = "sharedPrefs";
    String color = "";
    String faceSender = "";
    String jobSender = "";
    String sexSender = "";
    FloatingActionButton plus, history, chat;
    TextView historyText, chatText;
    private Animation fab_open, fab_close, fab_clock, fab_anticlock;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_selection);
        doctorName = getIntent().getStringExtra("doctorName");
        doctorID = getIntent().getStringExtra("doctorID");
        patientName = findViewById(R.id.name);
        patientFullName = findViewById(R.id.lastname);
        plus = findViewById(R.id.floatingActionButton);
        history = findViewById(R.id.floatingActionButton1);
        chat = findViewById(R.id.floatingActionButton2);
        historyText = findViewById(R.id.historyFloat);
        chatText = findViewById(R.id.communication);
        fab_close = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_close);
        fab_open = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_open);
        fab_clock = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_rotate_clock);
        fab_anticlock = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_rotate_anticlock);
        JsonObject jsonObject = new JsonObject();
//        _id = jsonObject.toString();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().setStatusBarColor(Color.TRANSPARENT);

        plus.setOnClickListener(v -> {
            if (isOpen) {
                historyText.setVisibility(View.INVISIBLE);
                chatText.setVisibility(View.INVISIBLE);
                chat.startAnimation(fab_close);
                history.startAnimation(fab_close);
                plus.startAnimation(fab_anticlock);
                chat.setClickable(false);
                history.setClickable(false);
                isOpen = false;
            } else {
                historyText.setVisibility(View.VISIBLE);
                chatText.setVisibility(View.VISIBLE);
                chat.startAnimation(fab_open);
                history.startAnimation(fab_open);
                plus.startAnimation(fab_clock);
                chat.setClickable(true);
                history.setClickable(true);
                isOpen = true;
            }
        });
        chat.setOnClickListener(v ->
        {
            Intent intent = new Intent(SpinnerSelectionActivity.this, ChatActivity.class);
            startActivity(intent);
        });
        history.setOnClickListener(v -> {
            Intent intent = new Intent(SpinnerSelectionActivity.this, HistoryActivity.class);
            startActivity(intent);
        });
        try {
            socket = IO.socket(URL);
            socket.connect();
            socket.on("loginTechnician", args -> runOnUiThread(() -> {
                Snackbar.make(mRelativeLayout, "Η λίστα ανανεώθηκε", Snackbar.LENGTH_SHORT).show();
                JSONObject data = (JSONObject) args[0];
                try {
                    username = data.getString("username");
                    socketID = data.getString("socketID");
                    if (senderUsername.equals(username)) {
                        senderSocketID = socketID;
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        comment = findViewById(R.id.comment);
        senderSocketID = getIntent().getStringExtra("socketID");
        senderUsername = getIntent().getStringExtra("username");
        Bundle b = getIntent().getExtras();
        String tech = b.getString("key");
        Log.d("re malaka", "onCreate: " + senderUsername);
        sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("technician", senderUsername);
        editor.apply();
//        Intent intent = new Intent(this, HistoryActivity.class);
//        intent.putExtra("technician", senderUsername);
//        startActivity(intent);
        _id = getIntent().getStringExtra("_id");
        back = findViewById(R.id.back);
        mRelativeLayout = findViewById(R.id.relativeSelection);
        RadioGroup sexGroup = findViewById(R.id.sexGroup);
        RadioGroup faceGroup = findViewById(R.id.faceGroup);
        RadioGroup jobGroup = findViewById(R.id.jobGroup);
        RadioGroup colorGroup = findViewById(R.id.color);
        photo = findViewById(R.id.photo);
        Spinner kinitiSpinner = findViewById(R.id.kinitiSpinner);
        Spinner akinitiSpinner = findViewById(R.id.akinitiSpinner);
        Spinner kinitiItems = findViewById(R.id.kinitiItems);
        Spinner akinitiItems = findViewById(R.id.akinitiItems);
        Button button = findViewById(R.id.send);
        TextView date = findViewById(R.id.date);
        EditText denture = findViewById(R.id.denture);
        sex = new Sex();
        face = new Face();
        job = new Job();
        mAtomikoVasiko = new AtomikoVasiko();
        colorModel = new ColorModel();
        photo.setOnClickListener(v -> openMedia());

        date.setOnClickListener(v -> {
            mCalendar = Calendar.getInstance();
            int day = mCalendar.get(Calendar.DAY_OF_MONTH);
            int month = mCalendar.get(Calendar.MONTH);
            int year = mCalendar.get(Calendar.YEAR);

            mDatePickerDialog = new DatePickerDialog(SpinnerSelectionActivity.this, (view, year1, month1, dayOfMonth)
                    -> date.setText(dayOfMonth + "/" + (month1 + 1) + "/" + year1), year, month, day);
            mDatePickerDialog.show();
        });

        sexGroup.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.woman) {
                sex.setFemale(true);
                sexSender = "Γυναίκα";
                sex.setMale(false);
            } else if (checkedId == R.id.man) {
                sexSender = "Άντρας";
                sex.setMale(true);
                sex.setFemale(false);
            }
        });
        faceGroup.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.triangle) {
                face.setTriangle(true);
                faceSender = "Τρίγωνο";
                face.setSquare(false);
                face.setCircle(false);
            } else if (checkedId == R.id.square) {
                face.setSquare(true);
                faceSender = "Τετράγωνο";
                face.setTriangle(false);
                face.setCircle(false);
            } else if (checkedId == R.id.circle) {
                face.setCircle(true);
                face.setTriangle(false);
                faceSender = "Κυκλικό";
                face.setSquare(false);
            }
        });
        jobGroup.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.kinitiCheck) {
                job.setKiniti(true);
                jobSender = "Κινητή";
                job.setAkiniti(false);
                akinitiSpinner.setVisibility(View.INVISIBLE);
                kinitiItems.setVisibility(View.VISIBLE);
                akinitiItems.setVisibility(View.INVISIBLE);
                kinitiSpinner.setVisibility(View.VISIBLE);
            } else if (checkedId == R.id.akinitiCheck) {
                job.setAkiniti(true);
                jobSender = "Ακίνητη";
                job.setKiniti(false);
                kinitiSpinner.setVisibility(View.INVISIBLE);
                akinitiSpinner.setVisibility(View.VISIBLE);
                kinitiItems.setVisibility(View.INVISIBLE);
                akinitiItems.setVisibility(View.VISIBLE);
            }
        });

        colorGroup.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.a1) {
                colorModel.setA1(true);
                color = "a1";
                colorModel.setA2(false);
                colorModel.setA35(false);
                colorModel.setA4(false);
                colorModel.setB1(false);
                colorModel.setB2(false);
                colorModel.setB3(false);
                colorModel.setB4(false);
                colorModel.setC1(false);
                colorModel.setC2(false);
                colorModel.setC3(false);
                colorModel.setC4(false);
                colorModel.setD2(false);
                colorModel.setD3(false);
                colorModel.setD4(false);
            } else if (checkedId == R.id.a2) {
                colorModel.setA1(false);
                colorModel.setA2(true);
                color = "a2";
                colorModel.setA35(false);
                colorModel.setA4(false);
                colorModel.setB1(false);
                colorModel.setB2(false);
                colorModel.setB3(false);
                colorModel.setB4(false);
                colorModel.setC1(false);
                colorModel.setC2(false);
                colorModel.setC3(false);
                colorModel.setC4(false);
                colorModel.setD2(false);
                colorModel.setD3(false);
                colorModel.setD4(false);
            } else if (checkedId == R.id.a35) {
                colorModel.setA1(false);
                colorModel.setA2(false);
                colorModel.setA35(true);
                color = "a3";
                colorModel.setA4(false);
                colorModel.setB1(false);
                colorModel.setB2(false);
                colorModel.setB3(false);
                colorModel.setB4(false);
                colorModel.setC1(false);
                colorModel.setC2(false);
                colorModel.setC3(false);
                colorModel.setC4(false);
                colorModel.setD2(false);
                colorModel.setD3(false);
                colorModel.setD4(false);
            } else if (checkedId == R.id.a4) {
                colorModel.setA1(false);
                colorModel.setA2(false);
                colorModel.setA35(false);
                colorModel.setA4(true);
                color = "a4";
                colorModel.setB1(false);
                colorModel.setB2(false);
                colorModel.setB3(false);
                colorModel.setB4(false);
                colorModel.setC1(false);
                colorModel.setC2(false);
                colorModel.setC3(false);
                colorModel.setC4(false);
                colorModel.setD2(false);
                colorModel.setD3(false);
                colorModel.setD4(false);
            } else if (checkedId == R.id.b1) {
                colorModel.setA1(false);
                colorModel.setA2(false);
                colorModel.setA35(false);
                colorModel.setA4(false);
                colorModel.setB1(true);
                color = "b1";
                colorModel.setB2(false);
                colorModel.setB3(false);
                colorModel.setB4(false);
                colorModel.setC1(false);
                colorModel.setC2(false);
                colorModel.setC3(false);
                colorModel.setC4(false);
                colorModel.setD2(false);
                colorModel.setD3(false);
                colorModel.setD4(false);
            } else if (checkedId == R.id.b2) {
                colorModel.setA1(false);
                colorModel.setA2(false);
                colorModel.setA35(false);
                colorModel.setA4(false);
                colorModel.setB1(false);
                colorModel.setB2(true);
                color = "b2";
                colorModel.setB3(false);
                colorModel.setB4(false);
                colorModel.setC1(false);
                colorModel.setC2(false);
                colorModel.setC3(false);
                colorModel.setC4(false);
                colorModel.setD2(false);
                colorModel.setD3(false);
                colorModel.setD4(false);
            } else if (checkedId == R.id.b3) {
                colorModel.setA1(false);
                colorModel.setA2(false);
                colorModel.setA35(false);
                colorModel.setA4(false);
                colorModel.setB1(false);
                colorModel.setB2(false);
                colorModel.setB3(true);
                color = "b3";
                colorModel.setB4(false);
                colorModel.setC1(false);
                colorModel.setC2(false);
                colorModel.setC3(false);
                colorModel.setC4(false);
                colorModel.setD2(false);
                colorModel.setD3(false);
                colorModel.setD4(false);
            } else if (checkedId == R.id.b4) {
                colorModel.setA1(false);
                colorModel.setA2(false);
                colorModel.setA35(false);
                colorModel.setA4(false);
                colorModel.setB1(false);
                colorModel.setB2(false);
                colorModel.setB3(false);
                colorModel.setB4(true);
                color = "b4";
                colorModel.setC1(false);
                colorModel.setC2(false);
                colorModel.setC3(false);
                colorModel.setC4(false);
                colorModel.setD2(false);
                colorModel.setD3(false);
                colorModel.setD4(false);
            } else if (checkedId == R.id.c1) {
                colorModel.setA1(false);
                colorModel.setA2(false);
                colorModel.setA35(false);
                colorModel.setA4(false);
                colorModel.setB1(false);
                colorModel.setB2(false);
                colorModel.setB3(false);
                colorModel.setB4(false);
                colorModel.setC1(true);
                color = "c1";
                colorModel.setC2(false);
                colorModel.setC3(false);
                colorModel.setC4(false);
                colorModel.setD2(false);
                colorModel.setD3(false);
                colorModel.setD4(false);
            } else if (checkedId == R.id.c2) {
                colorModel.setA1(false);
                colorModel.setA2(false);
                colorModel.setA35(false);
                colorModel.setA4(false);
                colorModel.setB1(false);
                colorModel.setB2(false);
                colorModel.setB3(false);
                colorModel.setB4(false);
                colorModel.setC1(false);
                colorModel.setC2(true);
                color = "c2";
                colorModel.setC3(false);
                colorModel.setC4(false);
                colorModel.setD2(false);
                colorModel.setD3(false);
                colorModel.setD4(false);
            } else if (checkedId == R.id.c3) {
                colorModel.setA1(false);
                colorModel.setA2(false);
                colorModel.setA35(false);
                colorModel.setA4(false);
                colorModel.setB1(false);
                colorModel.setB2(false);
                colorModel.setB3(false);
                colorModel.setB4(false);
                colorModel.setC1(false);
                colorModel.setC2(false);
                colorModel.setC3(true);
                color = "c3";
                colorModel.setC4(false);
                colorModel.setD2(false);
                colorModel.setD3(false);
                colorModel.setD4(false);
            } else if (checkedId == R.id.c4) {
                colorModel.setA1(false);
                colorModel.setA2(false);
                colorModel.setA35(false);
                colorModel.setA4(false);
                colorModel.setB1(false);
                colorModel.setB2(false);
                colorModel.setB3(false);
                colorModel.setB4(false);
                colorModel.setC1(false);
                colorModel.setC2(false);
                colorModel.setC3(false);
                colorModel.setC4(true);
                color = "c4";
                colorModel.setD2(false);
                colorModel.setD3(false);
                colorModel.setD4(false);
            } else if (checkedId == R.id.d2) {
                colorModel.setA1(false);
                colorModel.setA2(false);
                colorModel.setA35(false);
                colorModel.setA4(false);
                colorModel.setB1(false);
                colorModel.setB2(false);
                colorModel.setB3(false);
                colorModel.setB4(false);
                colorModel.setC1(false);
                colorModel.setC2(false);
                colorModel.setC3(false);
                colorModel.setC4(false);
                colorModel.setD2(true);
                color = "d2";
                colorModel.setD3(false);
                colorModel.setD4(false);
            } else if (checkedId == R.id.d3) {
                colorModel.setA1(false);
                colorModel.setA2(false);
                colorModel.setA35(false);
                colorModel.setA4(false);
                colorModel.setB1(false);
                colorModel.setB2(false);
                colorModel.setB3(false);
                colorModel.setB4(false);
                colorModel.setC1(false);
                colorModel.setC2(false);
                colorModel.setC3(false);
                colorModel.setC4(false);
                colorModel.setD2(false);
                colorModel.setD3(true);
                color = "d3";
                colorModel.setD4(false);
            } else if (checkedId == R.id.d4) {
                colorModel.setA1(false);
                colorModel.setA2(false);
                colorModel.setA35(false);
                colorModel.setA4(false);
                colorModel.setB1(false);
                colorModel.setB2(false);
                colorModel.setB3(false);
                colorModel.setB4(false);
                colorModel.setC1(false);
                colorModel.setC2(false);
                colorModel.setC3(false);
                colorModel.setC4(false);
                colorModel.setD2(false);
                colorModel.setD3(false);
                colorModel.setD4(true);
                color = "d4";
            }
        });

        button.setOnClickListener(v -> {
            Intent result = new Intent(SpinnerSelectionActivity.this, ResultActivity.class);
            result.putExtra("sex", sex);
            result.putExtra("face", face);
            result.putExtra("job", job);
            result.putExtra("color", colorModel);
            result.putExtra("atomikovasiko", mAtomikoVasiko);
            sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
            doctorID = sharedPreferences.getString("doctorID", "");
            doctorName = sharedPreferences.getString("doctorName", "");
            String name = patientName.getText().toString();
            String lastName = patientFullName.getText().toString();
            String c = comment.getText().toString();
            jsonObject.addProperty("recieversocketID", senderSocketID);
            jsonObject.addProperty("recieverUsername", senderUsername);
            jsonObject.addProperty("senderID", doctorID);
            jsonObject.addProperty("senderName", doctorName);
            jsonObject.addProperty("firstName", name);
            jsonObject.addProperty("lastName", lastName);
            jsonObject.addProperty("comment", c);
            jsonObject.addProperty("color", color);
            jsonObject.addProperty("date", date.getText().toString());
            jsonObject.addProperty("gender", sexSender);
            jsonObject.addProperty("face", faceSender);
            jsonObject.addProperty("category", jobSender);
            if (job.isKiniti()) {
                jsonObject.addProperty("subCategory", kinitiSpinner.getSelectedItem().toString());
                jsonObject.addProperty("item", kinitiItems.getSelectedItem().toString());
            } else if (job.isAkiniti()) {
                jsonObject.addProperty("subCategory", akinitiSpinner.getSelectedItem().toString());
                jsonObject.addProperty("item", akinitiItems.getSelectedItem().toString());
            }
            jsonObject.addProperty("denture", denture.getText().toString());
            socket.emit("sendToTechnicianAndroid", jsonObject);
            startActivity(result);
        });


        kiniti = new ArrayList<>();
        kiniti.add("Θερμοπλαστική");
        kiniti.add("Ακρυλική");
        kiniti.add("Άλλο");

        thermo = new ArrayList<>();
        thermo.add("Άκερς");
        thermo.add("Ολική οδοντοστοιχία");
        thermo.add("Μερική οδοντοστοιχία");
        thermo.add("Επιοδιόρθωση");
        thermo.add("Αναγόμωση");

        allo = new ArrayList<>();
        allo.add("Ατομικό Δισκάριο");
        allo.add("Βασική Πλάκα");

        akriliki = new ArrayList<>();
        akriliki.add("Άκερς");
        akriliki.add("Ολική οδοντοστοιχία");
        akriliki.add("Μερική οδοντοστοιχία");
        akriliki.add("Επιοδιόρθωση");
        akriliki.add("Αναγόμωση");

        akiniti = new ArrayList<>();
        akiniti.add("PMMA");
        akiniti.add("Μέταλλο");
        akiniti.add("Ζιρκόνια");

        pmma = new ArrayList<>();
        pmma.add("Προσωρινές");
        pmma.add("Ένθετο/Επένθετο");


        metallo = new ArrayList<>();
        //metallo
        metallo.add("Μεταλλοκεραμική");
        metallo.add("Μέταλλο/Φ.Π");
        metallo.add("Όλικη Χύτη");

        zirkonia = new ArrayList<>();
        //zirkonia
        zirkonia.add("Διαστρωματική");
        zirkonia.add("Μονολιθική");
        zirkonia.add("Ένθετο/Επένθετο");

        firstSpinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, kiniti);
        thirdSpinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, akiniti);

        firstSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        thirdSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        kinitiSpinner.setAdapter(firstSpinnerAdapter);
        akinitiSpinner.setAdapter(thirdSpinnerAdapter);


        kinitiSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    secondSpinnerAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, thermo);
//                    kinitiSpinner.getSelectedItem().toString();
//                    item = parent.getItemAtPosition(position).toString();
                }
                if (position == 1) {
                    secondSpinnerAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, akriliki);
//                    kinitiSpinner.getSelectedItem().toString();
//                    item = parent.getItemAtPosition(position).toString();
                }
                if (position == 2) {
                    secondSpinnerAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, allo);
//                    kinitiSpinner.getSelectedItem().toString();
//                    item = parent.getItemAtPosition(position).toString();
                }
                kinitiItems.setAdapter(secondSpinnerAdapter);
                secondSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        akinitiSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    forthSpinnerAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, pmma);
//                    akinitiSpinner.getSelectedItem().toString();
//                    item = parent.getItemAtPosition(position).toString();
                }
                if (position == 1) {
                    forthSpinnerAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, metallo);
//                    akinitiSpinner.getSelectedItem().toString();
//                    item = parent.getItemAtPosition(position).toString();
                }
                if (position == 2) {
                    forthSpinnerAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, zirkonia);
//                    akinitiSpinner.getSelectedItem().toString();
//                    item = parent.getItemAtPosition(position).toString();
                }
                akinitiItems.setAdapter(forthSpinnerAdapter);
                forthSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        back.setOnClickListener(v ->
        {
            Intent back = new Intent(SpinnerSelectionActivity.this, LabSelectionActivity.class);
            startActivity(back);
            finish();
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CAMERA_REQUEST && resultCode == RESULT_OK && data != null) {
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            photo.setImageBitmap(bitmap);


        } else if (requestCode == GalleryPick && resultCode == RESULT_OK && data != null) {
            Uri imageUri = data.getData();
            photo.setImageURI(imageUri);
        } else {
            Snackbar.make(mRelativeLayout, "Κάτι δεν πήγε καλά, δοκιμάστε ξανά", Snackbar.LENGTH_LONG).show();
        }
    }


    private void openMedia() {
        AlertDialog.Builder myAlertDialog = new AlertDialog.Builder(SpinnerSelectionActivity.this);
        myAlertDialog.setTitle("Επιλέξτε Φωτογραφία");
        myAlertDialog.setPositiveButton("ΣΥΛΛΟΓΗ",
                (arg0, arg1) -> {
                    Intent pictureActionIntent = null;
                    pictureActionIntent = new Intent(
                            Intent.ACTION_PICK,
                            MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(
                            pictureActionIntent,
                            GALLERY_PICTURE);
                });

        myAlertDialog.setNegativeButton("ΚΑΜΕΡΑ",
                (arg0, arg1) -> {
                    StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
                    StrictMode.setVmPolicy(builder.build());
                    Intent intent = new Intent(
                            MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent,
                            CAMERA_REQUEST);
                });
        myAlertDialog.show();
    }


}
