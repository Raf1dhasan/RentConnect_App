package com.rafid.rect_connect;

import java.util.List;

public class House {
    private String name;
    private String description;
    private int imageResource;
    private String price;
    private int bedrooms;
    private int bathrooms;
    private boolean nearShoppingComplex;
    private List<Integer> detailImages;
    private String details;
    private String amenities;
    private double latitude;  // New field
    private double longitude; // New field

    // Updated Constructor
    public House(String name, String description, int imageResource, String price, int bedrooms, int bathrooms, boolean nearShoppingComplex, List<Integer> detailImages, String details, String amenities, double latitude, double longitude) {
        this.name = name;
        this.description = description;
        this.imageResource = imageResource;
        this.price = price;
        this.bedrooms = bedrooms;
        this.bathrooms = bathrooms;
        this.nearShoppingComplex = nearShoppingComplex;
        this.detailImages = detailImages;
        this.details = details != null ? details : "Details not available";
        this.amenities = amenities != null ? amenities : "Amenities not available";
        this.latitude = latitude;
        this.longitude = longitude;
    }

    // Getters and Setters for all fields, including new ones

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getImageResource() {
        return imageResource;
    }

    public void setImageResource(int imageResource) {
        this.imageResource = imageResource;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getBedrooms() {
        return bedrooms;
    }

    public void setBedrooms(int bedrooms) {
        this.bedrooms = bedrooms;
    }

    public int getBathrooms() {
        return bathrooms;
    }

    public void setBathrooms(int bathrooms) {
        this.bathrooms = bathrooms;
    }

    public boolean isNearShoppingComplex() {
        return nearShoppingComplex;
    }

    public void setNearShoppingComplex(boolean nearShoppingComplex) {
        this.nearShoppingComplex = nearShoppingComplex;
    }

    public List<Integer> getDetailImages() {
        return detailImages;
    }

    public void setDetailImages(List<Integer> detailImages) {
        this.detailImages = detailImages;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getAmenities() {
        return amenities;
    }

    public void setAmenities(String amenities) {
        this.amenities = amenities;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
