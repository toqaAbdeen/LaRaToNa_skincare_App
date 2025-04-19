package com.example.laratonaskincare;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class CategoryActivity extends AppCompatActivity {
    private TextView txtWelcomeLine1;
    private final static String WELCOME = "Welcome";
    private SearchView searchView;
    private ImageView imVSettings;
    private ImageView imVHome;
    private FloatingActionButton fab_cart;
    private final String[] categoryNames = {
            "Cleansers", "Moisturizers", "Serums", "Toners", "Sunscreens",
            "Exfoliators", "Masks", "Tools & Accessories", "Eye Care", "Lip Care"
    };

    private final int[] categoryImages = {
            R.drawable.cleansers_cover_page, R.drawable.moisturizers_cover_page,
            R.drawable.serums_cover_page, R.drawable.toners_cover_page, R.drawable.sunscreens_cover_page,
            R.drawable.exfoliants_cover_page, R.drawable.masks_cover_page, R.drawable.tools_accessories_cover_page,
            R.drawable.eye_care_cover_page, R.drawable.lip_care_cover_page
    };

    private CategoryAdapter categoryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_category);

        txtWelcomeLine1 = findViewById(R.id.txtWelcomeLine1);
        SharedPreferences sharedPreferences = getSharedPreferences("user_prefs", MODE_PRIVATE);
        String username = sharedPreferences.getString("USERNAME", "");
        txtWelcomeLine1.setText(username.isEmpty() ? WELCOME + "!" : WELCOME + " " + username + "!");

        RecyclerView recyclerView = findViewById(R.id.recycler_view_categories);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        categoryAdapter = new CategoryAdapter(this, categoryNames, categoryImages);
        recyclerView.setAdapter(categoryAdapter);

        searchView = findViewById(R.id.searchView);
        searchView.clearFocus();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                categoryAdapter.searchFilter(newText);
                return true;
            }
        });
        imVSettings = findViewById(R.id.imVSettings);
        imVSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CategoryActivity.this, SettingsActivity.class);
                startActivity(intent);
            }
        });
        imVHome = findViewById(R.id.imVHome);
        imVHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainIntent = new Intent(CategoryActivity.this, MainActivity.class);
                startActivity(mainIntent);
            }
        });
        fab_cart = findViewById(R.id.fab_cart);
        fab_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CategoryActivity.this, BagActivity.class);
                startActivity(intent);
            }
        });


    }
}
