package com.rafid.rect_connect;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HouseListActivity extends BaseActivity {

    private RecyclerView recyclerView;
    private HouseAdapter adapter;
    private List<House> houseList;
    private List<House> filteredList;
    private SearchView searchProperty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_house_list);

        // Set up ActionBar title
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(false); // No back button
            getSupportActionBar().setDisplayShowHomeEnabled(false);
            getSupportActionBar().setTitle("     Rent-Connect."); // Set the ActionBar title
        }

        // Initialize UI components
        recyclerView = findViewById(R.id.recycler_view);
        searchProperty = findViewById(R.id.search_property);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        // Handle Bottom Navigation
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            if (item.getItemId() == R.id.navigation_home) {
                startActivity(new Intent(HouseListActivity.this, AddHouseActivity2.class));
                return true;
            }
            return false;
        });

        // Hide Bottom Navigation for non-landlords
        String userType = getIntent().getStringExtra("userType");
        if (userType == null || !"Landlord".equals(userType)) {
            bottomNavigationView.setVisibility(View.GONE);
        }

        // Populate sample list of houses with all necessary details, including latitude and longitude
        houseList = new ArrayList<>();
        houseList.add(new House("Cozy Townhouse on Summit Drive", "1 bedroom, shared bath and kitchen", R.drawable.img_house1,
                "$700", 2, 1, false, Arrays.asList(R.drawable.img_house1_1, R.drawable.img_house1_2, R.drawable.img_house1_3),
                "Spacious townhouse with a shared kitchen and bath", "In-house laundry, Close to bus stop, Parking available, Large patio",
                50.6558636, -120.3451228)); // Latitude and Longitude

        houseList.add(new House("Downtown Studio Apartment", "Studio Apartment with bath and kitchen", R.drawable.img_house2,
                "$1500", 4, 3, true, Arrays.asList(R.drawable.img_house2_1, R.drawable.img_house2_2, R.drawable.img_house2_3),
                "Studio apartment in the heart of the city with modern amenities", "Pool, Gym, Nearby park",
                50.6754583, -120.3435004));

        houseList.add(new House("Scenic Bungalow on Aberdeen", "1 bedroom, shared bath and kitchen", R.drawable.img_house3,
                "$650", 3, 2, false, Arrays.asList(R.drawable.img_house3_1, R.drawable.img_house3_2, R.drawable.img_house3_3),
                "City apartment with great views", "24/7 security, In-building laundry",
                50.6377642, -120.3695201));

        houseList.add(new House("Sahali Apartment", "1 master bedroom available for couples", R.drawable.img_house4,
                "$800", 2, 2, true, Arrays.asList(R.drawable.img_house4_1, R.drawable.img_house4_2),
                "Cozy cottage with traditional charm", "Garden, Fireplace, Nearby hiking trails",
                50.6499375, -120.3465451));

        // Additional Houses
        houseList.add(new House("Apartment near TRU", "Living room for rent, suitable for students.", R.drawable.img_house5,
                "$400", 3, 3, true, Arrays.asList(R.drawable.img_house5_1),
                "Enjoy the ocean breeze and sandy beaches", "Private beach access, Pool, BBQ area",
                50.666956, -120.3663891));

        houseList.add(new House("Beautiful house on Northshore", "Full basement for rent", R.drawable.img_house6,
                "$1200", 3, 2, false, Arrays.asList(R.drawable.img_house6_1),
                "Escape the hustle and bustle", "Panoramic mountain views, Hiking trails, Fireplace",
                50.699949, -120.3718088));

        houseList.add(new House("Lakeside Retreat near Westsyde pool", "Luxury penthouse for rent", R.drawable.img_house7,
                "$3000", 4, 4, true, Arrays.asList(R.drawable.img_house7_1, R.drawable.img_house7_2),
                "Top-floor apartment with stunning city views", "Private elevator, Rooftop garden, Gym",
                50.7661531, -120.3504542));

        houseList.add(new House("Modern house on Pineview", "4 rooms, 2 baths with large garden", R.drawable.img_house8,
                "$2900", 2, 2, true, Arrays.asList(R.drawable.img_house8_1, R.drawable.img_house8_2, R.drawable.img_house8_3),
                "Perfect for peaceful country living", "Large garden, Barn, Nearby trails",
                50.6539829, -120.3976329));

        // Log to verify data population
        Log.d("HouseListActivity", "Total houses in list: " + houseList.size());

        // Initialize filtered list with all houses initially
        filteredList = new ArrayList<>(houseList);

        // Set up RecyclerView and adapter
        setupRecyclerView();

        // Set up SearchView filtering
        setupSearchView();
    }

    private void setupRecyclerView() {
        adapter = new HouseAdapter(this, filteredList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    private void setupSearchView() {
        searchProperty.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                filterHouses(query);
                if (filteredList.isEmpty()) {
                    Toast.makeText(HouseListActivity.this, "No matching properties found", Toast.LENGTH_SHORT).show();
                }
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterHouses(newText);
                return true;
            }
        });
    }

    private void filterHouses(String query) {
        filteredList.clear();

        if (query == null || query.isEmpty()) {
            filteredList.addAll(houseList);
        } else {
            String lowerCaseQuery = query.toLowerCase();
            for (House house : houseList) {
                if (house.getName().toLowerCase().contains(lowerCaseQuery) ||
                        house.getDescription().toLowerCase().contains(lowerCaseQuery) ||
                        (house.getAmenities() != null && house.getAmenities().toLowerCase().contains(lowerCaseQuery))) {
                    filteredList.add(house);
                }
            }
        }

        adapter.notifyDataSetChanged();
        Log.d("HouseListActivity", "Filtered houses count: " + filteredList.size());
    }
}
