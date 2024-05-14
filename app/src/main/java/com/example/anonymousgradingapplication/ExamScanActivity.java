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
import android.widget.TextView;
import android.widget.Toast;

import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Professor;
import com.amplifyframework.datastore.generated.model.Student;
import com.amplifyframework.datastore.generated.model.StudentClass;
import com.google.zxing.BarcodeFormat;
import com.journeyapps.barcodescanner.BarcodeEncoder;
import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ExamScanActivity extends AppCompatActivity {

    private Button buttonCamera, backButton, classMapButton, addStudentButton, fetchButton;

    private String courseID, profID, examID, examName;
    private Professor currProfessor;
    private StudentClass currClass;
    private TextView textViewExamName;
    private EditText studentNameEdit;
    private ListView rosterListView;
    private ArrayList<String> studentNames;
    private ArrayList<Bitmap> barcodeBitmaps;
    private BarcodeMapAdapter adapter;

    private String getBarcodeString(String student)
    {
        return student + "," + examName;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam_scan);

        profID = getIntent().getStringExtra("profID");
        courseID = getIntent().getStringExtra("courseID");
        examID = getIntent().getStringExtra("examID");
        examName = getIntent().getStringExtra("examName");

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
        fetchButton = (Button) findViewById(R.id.buttonFetchExam);
        textViewExamName = (TextView) findViewById(R.id.editExamName);

        textViewExamName.setText(examName);

        adapter = new BarcodeMapAdapter(this.getApplicationContext(), studentNames, barcodeBitmaps);
        BarcodeEncoder barcodeEncoder = new BarcodeEncoder();

        Amplify.DataStore.query(Student.class, Student.STUDENT_CLASS_STUDENTS_ID.eq(courseID),
                matches->{
                    while(matches.hasNext())
                    {
                        Student temp = matches.next();

                        try {

                            Bitmap bitmap = barcodeEncoder.encodeBitmap(getBarcodeString(temp.getName()), BarcodeFormat.QR_CODE, 400, 400);
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

        fetchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Amplify.DataStore.query(Student.class, Student.STUDENT_CLASS_STUDENTS_ID.eq(currClass.getId()),
                        matches->{

                            while (matches.hasNext())
                            {
                                Student temp = matches.next();
                                if(!studentNames.contains(temp.getName()))
                                {
                                    try
                                    {
                                        barcodeBitmaps.add(barcodeEncoder.encodeBitmap(getBarcodeString(temp.getName()), BarcodeFormat.QR_CODE, 400,400));
                                        studentNames.add(temp.getName());
                                    } catch (Exception e)
                                    {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        },
                        error->Log.e("FetchExamRoster", "Error: "+error));

                adapter.notifyDataSetChanged();
            }
        });
        addStudentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {

                    Bitmap bitmap = barcodeEncoder.encodeBitmap(getBarcodeString(studentNameEdit.getText().toString()), BarcodeFormat.QR_CODE, 400, 400);
                    barcodeBitmaps.add(bitmap);
                    studentNames.add(studentNameEdit.getText().toString());
                    Student temp = Student.builder().umbcId("AB12345").name(studentNameEdit.getText().toString()).studentClassStudentsId(currClass.getId()).build();
                    Amplify.DataStore.save(temp, result->Log.i("AddStudent", "Success: "+result), error->Log.e("AddStudent", "Error: "+error));
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
                    String exam = parts[1].trim();
                    int grade = (int) Math.ceil(Math.random() * 100);
                    Log.d(name, exam);
                    Toast.makeText(ExamScanActivity.this, "Name: " + name, Toast.LENGTH_LONG).show();
                    Toast.makeText(ExamScanActivity.this, "Exam: " + exam, Toast.LENGTH_LONG).show();
                    Toast.makeText(ExamScanActivity.this, "Grade: " + exam, Toast.LENGTH_LONG).show();
                    Toast.makeText(ExamScanActivity.this, "Scanned: " + result.getContents(), Toast.LENGTH_LONG).show();
                }
            });

    public void launchBarcodeScanner(View view) {
        barcodeLauncher.launch(new ScanOptions());
    }


}