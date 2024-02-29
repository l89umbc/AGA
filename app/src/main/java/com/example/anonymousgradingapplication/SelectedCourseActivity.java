package com.example.anonymousgradingapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SelectedCourseActivity extends AppCompatActivity {

    private Button classInfoButton;
    private Button examsButton;
    private Button saveButton;
    private Button uploadButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_course);

        classInfoButton = (Button) findViewById(R.id.buttonClassInfo);
        examsButton = (Button) findViewById(R.id.buttonExams);
        saveButton = (Button) findViewById(R.id.buttonCourseSave);
        uploadButton = (Button) findViewById(R.id.buttonUploadRoster);

        examsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(SelectedCourseActivity.this, AddExamActivity.class);
                startActivity(myIntent);
            }
        });

        classInfoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(SelectedCourseActivity.this, ClassInfoActivity.class);
                startActivity(myIntent);
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        uploadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}