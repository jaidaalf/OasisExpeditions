package com.example.oasisex.model;

import java.io.Serializable;

public class AllTrip implements Serializable {
    int id;
    String PlaceName;
    String Country;
    String Price;
    Integer ImageUrl;
    public AllTrip(int id , String PlaceName , String Country, String Price , Integer ImageUrl){
        this.id = id;
        this.PlaceName=PlaceName;
        this.Country=Country;
        this.Price=Price;
        this.ImageUrl=ImageUrl;

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

    public void setImageUrl(Integer imageUrl) {
        ImageUrl = imageUrl;
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

    public int getId() {
        return id;
    }
}