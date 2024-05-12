package com.example.anonymousgradingapplication;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Professor;
import com.amplifyframework.datastore.generated.model.Student;
import com.amplifyframework.datastore.generated.model.StudentClass;
import com.google.zxing.BarcodeFormat;
import com.journeyapps.barcodescanner.BarcodeEncoder;
import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;

import java.util.ArrayList;

public class ExamScanActivity extends AppCompatActivity {

    private Button buttonCamera, backButton, classMapButton, addStudentButton;

    private String courseID, profID;
    private Professor currProfessor;
    private StudentClass currClass;
    private EditText studentNameEdit;
    private ListView rosterListView;
    private ArrayList<String> studentNames;
    private ArrayList<Bitmap> barcodeBitmaps;
    private BarcodeMapAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam_scan);

        profID = getIntent().getStringExtra("profID");
        courseID = getIntent().getStringExtra("courseID");

        Amplify.DataStore.query(Professor.class, Professor.ID.eq(profID),
                matches->{
                    while(matches.hasNext())
                    {
                        currProfessor = matches.next();
                    }
                },
                error->Log.e("FetchProfessor", "Error: "+error));

        Amplify.DataStore.query(StudentClass.class, StudentClass.ID.eq(courseID),
                matches->{
                    while(matches.hasNext())
                    {
                        currClass = matches.next();
                    }
                },
                error->Log.e("FetchRoster", "Error: "+error));

        studentNames = new ArrayList<String>();
        barcodeBitmaps = new ArrayList<Bitmap>();

        buttonCamera = (Button) findViewById(R.id.buttonCamera);
        backButton = (Button) findViewById(R.id.buttonExamScanBack);
        classMapButton = (Button) findViewById(R.id.classMapButton);
        addStudentButton = (Button) findViewById(R.id.addStudentButton);
        studentNameEdit = (EditText) findViewById(R.id.editAddStudent);
        rosterListView = (ListView) findViewById(R.id.rosterListView);

        adapter = new BarcodeMapAdapter(this.getApplicationContext(), studentNames, barcodeBitmaps);

        Amplify.DataStore.query(Student.class, Student.STUDENT_CLASS_STUDENTS_ID.eq(courseID),
                matches->{
                    while(matches.hasNext())
                    {
                        Student temp = matches.next();

                        try {
                            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
                            Bitmap bitmap = barcodeEncoder.encodeBitmap(temp.getName(), BarcodeFormat.QR_CODE, 400, 400);
                            barcodeBitmaps.add(bitmap);
                            studentNames.add(temp.getName());
                        } catch (Exception e)
                        {
                            e.printStackTrace();
                        }
                    }
                },
                error->Log.e("FetchStudents", "Error: "+error));

        rosterListView.setAdapter(adapter);

        adapter.notifyDataSetChanged();

        addStudentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
                    Bitmap bitmap = barcodeEncoder.encodeBitmap(studentNameEdit.getText().toString(), BarcodeFormat.QR_CODE, 400, 400);
                    barcodeBitmaps.add(bitmap);
                    studentNames.add(studentNameEdit.getText().toString());
                    adapter.notifyDataSetChanged();
                } catch(Exception e) {
                    Log.d("new student", "new student failed");
                }
            }
        });

        buttonCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchBarcodeScanner(v);
            }
        });

        classMapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(ExamScanActivity.this, ClassInfoActivity.class);
                myIntent.putStringArrayListExtra("studentNames", studentNames);
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

    // Copied from zx barcode library readme

    private final ActivityResultLauncher<ScanOptions> barcodeLauncher = registerForActivityResult(new ScanContract(),
            result -> {
                if(result.getContents() == null) {
                    Toast.makeText(ExamScanActivity.this, "Cancelled", Toast.LENGTH_LONG).show();
                } else {
                    String[] parts = result.getContents().split(",");
                    String name = parts[0].trim();
                    String grade = parts[1].trim();
                    Log.d(name, grade);
                    Toast.makeText(ExamScanActivity.this, "Name: " + name, Toast.LENGTH_LONG).show();
                    Toast.makeText(ExamScanActivity.this, "Grade: " + grade, Toast.LENGTH_LONG).show();
                    Toast.makeText(ExamScanActivity.this, "Scanned: " + result.getContents(), Toast.LENGTH_LONG).show();
                }
            });

    public void launchBarcodeScanner(View view) {
        barcodeLauncher.launch(new ScanOptions());
    }


}