package com.example.anonymousgradingapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Student;
import com.amplifyframework.datastore.generated.model.StudentClass;

import java.util.ArrayList;

public class AddCourseActivity extends AppCompatActivity {

    private Button outButton, addCourseButton, fetchButton;
    private EditText editCourseName;
    private String profID;
    private ArrayList<String> courseNames, courseIDs;
    private ListView listView;
    private CourseAdapter courseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_course);

        outButton = (Button) findViewById(R.id.buttonLogOut);
        addCourseButton = (Button) findViewById(R.id.buttonAddCourse);
        courseNames = new ArrayList<String>();
        courseIDs = new ArrayList<String>();
        listView = (ListView) findViewById(R.id.listViewAddCourse);
        fetchButton = (Button) findViewById(R.id.addCourseFetchButton);
        courseAdapter = new CourseAdapter(this.getApplicationContext(), courseNames, courseIDs);
        editCourseName = (EditText) findViewById(R.id.editTextAddCourseName);
        profID = getIntent().getStringExtra("profID");

        listView.setAdapter(courseAdapter);

        fetchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Amplify.DataStore.query(StudentClass.class, StudentClass.PROFESSOR_CLASSES_ID.eq(profID),
                        matches->{
                            while (matches.hasNext())
                            {
                                StudentClass temp = matches.next();
                                if(!courseIDs.contains(temp.getId())){
                                    courseIDs.add(temp.getId());
                                    courseNames.add(temp.getName());
                                }
                            }
                        },
                        error->Log.e("FetchClasses", "Error: "+error));
                courseAdapter.notifyDataSetChanged();
            }
        });

        outButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                finish();
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent myIntent = new Intent(AddCourseActivity.this, SelectedCourseActivity.class);
                myIntent.putExtra("profID", profID);
                myIntent.putExtra("courseName", courseNames.get(position));
                myIntent.putExtra("courseID", courseIDs.get(position));
                startActivity(myIntent);
            }
        });

        addCourseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = editCourseName.getText().toString();

                Amplify.DataStore.query(StudentClass.class, StudentClass.NAME.eq(text).and(StudentClass.PROFESSOR_CLASSES_ID.eq(profID)),
                        matches->{
                            if(!matches.hasNext())
                            {
                                StudentClass temp = StudentClass.builder().name(text).professorClassesId(profID).build();
                                courseIDs.add(temp.getId());
                                courseNames.add(temp.getName());
                                Amplify.DataStore.save(temp, success->Log.i("AddClass", "Success: "+success), error->Log.e("AddClass", "Error: "+error));
                            }
                        },
                        error->Log.e("AddClass", "Error: "+error));
                courseAdapter.notifyDataSetChanged();
            }
        });
    }
}