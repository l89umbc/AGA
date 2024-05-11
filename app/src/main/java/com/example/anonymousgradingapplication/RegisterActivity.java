package com.example.anonymousgradingapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.amplifyframework.auth.AuthUserAttributeKey;
import com.amplifyframework.auth.options.AuthSignUpOptions;
import com.amplifyframework.core.Amplify;

import org.w3c.dom.Text;

public class RegisterActivity extends AppCompatActivity {
    private Button createButton, confirmButton, backButton;
    private EditText nameText, emailText, passText, codeText;
    private TextView codeLabel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        backButton = (Button) findViewById(R.id.backRegisterButton);
        createButton = (Button) findViewById(R.id.buttonCreateAccount);
        confirmButton = (Button) findViewById(R.id.buttonConfirmRegister);
        nameText = (EditText) findViewById(R.id.editTextRegisterName);
        emailText = (EditText) findViewById(R.id.editTextRegisterUsername);
        passText = (EditText) findViewById(R.id.editTextRegisterPassword);
        codeText = (EditText) findViewById(R.id.editTextConfirmCode);
        codeLabel = (TextView) findViewById(R.id.confirmationCodeLabel);

        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(passText.getText().toString().trim().length() == 0 || emailText.getText().toString().trim().length() == 0)
                {
                    return;
                }

                AuthSignUpOptions options = AuthSignUpOptions.builder()
                        .userAttribute(AuthUserAttributeKey.email(), emailText.getText().toString())
                        .build();

                Amplify.Auth.signUp(
                        emailText.getText().toString(),
                        passText.getText().toString(),
                        options, result->{
                            Log.i("AmplifyRegister", "Result:" + result.toString());
                            Log.i("AmplifyRegister", "User: " + emailText.getText().toString() +
                                    " Pass: " + passText.getText().toString());
                            }
                        ,
                        error -> Log.e("AmplifyRegister", "Result" + error.toString())
                );
                codeText.setVisibility(View.VISIBLE);
                codeLabel.setVisibility(View.VISIBLE);
                confirmButton.setVisibility(View.VISIBLE);
            }
        });

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(codeText.getText().toString().trim().length() == 0 || emailText.getText().toString().trim().length() == 0)
                {
                    return;
                }
                Amplify.Auth.confirmSignUp(emailText.getText().toString(),
                        codeText.getText().toString(),
                        result -> Log.i("AmplifyRegister", "Result: " + result.toString()),
                        error -> Log.e("AmplifyRegister", "Result" + error.toString()));

                finish();
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