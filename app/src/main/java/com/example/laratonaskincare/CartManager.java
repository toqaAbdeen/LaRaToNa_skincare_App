package com.example.laratonaskincare;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class CartManager {
    private static final String PREF_NAME = "user_prefs";
    private static final String CART_KEY = "CartItems";
    private static List<Product> cartItems = new ArrayList<>();

    public static void addToCart(Product newProduct, Context context) {
        List<Product> cartItems = getCartItems();

        boolean found = false;

        for (Product item : cartItems) {
            if (item.getName().equals(newProduct.getName())) {
                int updatedQuantity = item.getQuantity() + newProduct.getQuantity();
                item.setQuantity(updatedQuantity);
                found = true;
                break;
            }
        }

        if (!found) {
            cartItems.add(newProduct);
        }

        saveCart(context);
    }




    public static List<Product> getCartItems() {
        return cartItems;
    }

    public static void saveCart(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(cartItems);
        editor.putString(CART_KEY, json);
        editor.apply();

        Log.d("CartManager", "Cart saved: " + json);
    }


    public static void loadCart(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        String json = prefs.getString(CART_KEY, null);
        if (json != null) {
            Gson gson = new Gson();
            Type type = new TypeToken<List<Product>>() {}.getType();
            cartItems = gson.fromJson(json, type);

            Log.d("CartManager", "Cart loaded with " + cartItems.size() + " items.");
        } else {
            cartItems = new ArrayList<>();
            Log.d("CartManager", "Cart is empty.");
        }
    }


    public static void clearCart(Context context) {
        cartItems.clear();
        saveCart(context);
    }
}
