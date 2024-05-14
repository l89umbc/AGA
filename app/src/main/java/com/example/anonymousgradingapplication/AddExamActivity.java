package com.example.anonymousgradingapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Exam;
import com.amplifyframework.datastore.generated.model.StudentClass;

import java.util.ArrayList;

public class AddExamActivity extends AppCompatActivity {

    private ArrayList<String> examIDs, examNames;
    private ExamAdapter examAdapter;
    private String profID, courseID;
    private EditText editAddExamName;
    private ListView listView;
    private Button addExamButton, backButton, fetchButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_exam);

        addExamButton = (Button) findViewById(R.id.buttonAddExam);
        backButton = (Button) findViewById(R.id.buttonExamBack);
        fetchButton = (Button) findViewById(R.id.addExamFetchButton);
        editAddExamName = (EditText) findViewById(R.id.editTextAddExamName);
        examIDs = new ArrayList<String>();
        examNames = new ArrayList<String>();
        listView = (ListView) findViewById(R.id.listViewAddExam);
        profID = getIntent().getStringExtra("profID");
        courseID = getIntent().getStringExtra("courseID");
        examAdapter = new ExamAdapter(this.getApplicationContext(), examNames, examIDs);

        listView.setAdapter(examAdapter);


        fetchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Amplify.DataStore.query(Exam.class, Exam.STUDENT_CLASS_EXAMS_ID.eq(courseID),
                        matches->{
                            while (matches.hasNext())
                            {
                                Exam temp = matches.next();
                                if(!examIDs.contains(temp.getId())){
                                    examIDs.add(temp.getId());
                                    examNames.add(temp.getName());
                                }
                            }
                        },
                        error-> Log.e("FetchExam", "Error: "+error));
                examAdapter.notifyDataSetChanged();
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent myIntent = new Intent(AddExamActivity.this, ExamScanActivity.class);
                myIntent.putExtra("profID", profID);
                myIntent.putExtra("courseID", courseID);
                myIntent.putExtra("examID", examIDs.get(position));
                myIntent.putExtra("examName", examNames.get(position));
                startActivity(myIntent);
            }
        });
        addExamButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = editAddExamName.getText().toString();

                Amplify.DataStore.query(Exam.class, Exam.NAME.eq(text).and(Exam.STUDENT_CLASS_EXAMS_ID.eq(courseID)),
                        matches->{
                            if(!matches.hasNext())
                            {
                                Exam temp = Exam.builder().name(text).studentClassExamsId(courseID).build();
                                examIDs.add(temp.getId());
                                examNames.add(temp.getName());
                                Amplify.DataStore.save(temp, success->Log.i("AddExam", "Success: "+success), error->Log.e("AddExam", "Error: "+error));
                            }
                        },
                        error->Log.e("AddExam", "Error: "+error));
                examAdapter.notifyDataSetChanged();
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