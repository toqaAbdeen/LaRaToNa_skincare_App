package com.example.laratonaskincare;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {

    private final List<Product> productList;
    private final List<Product> fullProductList;
    private final Context context;
    private final static String IN_STOCK = "In Stock: ";

    public ProductAdapter(Context context, List<Product> productList) {
        this.context = context;
        this.productList = productList;
        this.fullProductList = new ArrayList<>(productList);
        convertListToJson(productList);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView nameTextView, descriptionTextView, priceTextView, otherTextView;
        public TextView txtInventoryCount, txtNumberOrder;
        public ImageView imVAdd, imVMinus;
        public Button btnAddToCart;

        public ViewHolder(View view) {
            super(view);
            imageView = view.findViewById(R.id.image);
            nameTextView = view.findViewById(R.id.txtName);
            descriptionTextView = view.findViewById(R.id.txtDescription);
            priceTextView = view.findViewById(R.id.txtPrice);
            otherTextView = view.findViewById(R.id.txtOther);
            imVAdd = view.findViewById(R.id.imVAdd);
            imVMinus = view.findViewById(R.id.imVMinus);
            txtNumberOrder = view.findViewById(R.id.txtNumberOrder);
            txtInventoryCount = view.findViewById(R.id.txtInventoryCount);
            btnAddToCart = view.findViewById(R.id.btnAddToCart);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.product_card_views, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Product product = productList.get(position);

        holder.nameTextView.setText(product.getName());
        holder.descriptionTextView.setText(product.getDescription());
        holder.priceTextView.setText(String.valueOf(product.getPrice()));
        holder.otherTextView.setText(product.getOther());
        holder.imageView.setImageResource(product.getImageID());

        final int[] quantity = {1};
        holder.txtNumberOrder.setText(String.valueOf(quantity[0]));
        holder.txtInventoryCount.setText(IN_STOCK + product.getInventoryCount());

        holder.imVAdd.setOnClickListener(v -> {
            if (quantity[0] < product.getInventoryCount()) {
                quantity[0]++;
                holder.txtNumberOrder.setText(String.valueOf(quantity[0]));
            } else {
                Toast.makeText(context, "No more stock available!", Toast.LENGTH_SHORT).show();
            }
        });

        holder.imVMinus.setOnClickListener(v -> {
            if (quantity[0] > 1) {
                quantity[0]--;
                holder.txtNumberOrder.setText(String.valueOf(quantity[0]));
            }
        });

        holder.btnAddToCart.setOnClickListener(v -> {
            if (product.getInventoryCount() >= quantity[0]) {
                Product productWithQuantity = new Product(product.getName(), product.getPrice(), product.getImageID());
                productWithQuantity.setQuantity(quantity[0]);
                product.setInventoryCount(product.getInventoryCount() - quantity[0]);
                productWithQuantity.setInventoryCount(product.getInventoryCount());

                CartManager.addToCart(productWithQuantity, context);

                Toast.makeText(holder.itemView.getContext(),
                        quantity[0] + " x " + product.getName() + " added to cart",
                        Toast.LENGTH_SHORT).show();

                holder.txtInventoryCount.setText(IN_STOCK + product.getInventoryCount());
                quantity[0] = 1;
                holder.txtNumberOrder.setText("1");
            } else {
                Toast.makeText(context, "Not enough stock available!", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void updateCartQuantity(Product product, int newQuantity) {
        for (Product p : CartManager.getCartItems()) {
            if (p.getName().equals(product.getName())) {
                p.setQuantity(newQuantity);
                CartManager.saveCart(context);
                Log.d("CartManager", "Updated quantity for " + product.getName() + " to " + newQuantity);
                break;
            }
        }
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public void searchFilter(String text) {
        productList.clear();
        if (text.isEmpty()) {
            productList.addAll(fullProductList);
        } else {
            String searchText = text.toLowerCase();
            for (Product product : fullProductList) {
                if (product.getName().toLowerCase().contains(searchText) ||
                        product.getCategory().toLowerCase().contains(searchText)) {
                    productList.add(product);
                }
            }
        }
        notifyDataSetChanged();
    }

    private void convertListToJson(List<Product> productList) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(productList);
        Log.d("Product JSON", json);
    }
}
