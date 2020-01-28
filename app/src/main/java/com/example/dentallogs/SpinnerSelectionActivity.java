package com.example.dentallogs;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.example.dentallogs.Chat.ChatActivity;
import com.example.dentallogs.Model.Face;
import com.example.dentallogs.Model.Job;
import com.example.dentallogs.Model.Sex;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class SpinnerSelectionActivity extends AppCompatActivity {
    ArrayAdapter<String> firstSpinnerAdapter;
    ArrayAdapter<String> secondSpinnerAdapter;
    ArrayAdapter<String> thirdSpinnerAdapter;
    ArrayAdapter<String> forthSpinnerAdapter;
    ArrayList<String> thermo;
    ArrayList<String> pmma;
    ArrayList<String> metallo;
    ArrayList<String> zirkonia;
    EditText comment;
    FloatingActionButton mFloatingActionButton;
    RadioGroup sexGroup;
    RadioGroup faceGroup;
    RadioGroup jobGroup;
    Sex sex;
    Face face;
    Job job;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_selection);
        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        getWindow().setStatusBarColor(Color.TRANSPARENT);

        comment = findViewById(R.id.comment);
        mFloatingActionButton = findViewById(R.id.floatingActionButton);
        sexGroup = findViewById(R.id.sexGroup);
        faceGroup = findViewById(R.id.faceGroup);
        jobGroup = findViewById(R.id.jobGroup);

        Spinner first = findViewById(R.id.thermoplastiki);
        Spinner second = findViewById(R.id.PMMA);
        Spinner third = findViewById(R.id.metallo);
        Spinner forth = findViewById(R.id.zirkonia);

        sex = new Sex();
        face = new Face();
        job = new Job();
        int choiceSex = sexGroup.getCheckedRadioButtonId();
        switch (choiceSex) {
            case R.id.woman:
                sex.setMale(false);
                sex.setFemale(true);
                break;
            case R.id.man:
                sex.setMale(true);
                sex.setFemale(false);
                break;
        }
        int choiceFace = faceGroup.getCheckedRadioButtonId();
        switch (choiceFace) {
            case R.id.triangle:
                face.setSquare(false);
                face.setCircle(false);
                face.setSquare(true);
                break;
            case R.id.square:
                face.setTriangle(false);
                face.setCircle(false);
                face.setSquare(true);
                break;
            case R.id.circle:
                face.setTriangle(false);
                face.setSquare(false);
                face.setCircle(true);
                break;
        }

        int choiceJob = jobGroup.getCheckedRadioButtonId();
        switch (choiceJob) {
            case R.id.akinitiCheck:
                job.setAkiniti(true);
                job.setKiniti(false);
                break;
            case R.id.kinitiCheck:
                job.setAkiniti(false);
                job.setKiniti(true);
                break;
        }

        thermo = new ArrayList<>();
        thermo.add(0, "Θερμοπλαστική/Ακρυλική");
        thermo.add("Άκερς");
        thermo.add("Ολική οδοντοστοιχία");
        thermo.add("Μερική οδοντοστοιχία");
        thermo.add("Επιοδιόρθωση");
        thermo.add("Αναγόμωση");

        pmma = new ArrayList<>();
        pmma.add(0, "PMMA");
        pmma.add("Προσωρινές");
        pmma.add("Ένθετο/Επένθετο");

        metallo = new ArrayList<>();
        metallo.add(0, "Μέταλλο");
        metallo.add("Μεταλλοκεραμική");
        metallo.add("Μέταλλο/Φ.Π");
        metallo.add("Όλικη Χύτη");

        zirkonia = new ArrayList<>();
        zirkonia.add(0, "Ζιρκόνια");
        zirkonia.add("Διαστρωματική");
        zirkonia.add("Μονολιθική");
        zirkonia.add("Ένθετο/Επένθετο");
        firstSpinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, thermo);
        secondSpinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, pmma);
        thirdSpinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, metallo);
        forthSpinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, zirkonia);

        firstSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        secondSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        thirdSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        forthSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        first.setAdapter(firstSpinnerAdapter);
        second.setAdapter(secondSpinnerAdapter);
        third.setAdapter(thirdSpinnerAdapter);
        forth.setAdapter(forthSpinnerAdapter);

        mFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SpinnerSelectionActivity.this, ChatActivity.class);
                startActivity(intent);
            }
        });

        first.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parent.getItemAtPosition(position).equals("Θερμοπλαστική")) {
                    //do nothing
                } else {
                    String item = parent.getItemAtPosition(position).toString();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        second.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parent.getItemAtPosition(position).equals("PMMA")) {
                    //do nothing
                } else {
                    String item = parent.getItemAtPosition(position).toString();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        third.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parent.getItemAtPosition(position).equals("PMMA")) {
                    //do nothing
                } else {
                    String item = parent.getItemAtPosition(position).toString();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        forth.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parent.getItemAtPosition(position).equals("PMMA")) {
                    //do nothing
                } else {
                    String item = parent.getItemAtPosition(position).toString();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SpinnerSelectionActivity.this, ResultActivity.class);
                startActivity(intent);
            }
        });
    }
}
