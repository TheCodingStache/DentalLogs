package com.example.dentallogs;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.dentallogs.API.Client;
import com.example.dentallogs.Model.ModelLogin;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    FloatingActionButton logIn;
    private EditText username;
    private EditText password;
    private ProgressDialog loadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadingBar = new ProgressDialog(this);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        TextView signIn = findViewById(R.id.signUpText);
        username = findViewById(R.id.usernameLogin);
        password = findViewById(R.id.passwordLogin);
        logIn = findViewById(R.id.logIn);
        signIn.setOnClickListener(v -> {
            openSignUp();
        });
        logIn.setOnClickListener(v -> {
            userLogin();

        });
    }

    private void userLogin() {
        String usernameText = username.getText().toString();
        String passwordText = password.getText().toString();
        if (TextUtils.isEmpty(usernameText)) {
            Toast.makeText(this, "Παρακαλώ πληκτρολογήστε το username σας", Toast.LENGTH_LONG).show();
            username.requestFocus();
        } else if (TextUtils.isEmpty(passwordText)) {
            Toast.makeText(this, "Παρακαλώ πληκτρολογήστε τον κωδικό σας", Toast.LENGTH_LONG).show();
        } else if (password.length() < 6) {
            password.setError("Ο κωδικός πρέπει να περιέχει τουλάχιστον 6 χαρακτήρες...");
            password.requestFocus();
        } else {
            Call<ModelLogin> call = Client
                    .getInstance()
                    .getApi()
                    .login(usernameText, passwordText);
            call.enqueue(new Callback<ModelLogin>() {
                @Override
                public void onResponse(Call<ModelLogin> call, Response<ModelLogin> response) {
                    isNetworkAvailable(MainActivity.this);
                    ModelLogin modelLogin = response.body();
                    if (response.isSuccessful()) {
                        Toast.makeText(MainActivity.this, "Επιτυχής σύνδεση", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(MainActivity.this, LabSelectionActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(MainActivity.this, "Λάθος στοιχεία", Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<ModelLogin> call, Throwable t) {

                }
            });
        }
    }

    private void openSignUp() {
        Intent signUp = new Intent(this, SignUpActivity.class);
        startActivity(signUp);
    }

    public boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }
}
