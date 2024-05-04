package com.example.oasisex.model;

import java.io.Serializable;

public class TripPackage implements Serializable {
    private String destination, accommodation, description, startDate, endDate, rallyPoint, activityDescription, tripName;
    private double price, rating;
    private int duration;
    private int  pakckage_id;
    private int  imgSrc;


    // Constructor
    public TripPackage(int pakckage_id, String destination, String accommodation, String description, double price, int duration,
                       String startDate, String endDate, String rallyPoint, String activityDescription, double rating,
                       String tripName, int imgSrc) {
        this.pakckage_id = pakckage_id;
        this.destination = destination;
        this.accommodation = accommodation;
        this.description = description;
        this.price = price;
        this.duration = duration;
        this.startDate = startDate;
        this.endDate = endDate;
        this.rallyPoint = rallyPoint;
        this.activityDescription = activityDescription;
        this.rating = rating;
        this.tripName = tripName;
        this.imgSrc = imgSrc;
    }

    public int getImgSrc() {
        return imgSrc;
    }

    public int getPakckage_id() {
        return pakckage_id;
    }


    // Getter methods
    public String getDestination() {
        return destination;
    }

    public String getAccommodation() {
        return accommodation;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public int getDuration() {
        return duration;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public String getRallyPoint() {
        return rallyPoint;
    }

    public String getActivityDescription() {
        return activityDescription;
    }

    public double getRating() {
        return rating;
    }

    public String getTripName() {
        return tripName;
    }
}
