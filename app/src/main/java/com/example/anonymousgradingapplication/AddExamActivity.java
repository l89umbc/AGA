package com.example.anonymousgradingapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AddExamActivity extends AppCompatActivity {

    private Button addExamButton;
    private Button backButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_exam);

        addExamButton = (Button) findViewById(R.id.buttonAddExam);
        backButton = (Button) findViewById(R.id.buttonExamBack);
        addExamButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(AddExamActivity.this, ExamScanActivity.class);
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