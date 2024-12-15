package com.rafid.rect_connect;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class BaseActivity extends AppCompatActivity {
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialize FirebaseAuth instance
        auth = FirebaseAuth.getInstance();

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            finish();
            return true;
        } else if (id == R.id.action_contact_us) {
            // Handle contact us action
            Intent contactUsIntent = new Intent(this, ContactUsActivity.class);
            startActivity(contactUsIntent);
            return true;
        } else if (id == R.id.action_about_us) {
            // Handle about us action
            Intent aboutUsIntent = new Intent(this, AboutUsActivity.class);
            startActivity(aboutUsIntent);
            return true;
        } else if (id == R.id.action_add_house) {
            Intent addHouseIntent = new Intent(this, AddHouseActivity2.class);
            startActivity(addHouseIntent);
            return true;
        } else if (id == R.id.action_logout) {
            // Handle logout action with Firebase sign-out
            auth.signOut(); // Sign out from Firebase

            Intent logoutIntent = new Intent(this, PreLoginActivity.class);
            logoutIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(logoutIntent);
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }
}
