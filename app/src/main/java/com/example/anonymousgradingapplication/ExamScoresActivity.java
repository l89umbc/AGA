package com.example.anonymousgradingapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Student;
import com.amplifyframework.datastore.generated.model.StudentExam;

import java.util.ArrayList;

public class ExamScoresActivity extends AppCompatActivity {
    private ArrayList<String> studentNames, studentGrades;
    private String examID;
    private Button fetchButton, backButton;
    private ListView listView;
    private ExamScoreAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam_scores);

        examID = getIntent().getStringExtra("examID");

        studentGrades = new ArrayList<String>();
        studentNames = new ArrayList<String>();
        fetchButton = (Button) findViewById(R.id.buttonFetchExamScores);
        backButton = (Button) findViewById(R.id.examScoresBackButton);
        listView = (ListView) findViewById(R.id.listViewExamScores);
        adapter = new ExamScoreAdapter(this.getApplicationContext(), studentNames, studentGrades);

        listView.setAdapter(adapter);

        fetchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Amplify.DataStore.query(StudentExam.class, StudentExam.EXAM_STUDENT_EXAM_ID.eq(examID),
                        matches->{
                            while(matches.hasNext()){
                                StudentExam temp = matches.next();

                                if(!studentNames.contains(temp.getStudentName()))
                                {
                                    studentNames.add(temp.getStudentName());
                                    studentGrades.add(temp.getGrade());
                                }
                            }
                        },
                        error->Log.e("FetchExamScores", "Error: "+error));
                adapter.notifyDataSetChanged();
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