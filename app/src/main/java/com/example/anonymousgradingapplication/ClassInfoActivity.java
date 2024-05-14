package com.example.anonymousgradingapplication;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.util.ArrayList;

public class ClassInfoActivity extends AppCompatActivity {

    private Button backButton, examScores;
    private ArrayList<String> studentNames;
    private ArrayList<Bitmap> barcodeBitmaps;
    private BarcodeMapAdapter adapter;
    private String examName;
    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_info);

        backButton = (Button) findViewById(R.id.buttonClassInfoBack);
        listView = (ListView) findViewById(R.id.studentHolder);
        examScores = (Button) findViewById(R.id.buttonExamScores);
        examName = getIntent().getStringExtra("examName");

        studentNames = new ArrayList<String>();
        barcodeBitmaps = new ArrayList<Bitmap>();

        studentNames = getIntent().getStringArrayListExtra("studentNames");
        if(studentNames != null)
        {
            for(String name: studentNames)
            {
                BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
                Bitmap bitmap = null;
                try {
                    bitmap = barcodeEncoder.encodeBitmap((name+","+examName), BarcodeFormat.QR_CODE, 400, 400);
                } catch (WriterException e) {
                    throw new RuntimeException(e);
                }
                barcodeBitmaps.add(bitmap);
            }
        }

        adapter = new BarcodeMapAdapter(ClassInfoActivity.this, studentNames, barcodeBitmaps);

        listView.setAdapter(adapter);

        examScores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(ClassInfoActivity.this, ExamScoresActivity.class);
                myIntent.putExtra("examID", getIntent().getStringExtra("examID"));
                startActivity(myIntent);
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}