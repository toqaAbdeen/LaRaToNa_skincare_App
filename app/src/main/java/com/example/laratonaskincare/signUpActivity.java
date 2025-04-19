package com.example.laratonaskincare;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class signUpActivity extends AppCompatActivity {

    private EditText edtUsername, edtEmail, edtPhoneNumber, edtPassword, edtConfirmPassword;
    private CheckBox cksPolicy;
    private Button btnCreateAccount;

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    private static final String PREF_NAME = "user_prefs";
    private static final String USERNAME = "USERNAME";
    private static final String EMAIL = "EMAIL";
    private static final String PHONENUMBER = "PHONENUMBER";
    private static final String PASSWORD = "PASSWORD";
    private static final String CONFIRMPASSWORD = "CONFIRMPASSWORD";
    private static final String FLAG = "FLAG";
    private static final String POLICY_CHECKED = "POLICY_CHECKED";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sign_up);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        setupViews();
        setupSharedPrefs();
        checkPrefs();
    }

    private void setupViews() {
        edtUsername = findViewById(R.id.edtUsername);
        edtEmail = findViewById(R.id.edtEmail);
        edtPhoneNumber = findViewById(R.id.edtPhoneNumber);
        edtPassword = findViewById(R.id.edtPassword);
        edtConfirmPassword = findViewById(R.id.edtConfirmPassword);
        cksPolicy = findViewById(R.id.cksPolicy);
        btnCreateAccount = findViewById(R.id.btnCreateAccount);
    }

    private void setupSharedPrefs() {
        sharedPreferences = getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    private void checkPrefs() {
        boolean flag = sharedPreferences.getBoolean(FLAG, false);
        if (flag) {
            edtUsername.setText(sharedPreferences.getString(USERNAME, ""));
            edtEmail.setText(sharedPreferences.getString(EMAIL, ""));
            edtPhoneNumber.setText(sharedPreferences.getString(PHONENUMBER, ""));
            edtPassword.setText(sharedPreferences.getString(PASSWORD, ""));
            edtConfirmPassword.setText(sharedPreferences.getString(CONFIRMPASSWORD, ""));
            cksPolicy.setChecked(sharedPreferences.getBoolean(POLICY_CHECKED, false));
        }
    }

    public void btnCreateAccountOnClick(View view) {
        String username = edtUsername.getText().toString().trim();
        String email = edtEmail.getText().toString().trim();
        String phone = edtPhoneNumber.getText().toString().trim();
        String password = edtPassword.getText().toString().trim();
        String confirmPassword = edtConfirmPassword.getText().toString().trim();
        boolean isPolicyChecked = cksPolicy.isChecked();

        if (username.isEmpty() || email.isEmpty() || phone.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            Toast.makeText(this, "Please fill in all the fields.", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!password.equals(confirmPassword)) {
            Toast.makeText(this, "Passwords do not match.", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!isPolicyChecked) {
            Toast.makeText(this, "Please agree to the Terms of Service.", Toast.LENGTH_SHORT).show();
            return;
        }

        editor.putString(USERNAME, username);
        editor.putString(EMAIL, email);
        editor.putString(PHONENUMBER, phone);
        editor.putString(PASSWORD, password);
        editor.putString(CONFIRMPASSWORD, confirmPassword);
        editor.putBoolean(POLICY_CHECKED, isPolicyChecked);
        editor.putBoolean(FLAG, true);
        editor.commit();

        Toast.makeText(this, "Sign Up Successful!", Toast.LENGTH_SHORT).show();
        Log.d("DEBUG_SIGNUP", "Stored Username: " + username);
        Log.d("DEBUG_SIGNUP", "Stored Password: " + password);

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(signUpActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, 1000);
    }

}
