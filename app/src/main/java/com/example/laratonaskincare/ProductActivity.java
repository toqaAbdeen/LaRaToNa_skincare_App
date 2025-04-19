package com.example.laratonaskincare;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ProductActivity extends AppCompatActivity {
    private ProductAdapter productAdapter;
    private ArrayList<Product> allProducts;
    private SearchView searchView;
    private FloatingActionButton fab_cart;

    private TextView txtNumberOrder;
    private Button btnAddToCart;
    private ImageView imVAdd;
    private ImageView imVMinus;
    private ImageView imVSettings;
    private ImageView imVHome;



    private int numberOrder = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        setUpView();
        RecyclerView recyclerView = findViewById(R.id.recycler_view_products);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Intent intent = getIntent();
        String category = intent.getStringExtra(CategoryAdapter.CATEGORY);

        allProducts = new ArrayList<>();
        addAllProducts();

        ArrayList<Product> categoryProducts = new ArrayList<>();
        for (Product product : allProducts) {
            if (product.getCategory().equals(category)) {
                categoryProducts.add(product);
            }
        }

        productAdapter = new ProductAdapter(this,categoryProducts);
        recyclerView.setAdapter(productAdapter);

        searchView = findViewById(R.id.searchView);
        searchView.clearFocus();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                productAdapter.searchFilter(newText);
                return true;
            }
        });


    }

    private void setUpView() {
        txtNumberOrder = findViewById(R.id.txtNumberOrder);
        btnAddToCart = findViewById(R.id.btnAddToCart);
        imVAdd = findViewById(R.id.imVAdd);
        imVMinus = findViewById(R.id.imVMinus);
        imVSettings = findViewById(R.id.imVSettings);

        fab_cart = findViewById(R.id.fab_cart);
        fab_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProductActivity.this, BagActivity.class);
                startActivity(intent);
            }
        });
        imVSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProductActivity.this, SettingsActivity.class);
                startActivity(intent);
            }
        });
        imVHome = findViewById(R.id.imVHome);
        imVHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainIntent = new Intent(ProductActivity.this, CategoryActivity.class);
                startActivity(mainIntent);
            }
        });
        if (txtNumberOrder == null || btnAddToCart == null || imVAdd == null || imVMinus == null) {
            Log.e("InitView", "One or more views are null!");
            return;
        }

        imVAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberOrder++;
                txtNumberOrder.setText(String.valueOf(numberOrder));

            }
        });

        imVMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (numberOrder > 1) {
                    numberOrder--;
                }
                txtNumberOrder.setText(String.valueOf(numberOrder));
            }
        });
        btnAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
    private void addAllProducts() {
        // Cleansers
        allProducts.add(new Product("Gentle Gel Cleanser", R.drawable.gel_cleanser, "A lightweight gel-based cleanser ideal for daily use.", 49.00, "Cleansers", "Perfect for oily skin – Now at a special price!", 10));
        allProducts.add(new Product("Nourishing Cream Cleanser", R.drawable.cream_cleanser, "Rich cream cleanser that soothes and hydrates dry skin.", 49.00, "Cleansers", "Great for dry skin – Order now before it runs out!", 50));
        allProducts.add(new Product("Micellar Water", R.drawable.micellar_water, "Removes makeup and impurities without rinsing.", 45.00, "Cleansers", "Gentle & effective – Best seller this week!", 87));
        allProducts.add(new Product("Deep Pore Foam Cleanser", R.drawable.foam_cleanser, "Foamy cleanser that refreshes and deeply cleanses pores.", 47.00, "Cleansers", "Instant freshness – Limited-time offer!", 4));
        allProducts.add(new Product("Oil-Based Cleanser", R.drawable.oil_cleanser, "Perfect first step in double cleansing to remove makeup.", 55.00, "Cleansers", "Best for heavy makeup removal – Try it now!", 6));

        // Toners
        allProducts.add(new Product("Hydrating Toner", R.drawable.hydrating_toner, "Deeply hydrates and prepares skin for serums.", 42.00, "Toners", "Your everyday hydration boost – Available now!", 41));
        allProducts.add(new Product("AHA/BHA Exfoliating Toner", R.drawable.exfoliating_toner, "Brightens and smooths skin with gentle exfoliation.", 59.00, "Toners", "Get glowing skin – Order before stock ends!", 11));
        allProducts.add(new Product("pH-Balancing Toner", R.drawable.ph_balancing_toner, "Restores natural skin balance after cleansing.", 40.00, "Toners", "Essential step in your routine – Hot deal!", 3));
        allProducts.add(new Product("Soothing Calming Toner", R.drawable.calming_toner, "Reduces redness and irritation for sensitive skin.", 48.00, "Toners", "Perfect for sensitive skin – Act fast!", 29));

        // Serums
        allProducts.add(new Product("Brightening Vitamin C Serum", R.drawable.vitamin_c_serum, "Boosts radiance and evens out skin tone.", 89.00, "Serums", "Top pick for dull skin – Shop now!", 77));
        allProducts.add(new Product("Hydration Serum with Hyaluronic Acid", R.drawable.hyaluronic_acid_serum, "Locks in moisture and plumps the skin.", 85.00, "Serums", "Long-lasting hydration – A must-have!", 20));
        allProducts.add(new Product("Niacinamide Serum", R.drawable.niacinamide_serum, "Controls oil and minimizes pores.", 75.00, "Serums", "Perfect for acne-prone skin – Get yours today!", 10));
        allProducts.add(new Product("Anti-Aging Retinol Serum", R.drawable.retinol_serum, "Reduces wrinkles and fine lines over time.", 95.00, "Serums", "Visible results in weeks – Limited stock!", 8));
        allProducts.add(new Product("Collagen-Boost Peptide Serum", R.drawable.peptide_or_collagen_boosting_serum, "Improves skin elasticity and firmness.", 99.00, "Serums", "Firm & smooth skin – Back in stock!", 32));

        // Moisturizers
        allProducts.add(new Product("Hydra Boost Gel", R.drawable.gel_moisturizer, "Lightweight gel moisturizer for hydration", 85.00, "Moisturizers", "Perfect for daily use", 3));
        allProducts.add(new Product("Deep Nourish Cream", R.drawable.cream_moisturizer, "Rich cream for dry skin hydration", 92.50, "Moisturizers", "Ideal for nighttime repair", 150));
        allProducts.add(new Product("Refreshing Aqua Moisturizer", R.drawable.water_based_moisturizer, "Water-based formula for oil control", 78.00, "Moisturizers", "Best for oily/combination skin", 30));
        allProducts.add(new Product("Night Repair Cream", R.drawable.night_creams, "Overnight hydration and repair", 99.00, "Moisturizers", "Use before sleep", 5));
        allProducts.add(new Product("SPF Moisturizer", R.drawable.moisturizers_with_spf, "Hydrates and protects from sun", 89.99, "Moisturizers", "Day use with SPF 30", 2));
        allProducts.add(new Product("Organic Shea Butter", R.drawable.shea_butter, "Natural butter for intense moisture", 65.00, "Moisturizers", "Great for elbows, knees, and lips", 74));

        // Exfoliators
        allProducts.add(new Product("Gentle Chemical Exfoliant", R.drawable.chemical_exfoliants, "Removes dead skin with AHAs/BHAs", 75.00, "Exfoliators", "Recommended twice weekly", 89));
        allProducts.add(new Product("Soft Physical Scrub", R.drawable.physical_scrubs, "Mild scrub for sensitive skin", 62.00, "Exfoliators", "Gentle and effective", 24));
        allProducts.add(new Product("Natural Enzyme Exfoliant", R.drawable.enzyme_exfoliators, "Fruit enzymes for glowing skin", 79.50, "Exfoliators", "Made with papaya and pineapple", 68));

        // Masks
        allProducts.add(new Product("Detox Clay Mask", R.drawable.clay_masks, "Purifies and tightens pores", 70.00, "Masks", "Perfect for oily skin", 100));
        allProducts.add(new Product("Hydrating Sheet Mask", R.drawable.sheet_masks, "Deep hydration and soothing", 18.00, "Masks", "Single-use, easy to apply", 30));
        allProducts.add(new Product("Overnight Glow Mask", R.drawable.overnight_sleeping_masks, "Works while you sleep", 85.00, "Masks", "Leave on overnight", 1));
        allProducts.add(new Product("Peel-Off Mask", R.drawable.peel_off_masks, "Exfoliates and brightens", 65.00, "Masks", "Easy removal, no mess", 8));

        // Sunscreens
        allProducts.add(new Product("Mineral Shield SPF 50", R.drawable.mineral_physical_sunscreen, "Non-greasy formula for sensitive skin", 95.00, "Sunscreens", "Water-resistant for 80 minutes", 87));
        allProducts.add(new Product("Ultra-Light Chemical Sunscreen", R.drawable.chemical_sunscreen, "Fast absorption, no white cast", 88.00, "Sunscreens", "Best for outdoor activities", 53));
        allProducts.add(new Product("Tinted Glow Sunscreen", R.drawable.tinted_sunscreen, "SPF 30 with natural tint", 99.00, "Sunscreens", "Blends with skin tone", 99));
        allProducts.add(new Product("Sunscreen Stick", R.drawable.sunscreen_sticks_or_sprays, "Portable sun protection", 76.00, "Sunscreens", "Great for reapplication", 74));

        // Lip Care
        allProducts.add(new Product("Moisturizing Lip Balm", R.drawable.lip_balm, "Hydrates and repairs dry lips", 35.00, "Lip Care", "With natural oils", 36));
        allProducts.add(new Product("Sugar Lip Scrub", R.drawable.lip_scrubs, "Exfoliates and smooths lips", 42.00, "Lip Care", "Sweet and effective", 21));
        allProducts.add(new Product("Overnight Lip Mask", R.drawable.lip_masks, "Deep repair while you sleep", 58.00, "Lip Care", "For chapped and dry lips", 88));

        // Eye Care
        allProducts.add(new Product("Hydrating Eye Cream", R.drawable.eye_cream, "Reduces puffiness and fine lines", 120.00, "Eye Care", "Anti-aging formula", 49));
        allProducts.add(new Product("Cooling Eye Gel Patches", R.drawable.cooling_eye_gels_or_patches, "Refreshes tired eyes", 50.00, "Eye Care", "Use before makeup", 20));
        allProducts.add(new Product("Dark Circle Corrector", R.drawable.dark_circle_correctors, "Brightens under-eye area", 115.00, "Eye Care", "With caffeine and vitamin C", 63));

        // Tools & Accessories
        allProducts.add(new Product("Jade Face Roller", R.drawable.face_rollers, "Boosts circulation and reduces puffiness", 65.00, "Tools & Accessories", "Use daily for best results", 95));
        allProducts.add(new Product("Gua Sha Stone", R.drawable.gua_sha_stones, "Sculpts and tones facial muscles", 59.00, "Tools & Accessories", "Perfect for relaxing massages", 24));
        allProducts.add(new Product("Electric Cleansing Brush", R.drawable.cleansing_brushes, "Deep cleans and exfoliates", 185.00, "Tools & Accessories", "Waterproof and rechargeable", 3));
        allProducts.add(new Product("Facial Steamer", R.drawable.facial_steamers, "Opens pores for deeper cleansing", 210.00, "Tools & Accessories", "Ideal for home spa days", 1));
        allProducts.add(new Product("Silicone Mask Brush", R.drawable.mask_brushes, "Hygienic and easy to clean", 35.00, "Tools & Accessories", "For smooth mask application", 59));
        allProducts.add(new Product("Mini Skincare Fridge", R.drawable.mini_fridges, "Keeps products fresh", 299.00, "Tools & Accessories", "Compact and stylish design", 140));
        allProducts.add(new Product("Cotton Pads", R.drawable.cotton_pads, "Soft and gentle for daily use", 15.00, "Tools & Accessories", "For applying toner or makeup removal", 52));
        allProducts.add(new Product("Biodegradable Wet Wipes", R.drawable.wet_wipes, "Eco-friendly and refreshing", 25.00, "Tools & Accessories", "Great for travel or on-the-go use", 7));
    }

}
