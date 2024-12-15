package com.rafid.rect_connect;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class RentalFormActivity extends AppCompatActivity {

    private TextInputEditText fullNameInput, dateOfBirthInput, emailInput, phoneNumberInput;
    private TextInputEditText currentLandlordNameInput, landlordPhoneInput, numberOfOccupantsInput;
    private RadioGroup petsRadioGroup;
    private MaterialButton applyNowButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rental_form);

        // Enable the back button in the ActionBar
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Rental Application Form");
        }

        // Initialize fields
        fullNameInput = findViewById(R.id.full_name);
        dateOfBirthInput = findViewById(R.id.date_of_birth);
        emailInput = findViewById(R.id.email);
        phoneNumberInput = findViewById(R.id.phone_number);
        currentLandlordNameInput = findViewById(R.id.current_landlord_name);
        landlordPhoneInput = findViewById(R.id.landlord_phone);
        numberOfOccupantsInput = findViewById(R.id.number_of_occupants);
        petsRadioGroup = findViewById(R.id.pets_radio_group);
        applyNowButton = findViewById(R.id.apply_now_button);

        // Set click listener on Apply Now button
        applyNowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateForm()) {
                    sendApplicationEmail();
                }
            }
        });
    }

    /**
     * Validates the form fields to ensure they are not empty.
     *
     * @return true if all fields are valid, false otherwise.
     */
    private boolean validateForm() {
        if (TextUtils.isEmpty(fullNameInput.getText().toString())) {
            fullNameInput.setError("Full Name is required");
            return false;
        }
        if (TextUtils.isEmpty(dateOfBirthInput.getText().toString())) {
            dateOfBirthInput.setError("Date of Birth is required");
            return false;
        }
        if (TextUtils.isEmpty(emailInput.getText().toString()) ||
                !android.util.Patterns.EMAIL_ADDRESS.matcher(emailInput.getText().toString()).matches()) {
            emailInput.setError("Valid Email is required");
            return false;
        }
        if (TextUtils.isEmpty(phoneNumberInput.getText().toString())) {
            phoneNumberInput.setError("Phone Number is required");
            return false;
        }
        if (TextUtils.isEmpty(currentLandlordNameInput.getText().toString())) {
            currentLandlordNameInput.setError("Landlord's Name is required");
            return false;
        }
        if (TextUtils.isEmpty(landlordPhoneInput.getText().toString())) {
            landlordPhoneInput.setError("Landlord's Phone Number is required");
            return false;
        }
        if (TextUtils.isEmpty(numberOfOccupantsInput.getText().toString())) {
            numberOfOccupantsInput.setError("Number of Occupants is required");
            return false;
        }
        if (petsRadioGroup.getCheckedRadioButtonId() == -1) {
            Toast.makeText(this, "Please select if you have pets", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    /**
     * Gathers form data and sends it via email.
     */
    private void sendApplicationEmail() {
        String fullName = fullNameInput.getText().toString();
        String dateOfBirth = dateOfBirthInput.getText().toString();
        String email = emailInput.getText().toString();
        String phoneNumber = phoneNumberInput.getText().toString();
        String currentLandlordName = currentLandlordNameInput.getText().toString();
        String landlordPhone = landlordPhoneInput.getText().toString();
        String numberOfOccupants = numberOfOccupantsInput.getText().toString();
        String pets = ((RadioButton) findViewById(petsRadioGroup.getCheckedRadioButtonId())).getText().toString();

        String emailBody = "Rental Application Details:\n\n" +
                "Full Name: " + fullName + "\n" +
                "Date of Birth: " + dateOfBirth + "\n" +
                "Email: " + email + "\n" +
                "Phone Number: " + phoneNumber + "\n\n" +
                "Rental History:\n" +
                "Current Landlord's Name: " + currentLandlordName + "\n" +
                "Landlord's Phone Number: " + landlordPhone + "\n\n" +
                "Additional Information:\n" +
                "Pets: " + pets + "\n" +
                "Number of Occupants: " + numberOfOccupants + "\n";

        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setType("message/rfc822"); // MIME type for email clients
        emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{"rafidhasan202@gmail.com"});
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Rental Application Form");
        emailIntent.putExtra(Intent.EXTRA_TEXT, emailBody);

        try {
            startActivity(Intent.createChooser(emailIntent, "Send email with..."));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(RentalFormActivity.this, "No email app available", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish(); // Close the activity when the back button is pressed
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
