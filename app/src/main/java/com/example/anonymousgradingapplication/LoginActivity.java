package com.example.anonymousgradingapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.amplifyframework.core.Amplify;

public class LoginActivity extends AppCompatActivity {

    private Button loginButton, registerButton;
    private EditText emailText, passText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginButton = (Button) findViewById(R.id.loginButton);
        registerButton = (Button) findViewById(R.id.registerButton);
        emailText = (EditText) findViewById(R.id.editTextLoginUsername);
        passText = (EditText) findViewById(R.id.editTextLoginPassword);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Amplify.Auth.signIn(emailText.getText().toString(),
                        passText.getText().toString(),
                        result -> Log.i("AmplifyLogin", "Result: " + result.toString()),
                        error -> Log.e("AmplifyLogin", "Result: " + error.toString())
                );
                Amplify.Auth.fetchAuthSession(
                        result ->{
                                Log.i("AmplifyFetch", "Result: " + result.toString());
                                if (result.isSignedIn()) {
                                    Intent myIntent = new Intent(LoginActivity.this, AddCourseActivity.class);
                                    startActivity(myIntent);
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