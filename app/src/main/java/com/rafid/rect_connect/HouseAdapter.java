package com.rafid.rect_connect;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;

public class HouseAdapter extends RecyclerView.Adapter<HouseAdapter.HouseViewHolder> {

    private List<House> houses;
    private Context context;

    // Constructor
    public HouseAdapter(Context context, List<House> houses) {
        this.context = context;
        this.houses = houses;
    }

    @NonNull
    @Override
    public HouseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.house_item, parent, false);
        return new HouseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HouseViewHolder holder, int position) {
        House house = houses.get(position);

        // Bind data to each item
        holder.nameTextView.setText(house.getName());
        holder.descriptionTextView.setText(house.getDescription());
        holder.imageView.setImageResource(house.getImageResource());

        // Handle click on the entire card
        holder.cardView.setOnClickListener(v -> navigateToDetails(house));

        // Handle click on "See More" button
        holder.seeMoreButton.setOnClickListener(v -> navigateToDetails(house));
    }

    private void navigateToDetails(House house) {
        Intent intent = new Intent(context, HouseDetailActivity.class);

        // Passing data to HouseDetailActivity
        intent.putExtra("name", house.getName());
        intent.putExtra("description", house.getDescription());
        intent.putExtra("imageResource", house.getImageResource());
        intent.putExtra("price", house.getPrice());
        intent.putExtra("details", house.getDetails());
        intent.putExtra("amenities", house.getAmenities());
        intent.putExtra("bedrooms", house.getBedrooms());
        intent.putExtra("bathrooms", house.getBathrooms());
        intent.putExtra("nearShoppingComplex", house.isNearShoppingComplex());
        intent.putExtra("latitude", house.getLatitude());  // Pass latitude
        intent.putExtra("longitude", house.getLongitude()); // Pass longitude
        intent.putIntegerArrayListExtra("detailImages", new ArrayList<>(house.getDetailImages()));

        context.startActivity(intent);
    }

    @Override
    public int getItemCount() {
        return houses != null ? houses.size() : 0;
    }

    // ViewHolder for RecyclerView
    public static class HouseViewHolder extends RecyclerView.ViewHolder {
        public TextView nameTextView;
        public TextView descriptionTextView;
        public ImageView imageView;
        public MaterialButton seeMoreButton; // Reference to the "See More" button
        public CardView cardView; // Reference the CardView

        public HouseViewHolder(View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.card_view); // Reference the CardView
            nameTextView = itemView.findViewById(R.id.name);
            descriptionTextView = itemView.findViewById(R.id.description);
            imageView = itemView.findViewById(R.id.image);
            seeMoreButton = itemView.findViewById(R.id.see_more); // Reference the "See More" button
        }
    }
}
