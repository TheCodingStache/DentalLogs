package com.example.dentallogs;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.dentallogs.API.Client;
import com.example.dentallogs.Model.ModelSignUp;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends AppCompatActivity {
    EditText username;
    EditText email;
    EditText password;
    EditText phone;
    EditText vat;
    EditText address;
    FloatingActionButton mFloatingActionButton;
    private ProgressDialog loadingBar;
    RelativeLayout mRelativeLayout;
    ModelSignUp mModelSignUp;

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
        password = findViewById(R.id.password);
        phone = findViewById(R.id.phone);
        vat = findViewById(R.id.vat);
        address = findViewById(R.id.address);
        mRelativeLayout = findViewById(R.id.signUpLinear);
        mFloatingActionButton = findViewById(R.id.signupfloat);
        loadingBar = new ProgressDialog(this);

        signUp.setOnClickListener(v -> {
            Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });

        mFloatingActionButton.setOnClickListener(v -> {
            userSignUp();
        });
    }

    private void userSignUp() {
        String emailText = email.getText().toString().trim();
        String usernameText = username.getText().toString().trim();
        String phoneText = phone.getText().toString();
        String addressText = address.getText().toString();
        String vatText = vat.getText().toString();
        String passwordText = password.getText().toString().trim();

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("username", usernameText);
        jsonObject.addProperty("email", emailText);
        jsonObject.addProperty("password", passwordText);
        jsonObject.addProperty("phone", phoneText);
        jsonObject.addProperty("afm", vatText);
        jsonObject.addProperty("address", addressText);

        if (TextUtils.isEmpty(emailText)) {
            email.setError("Το email είναι υποχρεωτικό");
            email.requestFocus();
        } else if (TextUtils.isEmpty(passwordText)) {
            password.setError("Ο κωδικός πρέπει να είναι τουλάχιστον 6 χαρακτήρες...");
            password.requestFocus();
        } else if (!Patterns.EMAIL_ADDRESS.matcher(emailText).matches()) {
            email.setError("Το email που πληκτρολογήσατε δεν είναι σωστό...");
            email.requestFocus();
        } else if (TextUtils.isEmpty(usernameText)) {
            username.setError("Το username είναι υποχρεωτικό...");
            username.requestFocus();
        } else if (TextUtils.isEmpty(phoneText)) {
            phone.setError("Το τηλέφωνο είναι υποχρεωτκό...");
            phone.requestFocus();
        } else if (TextUtils.isEmpty(addressText)) {
            address.setError("Η διεύθυνση είναι υποχρεωτική...");
            address.requestFocus();
        } else if (TextUtils.isEmpty(vatText)) {
            vat.setError("Το ΑΦΜ είναι υποχρεωτικό...");
            vat.requestFocus();
        } else {
            loadingBar.setTitle("Γίνεται σύνδεση");
            loadingBar.setMessage("Παρακαλώ περιμένετε");
            loadingBar.setCanceledOnTouchOutside(false);
            loadingBar.show();
            Call<ModelSignUp> call = Client
                    .getInstance()
                    .getApi()
                    .signup(jsonObject);
            call.enqueue(new Callback<ModelSignUp>() {
                @Override
                public void onResponse(Call<ModelSignUp> call, Response<ModelSignUp> response) {
                    if (response.code() == 200) {
                        mModelSignUp = response.body();
                        loadingBar.dismiss();
                        Toast.makeText(SignUpActivity.this, "Ο λογαριασμός δημιουργήθηκε με επιτυχία. Θα ειδοποιηθείτε με email για την ενεργοποίηση του!", Toast.LENGTH_LONG).show();
                        Intent transfer = new Intent(SignUpActivity.this, MainActivity.class);
                        startActivity(transfer);
                        finish();
                    } else if (response.code() == 300) {
                        loadingBar.dismiss();
                        Snackbar.make(mRelativeLayout, "Ο λογαριασμός υπάρχει ήδη", Snackbar.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ModelSignUp> call, Throwable t) {

                }
            });
        }
    }
}



