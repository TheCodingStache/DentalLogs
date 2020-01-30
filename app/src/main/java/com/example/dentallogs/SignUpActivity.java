package com.example.dentallogs;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SignUpActivity extends AppCompatActivity {
    EditText username;
    EditText email;
    EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        TextView signUp = findViewById(R.id.signInText);
        username = findViewById(R.id.usernameSignUp);
        email = findViewById(R.id.emailSignUp);
        password = findViewById(R.id.passwordSignUp);
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void userSignUp() {
        String emailText = email.getText().toString().trim();
        if (emailText.isEmpty()) {
            email.setError("Email is required");
            email.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(emailText).matches()) {
            email.setError("Enter a valid email");
            email.requestFocus();
            return;
        }
        String usernameText = username.getText().toString().trim();
        if (usernameText.isEmpty()) {
            username.setError("Username is required");
            username.requestFocus();
            return;
        }

        String passwordText = password.getText().toString().trim();
        if (passwordText.isEmpty()) {
            password.setError("Password is required");
            password.requestFocus();
            return;
        } else if (passwordText.length() < 6) {
            password.setError("Password should be at least 6 character long");
            password.requestFocus();
        }
    }
}

