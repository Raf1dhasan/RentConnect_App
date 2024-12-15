package com.rafid.rect_connect;

import android.os.Bundle;

public class AboutUsActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        // Set the title in the ActionBar
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Rent-Connect.");
        }
    }
}
