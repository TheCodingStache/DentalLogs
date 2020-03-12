package com.example.dentallogs;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class ResultActivity extends AppCompatActivity {
    //    TextView fullo;
//    TextView proswpo;
//    TextView douleia;
//    TextView xrwma;
//    TextView atomikoVasiko;
    Button mButton;
    TextView mTextView;
    ImageView mImageView;

    @Override
    public void onBackPressed() {
// super.onBackPressed();
// Not calling **super**, disables back button in current screen.
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        mButton = findViewById(R.id.backtomain);
        mTextView = findViewById(R.id.messageResult);
        mImageView = findViewById(R.id.tick);


        Glide.with(getApplicationContext())
                .load(R.drawable.tick)
                .placeholder(R.drawable.tick)
                .into(mImageView);
        mButton.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), LabSelectionActivity.class);
            startActivity(intent);
            finish();
        });
//        Sex sex = (Sex) getIntent().getSerializableExtra("sex");
//        Face face = (Face) getIntent().getSerializableExtra("face");
//        Job job = (Job) getIntent().getSerializableExtra("job");
//        ColorModel colorModel = (ColorModel) getIntent().getSerializableExtra("color");
//        AtomikoVasiko mAtomikoVasiko = (AtomikoVasiko) getIntent().getSerializableExtra("atomikovasiko");
//        String s = getString(R.string.typeSex) + ": ";
//        assert sex != null;
//        if (sex.getMale()) {
//            s += getString(R.string.man);
//        } else {
//            s += getString(R.string.woman);
//        }
//        fullo = findViewById(R.id.fullo);
//        fullo.setText(s);
//
//        String f;
//        assert face != null;
//        if (face.isTriangle()) {
//            f = getString(R.string.typeFace) + " " + getString(R.string.triangle);
//        } else if (face.isSquare()) {
//            f = getString(R.string.typeFace) + " " + getString(R.string.square);
//        } else {
//            f = getString(R.string.typeFace) + " " + getString(R.string.circle);
//        }
//        proswpo = findViewById(R.id.proswpo);
//        proswpo.setText(f);
//
//        String j;
//        assert job != null;
//        if (job.isKiniti()) {
//            j = getString(R.string.typeJob) + " " + getString(R.string.kiniti);
//        } else {
//            j = getString(R.string.typeJob) + " " + getString(R.string.akiniti);
//        }
//        douleia = findViewById(R.id.douleia);
//        douleia.setText(j);
//        String a;
//        assert mAtomikoVasiko != null;
//        if (mAtomikoVasiko.getAtomiko()) {
//            a = getString(R.string.material) + " " + getString(R.string.atomic);
//        } else {
//            a = getString(R.string.material) + " " + getString(R.string.basic);
//        }
//        atomikoVasiko = findViewById(R.id.atomikoVasiko);
//        atomikoVasiko.setText(a);
//
//        String c = " ";
//        assert colorModel != null;
//        if (colorModel.isA1()) {
//            c = getString(R.string.color) + " " + getString(R.string.a1);
//        } else if (colorModel.isA2()) {
//            c = getString(R.string.color) + " " + getString(R.string.a2);
//        } else if (colorModel.isA35()) {
//            c = getString(R.string.color) + " " + getString(R.string.a35);
//        } else if (colorModel.isA4()) {
//            c = getString(R.string.color) + " " + getString(R.string.a4);
//        } else if (colorModel.isB1()) {
//            c = getString(R.string.color) + " " + getString(R.string.b1);
//        } else if (colorModel.isB2()) {
//            c = getString(R.string.color) + " " + getString(R.string.b2);
//        } else if (colorModel.isB3()) {
//            c = getString(R.string.color) + " " + getString(R.string.b3);
//        } else if (colorModel.isB4()) {
//            c = getString(R.string.color) + " " + getString(R.string.b4);
//        } else if (colorModel.isC1()) {
//            c = getString(R.string.color) + " " + getString(R.string.c1);
//        } else if (colorModel.isC2()) {
//            c = getString(R.string.color) + " " + getString(R.string.c2);
//        } else if (colorModel.isC3()) {
//            c = getString(R.string.color) + " " + getString(R.string.c3);
//        } else if (colorModel.isC4()) {
//            c = getString(R.string.color) + " " + getString(R.string.c4);
//        } else if (colorModel.isD2()) {
//            c = getString(R.string.color) + " " + getString(R.string.d2);
//        } else if (colorModel.isD3()) {
//            c = getString(R.string.color) + " " + getString(R.string.d3);
//        } else if (colorModel.isD4()) {
//            c = getString(R.string.color) + " " + getString(R.string.d4);
//        }
//        xrwma = findViewById(R.id.xrwma);
//        xrwma.setText(c);
//    }

    }
}