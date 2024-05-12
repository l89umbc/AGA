package com.example.anonymousgradingapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.amplifyframework.core.Amplify;
import com.amplifyframework.core.model.query.QueryOptions;
import com.amplifyframework.core.model.query.Where;
import com.amplifyframework.datastore.generated.model.Professor;

public class LoginActivity extends AppCompatActivity {

    private Button loginButton, registerButton;
    private EditText userText, passText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginButton = (Button) findViewById(R.id.loginButton);
        registerButton = (Button) findViewById(R.id.registerButton);
        userText = (EditText) findViewById(R.id.editTextLoginUsername);
        passText = (EditText) findViewById(R.id.editTextLoginPassword);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Amplify.Auth.signIn(userText.getText().toString(),
                        passText.getText().toString(),
                        result -> Log.i("AmplifyLogin", "Result: " + result.toString()),
                        error -> Log.e("AmplifyLogin", "Result: " + error.toString())
                );
                Amplify.Auth.fetchAuthSession(
                        result ->{
                                Log.i("AmplifyFetch", "Result: " + result.toString());
                                if (result.isSignedIn()) {

                                    Amplify.DataStore.query(Professor.class, Professor.NAME.eq(userText.getText().toString()),
                                            matches -> {
                                                Professor professor = null;
                                                while(matches.hasNext())
                                                {
                                                    professor = matches.next();

                                                }
                                                if(professor != null)
                                                {
                                                    Log.i("AmplifyGetProfessor", professor.getName());
                                                    Intent myIntent = new Intent(LoginActivity.this, AddCourseActivity.class);
                                                    startActivity(myIntent);
                                                }
                                                else
                                                {
                                                    professor = Professor.builder().name(userText.getText().toString()).build();
                                                    Amplify.DataStore.save(professor,
                                                            success-> {
                                                                Intent myIntent = new Intent(LoginActivity.this, AddCourseActivity.class);
                                                                startActivity(myIntent);
                                                            },
                                                            error->Log.e("NewProfessorAdded", "Error: "+error));
                                                }
                                            },
                                            error->Log.i("AmplifyGetProfessor", "Error: "+error));
                                }

                            },
                        error -> Log.e("AmplifyFetch", "Result: " + error.toString())
                );

            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(myIntent);
            }
        });
    }
}