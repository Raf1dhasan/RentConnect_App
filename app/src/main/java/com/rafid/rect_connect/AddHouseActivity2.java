package com.rafid.rect_connect;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class AddHouseActivity2 extends AppCompatActivity {

    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private static final int REQUEST_IMAGE_PICK = 2;
    private static final int REQUEST_CAMERA_PERMISSION = 100;

    private TextInputEditText locationInput, roomsInput, priceInput, descriptionInput, amenitiesInput, emailInput, phoneInput;
    private ImageView imagePreview;
    private MaterialButton submitButton, uploadImageButton;
    private Uri imageUri;
    private String currentPhotoPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_house2);

        // Enable the back button and set the title in the ActionBar
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("List Your Property");
        }

        // Initialize views
        locationInput = findViewById(R.id.house_location);
        roomsInput = findViewById(R.id.number_of_rooms);
        priceInput = findViewById(R.id.price);
        descriptionInput = findViewById(R.id.description);
        amenitiesInput = findViewById(R.id.amenities);
        emailInput = findViewById(R.id.email);
        phoneInput = findViewById(R.id.phone_number);
        imagePreview = findViewById(R.id.image_preview);
        uploadImageButton = findViewById(R.id.upload_image_button);
        submitButton = findViewById(R.id.submit_button);

        // Set click listeners
        uploadImageButton.setOnClickListener(v -> showImagePickerOptions());
        submitButton.setOnClickListener(v -> {
            if (validateInputs()) {
                Toast.makeText(this, "Listing submitted Successfully! Wait for Confirmation", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    private void showImagePickerOptions() {
        CharSequence[] options = {"Take Photo", "Choose from Gallery", "Cancel"};

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Select an Option");
        builder.setItems(options, (dialog, item) -> {
            if (options[item].equals("Take Photo")) {
                // Check camera permissions
                if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                    launchCamera();
                } else {
                    ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.CAMERA}, REQUEST_CAMERA_PERMISSION);
                }
            } else if (options[item].equals("Choose from Gallery")) {
                Intent pickPhoto = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(pickPhoto, REQUEST_IMAGE_PICK);
            } else {
                dialog.dismiss();
            }
        });
        builder.show();
    }

    private void launchCamera() {
        Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePicture.resolveActivity(getPackageManager()) != null) {
            try {
                File photoFile = createImageFile();
                if (photoFile != null) {
                    imageUri = FileProvider.getUriForFile(this, "com.example.rect_connect.fileprovider", photoFile);
                    takePicture.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                    startActivityForResult(takePicture, REQUEST_IMAGE_CAPTURE);
                }
            } catch (IOException ex) {
                Toast.makeText(this, "Error occurred while creating file", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "No camera application found", Toast.LENGTH_SHORT).show();
        }
    }

    private File createImageFile() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(null);
        File imageFile = File.createTempFile(imageFileName, ".jpg", storageDir);
        currentPhotoPath = imageFile.getAbsolutePath();
        return imageFile;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_IMAGE_CAPTURE) {
                // Display the captured photo
                imagePreview.setImageURI(imageUri);
            } else if (requestCode == REQUEST_IMAGE_PICK && data != null) {
                imageUri = data.getData();
                imagePreview.setImageURI(imageUri);
            }
        }
    }

    private boolean validateInputs() {
        if (locationInput.getText().toString().trim().isEmpty()) {
            locationInput.setError("Location is required");
            return false;
        }
        if (roomsInput.getText().toString().trim().isEmpty()) {
            roomsInput.setError("Number of rooms is required");
            return false;
        }
        if (priceInput.getText().toString().trim().isEmpty()) {
            priceInput.setError("Price is required");
            return false;
        }
        if (descriptionInput.getText().toString().trim().isEmpty()) {
            descriptionInput.setError("Description is required");
            return false;
        }
        if (amenitiesInput.getText().toString().trim().isEmpty()) {
            amenitiesInput.setError("Amenities are required");
            return false;
        }
        if (emailInput.getText().toString().trim().isEmpty()) {
            emailInput.setError("Email is required");
            return false;
        }
        if (phoneInput.getText().toString().trim().isEmpty()) {
            phoneInput.setError("Phone number is required");
            return false;
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CAMERA_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                launchCamera();
            } else {
                Toast.makeText(this, "Camera permission is required to take photos", Toast.LENGTH_SHORT).show();
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish(); // Close activity when back button is pressed
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
