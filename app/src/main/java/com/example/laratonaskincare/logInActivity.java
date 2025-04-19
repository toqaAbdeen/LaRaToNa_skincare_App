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

public class logInActivity extends AppCompatActivity {

    private EditText edtUsername, edtPassword;
    private Button btnLogIn;
    private CheckBox chk;

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    private static final String USERNAME = "USERNAME";
    private static final String PASSWORD = "PASSWORD";
    private static final String FLAG = "FLAG";
    private boolean flag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_log_in);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        setupViews();
        setupSharedPrefs();
        checkPrefs();
    }

    private void setupSharedPrefs() {
        sharedPreferences = getSharedPreferences("user_prefs", MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    private void setupViews() {
        edtUsername = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);
        btnLogIn = findViewById(R.id.btnLogIn);
        chk = findViewById(R.id.chk);
    }

    private void checkPrefs() {
        flag = sharedPreferences.getBoolean(FLAG, false);
        if (flag) {
            String name = sharedPreferences.getString(USERNAME, "");
            String password = sharedPreferences.getString(PASSWORD, "");
            edtUsername.setText(name);
            edtPassword.setText(password);
            chk.setChecked(true);
        }
    }

    public void btnLogInOnCLick(View view) {
        String inputUsername = edtUsername.getText().toString().trim();
        String inputPassword = edtPassword.getText().toString().trim();

        String storedUsername = sharedPreferences.getString(USERNAME, "");
        String storedPassword = sharedPreferences.getString(PASSWORD, "");

        if (!inputUsername.isEmpty() && !inputPassword.isEmpty()) {
            if (inputUsername.equals(storedUsername) && inputPassword.equals(storedPassword)) {
                Toast.makeText(this, "Login Successful!", Toast.LENGTH_SHORT).show();

                if (chk.isChecked()) {
                    if (!flag) {
                        editor.putString(USERNAME, storedUsername);
                        editor.putString(PASSWORD, storedPassword);
                        editor.putBoolean(FLAG, true);
                        editor.commit();
                    }
                }

                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(logInActivity.this, CategoryActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }, 1000);
            }else {
                Toast.makeText(this, "Invalid username or password.", Toast.LENGTH_SHORT).show();
            }

    }else{
        Toast.makeText(this, "Please fill in all the fields.", Toast.LENGTH_SHORT).show();

    }
}}
