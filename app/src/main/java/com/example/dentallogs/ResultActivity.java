package com.example.dentallogs;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.dentallogs.Model.Face;
import com.example.dentallogs.Model.Job;
import com.example.dentallogs.Model.Sex;

public class ResultActivity extends AppCompatActivity {
    TextView fullo;
    TextView proswpo;
    TextView douleia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        Sex sex = (Sex) getIntent().getSerializableExtra("sex");
        Face face = (Face) getIntent().getSerializableExtra("face");
        Job job = (Job) getIntent().getSerializableExtra("job");
        String s = getString(R.string.typeSex) + ": ";
        assert sex != null;
        if (sex.getMale()) {
            s += getString(R.string.man);
        } else {
            s += getString(R.string.woman);
        }
        fullo = findViewById(R.id.fullo);
        fullo.setText(s);

        String f;
        assert face != null;
        if (face.isTriangle()) {
            f = getString(R.string.typeFace) + " " + getString(R.string.triangle);
        } else if (face.isSquare()) {
            f = getString(R.string.typeFace) + " " + getString(R.string.square);
        } else {
            f = getString(R.string.typeFace) + " " + getString(R.string.circle);
        }
        proswpo = findViewById(R.id.proswpo);
        proswpo.setText(f);

        String j;
        assert job != null;
        if (job.isKiniti()) {
            j = getString(R.string.typeJob) + " " + getString(R.string.kiniti);
        } else {
            j = getString(R.string.typeJob) + " " + getString(R.string.akiniti);
        }
        douleia = findViewById(R.id.douleia);
        douleia.setText(j);
    }
}
