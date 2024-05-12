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
import android.widget.EditText;
import android.widget.TextView;

import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Exam;
import com.amplifyframework.datastore.generated.model.Professor;
import com.amplifyframework.datastore.generated.model.Student;
import com.amplifyframework.datastore.generated.model.StudentClass;
import com.opencsv.CSVReader;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;

public class SelectedCourseActivity extends AppCompatActivity {

    private Button examsButton, saveButton, uploadButton, fetchButton;
    private EditText editProfName, editClassName;
    private TextView classRosterText;
    private List<Student> toAdd;
    private ActivityResultLauncher<Intent> activityResultLauncher;
    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;
    public static String namepref= "Class1";
    private String profName;
    private Professor currProfessor = null;
    private StudentClass currClass = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_course);

        examsButton = (Button) findViewById(R.id.buttonExams);
        saveButton = (Button) findViewById(R.id.buttonCourseSave);
        uploadButton = (Button) findViewById(R.id.buttonUploadRoster);
        fetchButton = (Button) findViewById(R.id.buttonFetchRoster);
        classRosterText = (TextView) findViewById(R.id.textViewRoster);
        editClassName = (EditText) findViewById(R.id.editTextCourseName);
        editProfName = (EditText) findViewById(R.id.editTextProfessor);

        Intent intent = getIntent();
        profName = intent.getStringExtra("profName");
        Amplify.DataStore.query(Professor.class, Professor.NAME.eq(intent.getStringExtra("profName")),
                matches->{
                    while(matches.hasNext())
                    {
                        currProfessor = matches.next();
                        editProfName.setText(currProfessor.getName());
                    }
                },
                error->Log.e("GetProfessor", "Error: "+error)
                );

//        prefs = getSharedPreferences(namepref, MODE_PRIVATE);
//        editor =  prefs.edit();
//
//        currClass = prefs.getAll();
//        StringBuilder temp = new StringBuilder();
//        for (Map.Entry<String,?> entry : currClass.entrySet())
//        {
//            temp.append(entry.getKey()).append(":   ").append(entry.getValue()).append("\n");
//        }
//        classRosterText.setText(temp);

        examsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(SelectedCourseActivity.this, AddExamActivity.class);
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

        fetchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Amplify.DataStore.query(StudentClass.class, StudentClass.NAME.eq(editClassName.getText().toString()),
                        matches->{
                            Log.i("FetchRoster", "Success: building roster");
                            while(matches.hasNext()){
                                StudentClass temp = matches.next();
                                Log.i("FetchRoster", "Success: " + temp.getName());
                                StringBuilder tString = new StringBuilder();

                                Amplify.DataStore.query(Student.class, Student.STUDENT_CLASS_STUDENTS_ID.eq(temp.getId()),
                                        match->{
                                            while(match.hasNext())
                                            {
                                                Student student = match.next();
                                                tString.append(student.getName()).append(":   ").append(student.getUmbcId()).append("\n");
                                            }
                                            classRosterText.setText(tString.toString());
                                        },
                                        error->Log.e("FetchStudents", "Error: "+error));

                            }
                        },
                        error->Log.e("FetchRoster", "Error: "+error));

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

                                    if(currProfessor != null)
                                    {
                                        Amplify.DataStore.query(StudentClass.class, StudentClass.NAME.eq(editClassName.getText().toString()),
                                                matches->{
                                                    while(matches.hasNext())
                                                    {
                                                        currClass = matches.next();
                                                    }
                                                },
                                                error->Log.e("CheckRoster", "Error: "+error));

                                        if (currClass == null)
                                        {
                                            currClass = StudentClass.builder().name(editClassName.getText().toString()).professorClassesId(currProfessor.getId()).build();
                                            Amplify.DataStore.save(currClass,
                                                    success->Log.i("AddClass", "Success: "+success),
                                                    error->Log.e("AddClass", "Error: "+error));
                                        }

                                    }
                                    else
                                    {
                                        Log.e("AddClass", "Error: Missing professor");
                                        return;
                                    }

                                    try {
                                        String[] line;
                                        while ((line = reader.readNext()) != null) {
                                            Log.d(line[0], line[1]);
                                            Student student = Student.builder().umbcId(line[1]).name(line[0]).studentClassStudentsId(currClass.getId()).build();
                                            Amplify.DataStore.save(student,
                                                    success->Log.i("AddStudent", "Success: "+success),
                                                    error->Log.e("AddStudent", "Error: "+error));
//                                          editor.putString(line[0], line[1]);
//                                          editor.commit();
                                        }
                                    } catch (IOException e) {
                                        throw new RuntimeException(e);
                                    }

                                    Amplify.DataStore.save(currClass,
                                            success->Log.i("AddClass", "Success: "+success),
                                            error->Log.e("AddClass", "Error: "+error));


//                                    currClass = prefs.getAll();

//                                    StringBuilder temp = new StringBuilder();
//                                    for (Map.Entry<String,?> entry : currClass.entrySet())
//                                    {
//                                        temp.append(entry.getKey()).append(":   ").append(entry.getValue()).append("\n");
//                                    }
//                                    classRosterText.setText(temp);
                                }

                            }
                        }
                    }
                });
    }
}