package com.example.anonymousgradingapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

public class SelectedCourseActivity extends AppCompatActivity {

    private Button classInfoButton;
    private Button examsButton;
    private Button saveButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_course);
    }
}