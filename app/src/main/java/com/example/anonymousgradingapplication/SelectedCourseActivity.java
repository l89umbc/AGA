package com.example.anonymousgradingapplication;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.opencsv.CSVReader;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.List;

public class SelectedCourseActivity extends AppCompatActivity {

    private Button classInfoButton;
    private Button examsButton;
    private Button saveButton;
    private Button uploadButton;
    private ActivityResultLauncher<Intent> activityResultLauncher;
    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;
    public static String namepref= "Authentication";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_course);

        classInfoButton = (Button) findViewById(R.id.buttonClassInfo);
        examsButton = (Button) findViewById(R.id.buttonExams);
        saveButton = (Button) findViewById(R.id.buttonCourseSave);
        uploadButton = (Button) findViewById(R.id.buttonUploadRoster);

        prefs = getSharedPreferences(namepref, MODE_PRIVATE);
        editor =  prefs.edit();

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
                Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                intent.setType("*/*");
                activityResultLauncher.launch(intent);
            }
        });

        activityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK) {
                            Intent data = result.getData();
                            if (data != null)
                            {
                                Uri uri = data.getData();
                                if(uri != null)
                                {
                                    InputStream input = null;
                                    try {
                                        input = getContentResolver().openInputStream(uri);
                                    } catch (FileNotFoundException e) {
                                        throw new RuntimeException(e);
                                    }
                                    CSVReader reader = new CSVReader(new InputStreamReader(input));


                                    try {
                                        String[] line;
                                        while ((line = reader.readNext()) != null) {
                                            Log.d(line[0], line[1]);
                                            editor.putString(line[0], line[1]);
                                            editor.commit();
                                        }
                                    } catch (IOException e) {
                                        throw new RuntimeException(e);
                                    }

                                }

                            }
                        }
                    }
                });
    }
}