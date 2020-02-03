package com.example.dentallogs;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    FloatingActionButton logIn;
    private EditText username;
    private EditText password;
    private boolean doubleBackToExitPressedOnce = false;
    private ProgressDialog loadingBar;

    private final static String TAG = "MainActivity login";
    RelativeLayout mRelativeLayout;
    boolean connected = false;

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            finishActivity(R.layout.activity_main);
            finish();
        }
        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Πηγαίνετε πίσω μία ακόμη φορά για να αποσυνδεθείτε", Toast.LENGTH_SHORT).show();
        new Handler().postDelayed(() -> doubleBackToExitPressedOnce = false, 2000);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRelativeLayout = findViewById(R.id.relativeLogin);
        loadingBar = new ProgressDialog(this);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            //we are connected to a network
            connected = true;
        }
        else
            connected = false;
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
            password.setError("Ο κωδικός πρέπει να περιέχει τουλάχιστον 6 χαρακτήρες...");
            password.requestFocus();
        } else {
            loadingBar.setTitle("Γίνεται σύνδεση");
            loadingBar.setMessage("Παρακαλώ περιμένετε");
            loadingBar.setCanceledOnTouchOutside(false);
            loadingBar.show();
            Call<ModelLogin[]> call = Client
                    .getInstance()
                    .getApi()
                    .login(jsonObject);
            call.enqueue(new Callback<ModelLogin[]>() {
                @Override
                public void onResponse(Call<ModelLogin[]> call, Response<ModelLogin[]> response) {
                    if (response.code() == 200) {
                        ModelLogin[] modelLogin = response.body();
                        loadingBar.dismiss();
                        Toast.makeText(MainActivity.this, "Επιτυχής σύνδεση", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainActivity.this, LabSelectionActivity.class);
                        startActivity(intent);
                    } else if (response.code() == 302) {
                        Snackbar.make(mRelativeLayout, "Λάθος στοιχεία, προσπαθήστε ξανά", Snackbar.LENGTH_LONG).show();
                        loadingBar.dismiss();
                    } else if (response.code() == 304) {
                        Snackbar.make(mRelativeLayout, "Ο λογαριασμός δεν έχει ενεργοποιηθεί ακόμα", Snackbar.LENGTH_LONG).show();
                    } else if (response.code() == 306) {
                        Snackbar.make(mRelativeLayout, "Ο Λογαριασμός δεν βρέθηκε", Snackbar.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<ModelLogin[]> call, Throwable t) {

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
