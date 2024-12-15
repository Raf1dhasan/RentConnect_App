package com.rafid.rect_connect;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class ContactUsActivity extends AppCompatActivity {

    private TextInputLayout emailField;
    private TextInputEditText emailInput;
    private TextInputEditText messageInput;
    private MaterialButton sendButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);

        // Enable the back button and set the title in the ActionBar
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Contact Us"); // Set the ActionBar title to "Contact Us"
        }

        // Initialize views
        emailField = findViewById(R.id.email_field);
        emailInput = findViewById(R.id.email_input);
        messageInput = findViewById(R.id.message_input);
        sendButton = findViewById(R.id.send_button);

        // Set click listener on Send button
        sendButton.setOnClickListener(v -> {
            if (validateEmail()) {
                sendEmail();
            }
        });
    }

    private boolean validateEmail() {
        String emailText = emailInput.getText().toString().trim();

        if (emailText.isEmpty()) {
            emailField.setError("Email is required");
            return false;
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(emailText).matches()) {
            emailField.setError("Please enter a valid email address");
            return false;
        } else {
            emailField.setError(null);
            return true;
        }
    }

    private void sendEmail() {
        String emailAddress = emailInput.getText().toString().trim();
        String message = messageInput.getText().toString().trim();

        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setType("message/rfc822");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{"rafidhasan202@gmail.com"});
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Contact Us Message");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "From: " + emailAddress + "\n\nMessage:\n" + message);

        try {
            startActivity(Intent.createChooser(emailIntent, "Choose an email client"));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(ContactUsActivity.this, "No email app available", Toast.LENGTH_SHORT).show();
        }
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
