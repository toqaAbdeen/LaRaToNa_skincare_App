package com.example.laratonaskincare;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class BagActivity extends AppCompatActivity {

    GridLayout cartGrid;
    private ImageView imVHome;
    private ImageView imVSettings;
    private Button btnConfirmBuy;
    private TextView txtTotal;
    private static final String TOTAL = "Total:";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bag);

        setUpView();
        CartManager.loadCart(this);

        for (Product product : CartManager.getCartItems()) {
            String name = product.getName();
            double unitPrice = product.getPrice();
            int quantity = product.getQuantity();
            double totalPrice = unitPrice * quantity;
            int imageRes = product.getImageID();

            String displayPrice = quantity + " x $" + String.format("%.2f", unitPrice)
                    + " = $" + String.format("%.2f", totalPrice);

            addItemToGrid(name, displayPrice, imageRes);
        }

        recalculateTotal();
    }

    public void setUpView() {
        imVSettings = findViewById(R.id.imVSettings);
        imVSettings.setOnClickListener(v -> {
            Intent settIntent = new Intent(BagActivity.this, SettingsActivity.class);
            startActivity(settIntent);
        });

        imVHome = findViewById(R.id.imVHome);
        imVHome.setOnClickListener(v -> {
            Intent mainIntent = new Intent(BagActivity.this, CategoryActivity.class);
            startActivity(mainIntent);
        });

        cartGrid = findViewById(R.id.girdLayCart);
        txtTotal = findViewById(R.id.txtTotal);
        btnConfirmBuy = findViewById(R.id.btnConfirmBuy);

        btnConfirmBuy.setOnClickListener(v -> {
            if (txtTotal.getText().toString().trim().equals("Total: $0.00") || txtTotal.getText().toString().trim().isEmpty()) {
                Toast.makeText(v.getContext(), "Your cart is empty. Add some glow first! ✨", Toast.LENGTH_SHORT).show();
            } else {
                new Handler().postDelayed(() -> {
                    cartGrid.removeAllViews();
                    CartManager.clearCart(v.getContext());
                    Toast.makeText(v.getContext(), "Thank you! Your skincare treats are on their way 💌", Toast.LENGTH_SHORT).show();
                    txtTotal.setText("Total: $0.00");
                }, 1500);
            }
        });
    }

    private void addItemToGrid(String name, String price, int imageRes) {
        View itemView = LayoutInflater.from(this).inflate(R.layout.cart_item, cartGrid, false);

        ImageView productImage = itemView.findViewById(R.id.productImage);
        TextView productName = itemView.findViewById(R.id.productName);
        TextView productPrice = itemView.findViewById(R.id.productPrice);
        ImageView deleteIcon = itemView.findViewById(R.id.imvDeleteIcon);

        productName.setText(name);
        productPrice.setText(price);
        productImage.setImageResource(imageRes);

        deleteIcon.setOnClickListener(v -> {
            List<Product> cartItems = CartManager.getCartItems();
            for (int i = 0; i < cartItems.size(); i++) {
                if (cartItems.get(i).getName().equals(name)) {
                    cartItems.remove(i);
                    break;
                }
            }

            CartManager.saveCart(this);
            cartGrid.removeView(itemView);
            recalculateTotal();

            Toast.makeText(this, "Item removed from cart 🗑️", Toast.LENGTH_SHORT).show();
        });

        cartGrid.addView(itemView);
    }

    private void recalculateTotal() {
        double newTotal = 0;
        for (Product product : CartManager.getCartItems()) {
            newTotal += product.getPrice() * product.getQuantity();
        }
        txtTotal.setText(TOTAL + " $" + String.format("%.2f", newTotal));
    }
}
