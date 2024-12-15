package com.rafid.rect_connect;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;



import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class PreLoginActivity extends AppCompatActivity {
    private Button loginButtonPre, signupButtonPre;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre_login);

        // Initialize Firebase Auth
        auth = FirebaseAuth.getInstance();

        // Check if user is already logged in
        FirebaseUser currentUser = auth.getCurrentUser();
        if (currentUser != null) {
            // User is logged in, redirect to HouseListActivity
            Intent houseListIntent = new Intent(PreLoginActivity.this, HouseListActivity.class);
            startActivity(houseListIntent);
            finish(); // Close the PreLoginActivity so the user can't go back to it
        }

        // Initialize buttons
        loginButtonPre = findViewById(R.id.login_button_pre);
        signupButtonPre = findViewById(R.id.signup_button_pre);

        // Set onClickListeners for the buttons
        loginButtonPre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loginIntent = new Intent(PreLoginActivity.this, LoginActivity.class);
                startActivity(loginIntent);
            }
        });

        signupButtonPre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signupIntent = new Intent(PreLoginActivity.this, SignupActivity.class);
                startActivity(signupIntent);
            }
        });
    }
}
