package com.example.dentallogs;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.dentallogs.Adapters.FirstWorkAdapter;
import com.example.dentallogs.Model.SpinnerItem;

import java.util.ArrayList;

public class SpinnerSelectionActivity extends AppCompatActivity {
    FirstWorkAdapter mFirstWorkAdapter;
    ArrayList<SpinnerItem> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_selection);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        initList();
        Spinner first = findViewById(R.id.kiniti);
        first.setSelection(R.id.thermoak);
        mFirstWorkAdapter = new FirstWorkAdapter(this, arrayList);
        first.setAdapter(mFirstWorkAdapter);
        first.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                SpinnerItem clickedItem = (SpinnerItem) parent.getItemAtPosition(position);
                String click = clickedItem.getMaterial();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(SpinnerSelectionActivity.this, "χαχαχα", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void initList() {
        SpinnerItem spinnerItem = new SpinnerItem("Θερμοπλαστική");
        arrayList = new ArrayList<>();
        arrayList.add(spinnerItem);
        arrayList.add(new SpinnerItem("Άκερς"));
        arrayList.add(new SpinnerItem("Ολική οδοντοστοιχία"));
        arrayList.add(new SpinnerItem("Μερική οδοντοστοιχία"));
        arrayList.add(new SpinnerItem("Επιοδιόρθωση"));
        arrayList.add(new SpinnerItem("Αναγόμωση"));
    }
}
