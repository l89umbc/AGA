package com.example.anonymousgradingapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class AddCourseActivity extends AppCompatActivity {

    private Button outButton;
    private Button addCourseButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_course);

        outButton = (Button) findViewById(R.id.buttonLogOut);
        addCourseButton = (Button) findViewById(R.id.buttonAddCourse);

        outButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                finish();
            }
        });

        addCourseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(AddCourseActivity.this, SelectedCourseActivity.class);
                startActivity(myIntent);
            }
        });
    }
}