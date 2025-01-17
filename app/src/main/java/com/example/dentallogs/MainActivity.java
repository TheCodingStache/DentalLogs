package com.example.dentallogs;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.dentallogs.API.Client;
import com.example.dentallogs.Model.ModelLogin;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.JsonObject;

import java.io.Serializable;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements Serializable {
    FloatingActionButton logIn;
    private EditText username;
    private EditText password;
    private ProgressDialog loadingBar;
    ModelLogin modelLogin;
    ArrayList<ModelLogin> modelList;
    private String SHARED_PREFS = "sharedPrefs";
    private String TEXT = "text";
    private String TEXT1 = "text1";
    private final static String TAG = "MainActivity login";
    RelativeLayout mRelativeLayout;
    boolean connected = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRelativeLayout = findViewById(R.id.relativeLogin);
        loadingBar = new ProgressDialog(this);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        modelList = new ArrayList<>();
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
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("email", usernameText);
        jsonObject.addProperty("password", passwordText);
        if (TextUtils.isEmpty(usernameText)) {
            Snackbar.make(mRelativeLayout, "Παρακαλώ πληκτρολογήστε το username σας", Snackbar.LENGTH_LONG).show();
            username.requestFocus();
        } else if (TextUtils.isEmpty(passwordText)) {
            Snackbar.make(mRelativeLayout, "Παρακαλώ πληκτρολογήστε τον κωδικό σας", Snackbar.LENGTH_LONG).show();
        } else if (password.length() < 4) {
            password.setError("Ο κωδικός πρέπει να περιέχει τουλάχιστον 4 χαρακτήρες...");
            password.requestFocus();
        } else {
            loadingBar.setTitle("Γίνεται σύνδεση");
            loadingBar.setMessage("Παρακαλώ περιμένετε");
            loadingBar.setCanceledOnTouchOutside(false);
            loadingBar.show();
            Call<ModelLogin> call = Client
                    .getInstance()
                    .getApi()
                    .login(jsonObject);
            call.enqueue(new Callback<ModelLogin>() {
                @Override
                public void onResponse(Call<ModelLogin> call, Response<ModelLogin> response) {
                    if (response.code() == 200) {
                        modelLogin = response.body();
                        loadingBar.dismiss();
                        Toast.makeText(MainActivity.this, "Επιτυχής σύνδεση", Toast.LENGTH_SHORT).show();
                        String id = modelLogin.getId();
                        String doctor = modelLogin.getUsername();
                        Log.d(TAG, "onResponse: " + doctor + " " + id);
                        Intent transfer = new Intent(MainActivity.this, LabSelectionActivity.class);
                        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("doctorID", id);
                        editor.putString("doctorName", doctor);
                        editor.apply();
                        startActivity(transfer);
                    } else if (response.code() == 302) {
                        Snackbar.make(mRelativeLayout, "Λάθος στοιχεία, προσπαθήστε ξανά", Snackbar.LENGTH_LONG).show();
                        loadingBar.dismiss();
                    } else if (response.code() == 304) {
                        loadingBar.dismiss();
                        Snackbar.make(mRelativeLayout, "Ο λογαριασμός δεν έχει ενεργοποιηθεί ακόμα", Snackbar.LENGTH_LONG).show();
                    } else if (response.code() == 306) {
                        loadingBar.dismiss();
                        Snackbar.make(mRelativeLayout, "Ο Λογαριασμός δεν βρέθηκε", Snackbar.LENGTH_LONG).show();
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
//
//    public boolean isNetworkAvailable(Context context) {
//        ConnectivityManager connectivityManager = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
//        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
//    }
}
