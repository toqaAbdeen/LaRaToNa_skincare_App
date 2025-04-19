package com.example.laratonaskincare;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    private final Context context;
    private List<String> captions;
    private List<Integer> imageIds;

    private final List<String> fullCaptions;
    private final List<Integer> fullImageIds;

    public static final String CATEGORY = "category";

    public CategoryAdapter(Context context, String[] captionsArray, int[] imageIdsArray) {
        this.context = context;
        this.captions = new ArrayList<>();
        this.imageIds = new ArrayList<>();
        this.fullCaptions = new ArrayList<>();
        this.fullImageIds = new ArrayList<>();

        for (int i = 0; i < captionsArray.length; i++) {
            this.captions.add(captionsArray[i]);
            this.imageIds.add(imageIdsArray[i]);
            this.fullCaptions.add(captionsArray[i]);
            this.fullImageIds.add(imageIdsArray[i]);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CardView cardView = (CardView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.category_card_views, parent, false);
        return new ViewHolder(cardView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        CardView cardView = holder.cardView;

        ImageView imageView = cardView.findViewById(R.id.image);
        Drawable drawable = ContextCompat.getDrawable(cardView.getContext(), imageIds.get(position));
        imageView.setImageDrawable(drawable);
        imageView.setContentDescription(captions.get(position));

        TextView textView = cardView.findViewById(R.id.txtName);
        textView.setText(captions.get(position));

        cardView.setOnClickListener(v -> {
            Intent intent = new Intent(context, ProductActivity.class);
            intent.putExtra(CATEGORY, captions.get(position));
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return captions.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final CardView cardView;

        public ViewHolder(CardView v) {
            super(v);
            cardView = v;
        }
    }

    public void searchFilter(String text) {
        captions.clear();
        imageIds.clear();

        if (text.isEmpty()) {
            captions.addAll(fullCaptions);
            imageIds.addAll(fullImageIds);
        } else {
            text = text.toLowerCase();
            for (int i = 0; i < fullCaptions.size(); i++) {
                if (fullCaptions.get(i).toLowerCase().contains(text)) {
                    captions.add(fullCaptions.get(i));
                    imageIds.add(fullImageIds.get(i));
                }
            }
        }
        notifyDataSetChanged();
    }
}
