package com.example.oasisex.model;

public class MyTrip {
    String PlaceName;
    String Country;
    String Price;
    Integer ImageUrl;
    int id;
    int trip_id;

    public MyTrip(int id, String placeName, String country, String price, Integer imageUrl, int trip_id) {
        PlaceName = placeName;
        Country = country;
        Price = price;
        ImageUrl = imageUrl;
        this.id= id;
        this.trip_id= trip_id;
    }

    public int getTrip_id() {
        return trip_id;
    }

    public int getId() {
        return id;
    }

    public String getPlaceName() {
        return PlaceName;
    }

    public String getCountry() {
        return Country;
    }

    public String getPrice() {
        return Price;
    }

    public Integer getImageUrl() {
        return ImageUrl;
    }

    public void setPlaceName(String placeName) {
        PlaceName = placeName;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public void setImageUrl(Integer imageUrl) {
        ImageUrl = imageUrl;
    }
}