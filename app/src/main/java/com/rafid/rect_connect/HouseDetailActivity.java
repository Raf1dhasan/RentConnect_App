package com.rafid.rect_connect;

import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class HouseDetailActivity extends AppCompatActivity {

    private ImageView imageView;
    private TextView nameTextView, descriptionTextView, priceTextView, detailsTextView, amenitiesTextView;
    private RecyclerView detailImagesRecyclerView;
    private Button applyButton;
    private FloatingActionButton mapButton, threeDButton;

    private double latitude;  // Latitude of the property
    private double longitude; // Longitude of the property

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_house_detail);

        // Enable the back button in the ActionBar and set the title to "Details"
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Details");
        }

        // Initialize the UI components
        imageView = findViewById(R.id.image);
        nameTextView = findViewById(R.id.name);
        descriptionTextView = findViewById(R.id.description);
        priceTextView = findViewById(R.id.price);
        detailsTextView = findViewById(R.id.details);
        amenitiesTextView = findViewById(R.id.amenities);
        detailImagesRecyclerView = findViewById(R.id.detail_images_recycler_view);
        applyButton = findViewById(R.id.apply_button);
        mapButton = findViewById(R.id.map_button);
        threeDButton = findViewById(R.id.three_d_button);

        // Set up data for the views
        setupData();

        // Set up listeners for the buttons
        setupButtonListeners();
    }

    private void setupData() {
        // Retrieve data from Intent
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String description = intent.getStringExtra("description");
        String price = intent.getStringExtra("price");
        String details = intent.getStringExtra("details");
        String amenities = intent.getStringExtra("amenities");
        int imageResource = intent.getIntExtra("imageResource", 0);
        List<Integer> detailImages = intent.getIntegerArrayListExtra("detailImages");

        // Retrieve latitude and longitude
        latitude = intent.getDoubleExtra("latitude", 0.0);
        longitude = intent.getDoubleExtra("longitude", 0.0);

        // Set data to views
        nameTextView.setText(name);
        descriptionTextView.setText(description);
        priceTextView.setText("Rent: " + price);
        detailsTextView.setText(details);
        amenitiesTextView.setText(amenities);
        imageView.setImageResource(imageResource);

        // Set up RecyclerView for detail images with a grid layout
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        detailImagesRecyclerView.setLayoutManager(layoutManager);

        // Pass image click listener to adapter
        DetailImagesAdapter detailImagesAdapter = new DetailImagesAdapter(detailImages, this::showImagePreviewDialog);
        detailImagesRecyclerView.setAdapter(detailImagesAdapter);
    }

    private void setupButtonListeners() {
        // Map Button opens MapsActivity with location data
        mapButton.setOnClickListener(v -> {
            Intent intent = new Intent(HouseDetailActivity.this, MapsActivity.class);
            intent.putExtra("latitude", latitude);
            intent.putExtra("longitude", longitude);
            startActivity(intent);
        });

        // 3D Button opens a video URL (like a virtual tour)
        threeDButton.setOnClickListener(v -> {
            String videoUrl = "https://www.youtube.com/shorts/v_CkVqKb0is"; // video link
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(videoUrl));
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setPackage("com.google.android.youtube"); // Opens in YouTube app if available
            startActivity(intent);
        });

        // Apply Button opens the rental application form
        applyButton.setOnClickListener(v -> {
            Intent intent = new Intent(HouseDetailActivity.this, RentalFormActivity.class);
            startActivity(intent);
        });
    }

    private void showImagePreviewDialog(int imageResId) {
        // Show an image preview dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_image_preview, null);
        builder.setView(dialogView);

        ImageView previewImage = dialogView.findViewById(R.id.preview_image);
        previewImage.setImageResource(imageResId);

        AlertDialog dialog = builder.create();
        dialog.show();

        // Dismiss the dialog when the image is clicked
        previewImage.setOnClickListener(v -> dialog.dismiss());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish(); // Close the activity when back button is pressed
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
