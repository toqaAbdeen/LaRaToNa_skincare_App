package com.example.laratonaskincare;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class SettingsActivity extends AppCompatActivity {
    private EditText edtUsername;
    private EditText edtPassword;
    private Button btnSaveButton;
    private Button bntLogout;
    private FloatingActionButton fab_cart;

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    private static final String USERNAME = "USERNAME";
    private static final String PASSWORD = "PASSWORD";
    private ImageView imVHome;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_settings);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;

        });
        Intent intent = getIntent();
        setUpViews();
        setupSharedPrefs();


    }

    private void setUpViews() {
        edtUsername = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);
        btnSaveButton = findViewById(R.id.btnSaveButton);
        bntLogout = findViewById(R.id.bntLogout);
        imVHome = findViewById(R.id.imVHome);
        imVHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainIntent = new Intent(SettingsActivity.this, CategoryActivity.class);
                startActivity(mainIntent);
            }
        });
        fab_cart = findViewById(R.id.fab_cart);
        fab_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingsActivity.this, BagActivity.class);
                startActivity(intent);
            }
        });

    }

    private void setupSharedPrefs() {
        sharedPreferences = getSharedPreferences("user_prefs", MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void btnSaveButtonOnCLick(View view) {
        String newUsername = edtUsername.getText().toString().trim();
        String newPassword = edtPassword.getText().toString().trim();

        if (newUsername.isEmpty() || newPassword.isEmpty()) {
            Toast.makeText(this, "You haven't made any changes to save.", Toast.LENGTH_SHORT).show();
        } else {
            editor.putString(USERNAME, newUsername);
            editor.putString(PASSWORD, newPassword);
            editor.commit();
            Toast.makeText(this, "All changes have been updated!", Toast.LENGTH_SHORT).show();


        }
    }

    public void bntLogoutOnCLick(View view) {
        editor.clear();
        editor.commit();
        startActivity(new Intent(SettingsActivity.this, MainActivity.class));
        finish();
    }
}