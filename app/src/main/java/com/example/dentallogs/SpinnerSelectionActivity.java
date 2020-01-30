package com.example.dentallogs;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.dentallogs.Chat.ChatActivity;
import com.example.dentallogs.Model.AtomikoVasiko;
import com.example.dentallogs.Model.ColorModel;
import com.example.dentallogs.Model.Face;
import com.example.dentallogs.Model.Job;
import com.example.dentallogs.Model.Sex;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Calendar;

public class SpinnerSelectionActivity extends AppCompatActivity {
    protected static final int CAMERA_REQUEST = 0;
    protected static final int GALLERY_PICTURE = 1;
    private Sex sex;
    private Face face;
    private Job job;
    private AtomikoVasiko mAtomikoVasiko;
    private ColorModel colorModel;
    private ImageView photo;
    private static final int GalleryPick = 1;
    Calendar mCalendar;
    DatePickerDialog mDatePickerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_selection);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        EditText comment = findViewById(R.id.comment);
        FloatingActionButton floatingActionButton = findViewById(R.id.floatingActionButton);
        RadioGroup sexGroup = findViewById(R.id.sexGroup);
        RadioGroup faceGroup = findViewById(R.id.faceGroup);
        RadioGroup jobGroup = findViewById(R.id.jobGroup);
        RadioGroup atomikovasiko = findViewById(R.id.atomikovasiko);
        RadioButton atomiko = findViewById(R.id.atomiko);
        RadioButton vasiki = findViewById(R.id.vasiki);
        RadioGroup colorGroup = findViewById(R.id.color);
        photo = findViewById(R.id.photo);
        Spinner first = findViewById(R.id.thermoplastiki);
        Spinner second = findViewById(R.id.PMMA);
        Spinner third = findViewById(R.id.metallo);
        Spinner forth = findViewById(R.id.zirkonia);
        Button button = findViewById(R.id.send);
        TextView date = findViewById(R.id.date);
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
                    -> date.setText(dayOfMonth + "/" + (month1+1) + "/" + year1), year, month, day );
            mDatePickerDialog.show();
        });

        atomikovasiko.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.atomiko) {
                mAtomikoVasiko.setAtomiko(true);
                mAtomikoVasiko.setVasiki(false);
            } else if (checkedId == R.id.vasiki) {
                mAtomikoVasiko.setAtomiko(false);
                mAtomikoVasiko.setVasiki(true);
            } else {
                mAtomikoVasiko.setAtomiko(false);
                mAtomikoVasiko.setVasiki(false);
            }
        });
        sexGroup.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.woman) {
                sex.setFemale(true);
                sex.setMale(false);
            } else if (checkedId == R.id.man) {
                sex.setMale(true);
                sex.setFemale(false);
            }
        });
        faceGroup.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.triangle) {
                face.setTriangle(true);
                face.setSquare(false);
                face.setCircle(false);
            } else if (checkedId == R.id.square) {
                face.setSquare(true);
                face.setTriangle(false);
                face.setCircle(false);
            } else if (checkedId == R.id.circle) {
                face.setCircle(true);
                face.setTriangle(false);
                face.setSquare(false);
            }
        });
        jobGroup.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.kinitiCheck) {
                job.setKiniti(true);
                job.setAkiniti(false);
                second.setVisibility(View.INVISIBLE);
                third.setVisibility(View.INVISIBLE);
                forth.setVisibility(View.INVISIBLE);
                first.setVisibility(View.VISIBLE);
                atomiko.setVisibility(View.VISIBLE);
                vasiki.setVisibility(View.VISIBLE);
            } else if (checkedId == R.id.akinitiCheck) {
                job.setAkiniti(true);
                job.setKiniti(false);
                first.setVisibility(View.INVISIBLE);
                atomiko.setVisibility(View.INVISIBLE);
                vasiki.setVisibility(View.INVISIBLE);
                second.setVisibility(View.VISIBLE);
                third.setVisibility(View.VISIBLE);
                forth.setVisibility(View.VISIBLE);
            }
        });

        colorGroup.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.a1) {
                colorModel.setA1(true);
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
                colorModel.setB3(true);
                colorModel.setB4(false);
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
            }
        });

        button.setOnClickListener(v -> {
            Intent intent = new Intent(SpinnerSelectionActivity.this, ResultActivity.class);
            intent.putExtra("sex", sex);
            intent.putExtra("face", face);
            intent.putExtra("job", job);
            intent.putExtra("color", colorModel);
            intent.putExtra("atomikovasiko", mAtomikoVasiko);
            startActivity(intent);
        });

        ArrayList<String> thermo = new ArrayList<>();
        thermo.add(0, "Θερμοπλαστική/Ακρυλική");
        thermo.add("Άκερς");
        thermo.add("Ολική οδοντοστοιχία");
        thermo.add("Μερική οδοντοστοιχία");
        thermo.add("Επιοδιόρθωση");
        thermo.add("Αναγόμωση");

        ArrayList<String> pmma = new ArrayList<>();
        pmma.add(0, "PMMA");
        pmma.add("Προσωρινές");
        pmma.add("Ένθετο/Επένθετο");

        ArrayList<String> metallo = new ArrayList<>();
        metallo.add(0, "Μέταλλο");
        metallo.add("Μεταλλοκεραμική");
        metallo.add("Μέταλλο/Φ.Π");
        metallo.add("Όλικη Χύτη");

        ArrayList<String> zirkonia = new ArrayList<>();
        zirkonia.add(0, "Ζιρκόνια");
        zirkonia.add("Διαστρωματική");
        zirkonia.add("Μονολιθική");
        zirkonia.add("Ένθετο/Επένθετο");
        ArrayAdapter<String> firstSpinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, thermo);
        ArrayAdapter<String> secondSpinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, pmma);
        ArrayAdapter<String> thirdSpinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, metallo);
        ArrayAdapter<String> forthSpinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, zirkonia);

        firstSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        secondSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        thirdSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        forthSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        first.setAdapter(firstSpinnerAdapter);
        second.setAdapter(secondSpinnerAdapter);
        third.setAdapter(thirdSpinnerAdapter);
        forth.setAdapter(forthSpinnerAdapter);

        floatingActionButton.setOnClickListener(v -> {
            Intent intent = new Intent(SpinnerSelectionActivity.this, ChatActivity.class);
            startActivity(intent);
        });

        first.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parent.getItemAtPosition(position).equals("Θερμοπλαστική")) {
                } else {
                    first.getSelectedItem().toString();
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
                } else {
                    second.getSelectedItem().toString();
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
                if (parent.getItemAtPosition(position).equals("Μέταλλο")) {
                } else {
                    third.getSelectedItem().toString();
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
                if (parent.getItemAtPosition(position).equals("Ζιρκόνια")) {
                } else {
                    String i = forth.getSelectedItem().toString();
                    String item = parent.getItemAtPosition(position).toString();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
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
            Toast.makeText(this, "Κάτι δεν πήγε καλά, δοκιμάστε ξανά", Toast.LENGTH_SHORT).show();

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
