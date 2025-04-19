package com.example.laratonaskincare;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private Animation top, bottom;
    private ImageView imgLogo;
    private TextView txtWelcome;
    private TextView txtAppName;
    private Button btnLogIn;
    private Button btnCreateAccount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        top = AnimationUtils.loadAnimation(this, R.anim.top);
        bottom = AnimationUtils.loadAnimation(this, R.anim.bottom);

        btnLogIn = findViewById(R.id.btnLogIn);
        txtAppName = findViewById(R.id.txtSkin);
        txtWelcome = findViewById(R.id.txtWelcome);
        btnCreateAccount = findViewById(R.id.btnCreateAccount);
        imgLogo = findViewById(R.id.imVLogo);

        btnLogIn.setAnimation(bottom);
        txtWelcome.setAnimation(bottom);
        btnCreateAccount.setAnimation(bottom);
        imgLogo.setAnimation(top);
        txtAppName.setAnimation(top);
        Intent intent = getIntent();


    }



    public void btnCreateAccountOnClick(View view) {
        Intent signUpIntent = new Intent(MainActivity.this, signUpActivity.class);
        startActivity(signUpIntent);

    }

    public void btnLogInOnCLick(View view) {
        Intent LogInIntent = new Intent(MainActivity.this, logInActivity.class);
        startActivity(LogInIntent);
    }
}