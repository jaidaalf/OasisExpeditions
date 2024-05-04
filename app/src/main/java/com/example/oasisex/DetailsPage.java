package com.example.oasisex;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.oasisex.model.TripPackage;

public class DetailsPage extends AppCompatActivity {
    private TextView titleTxt, locationTxt, descriptionTxt, scoreTxt, durationTxt, startDateTxt, endDateTxt, priceTxt;
    private ImageView picImg, backBtn;
    private TripPackage trip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detailspage); // Change this to your actual layout file if different

        initView();
        // Getting the trip package ID from the intent
        int packageId = getIntent().getIntExtra("packageId", -1);
        if (packageId != -1) {
            trip =(TripPackage)getIntent().getSerializableExtra("trip");
            setVariables(packageId);
        } else {
            Toast.makeText(this, "No trip package ID provided.", Toast.LENGTH_LONG).show();
        }
    }

    private void initView() {
        titleTxt = findViewById(R.id.titleTxt);
        locationTxt = findViewById(R.id.locationTxt);
        descriptionTxt = findViewById(R.id.descriptionTxt);
        scoreTxt = findViewById(R.id.scoreTxt);
        durationTxt = findViewById(R.id.durationTxt);
        startDateTxt = findViewById(R.id.goDateTxt);
        endDateTxt = findViewById(R.id.backTimeTxt);
        priceTxt = findViewById(R.id.priceTxt);
        picImg = findViewById(R.id.picImg);
        backBtn = findViewById(R.id.backBtn);

        // Setting up the back button to close the activity
        backBtn.setOnClickListener(v -> finish());
    }

    private void setVariables(int packageId) {
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        TripPackage tripPackage = (TripPackage) dbHelper.getTripPackageDetails(packageId);
        if (tripPackage != null) {
            titleTxt.setText(tripPackage.getTripName());
            locationTxt.setText(tripPackage.getDestination()); // Assuming the description also represents the location
            descriptionTxt.setText(tripPackage.getDescription());
            scoreTxt.setText(String.format("%.1f", tripPackage.getRating()));
            durationTxt.setText(tripPackage.getDuration() + " nights");
            startDateTxt.setText("Start: " + tripPackage.getStartDate());
            endDateTxt.setText("End: " + tripPackage.getEndDate());
            priceTxt.setText(String.format("%.2f SAR", tripPackage.getPrice()));
            picImg.setImageResource(tripPackage.getImgSrc());

            // Optionally load an image if you have an image URL in TripPackageDomain
            // Glide.with(this).load(tripPackage.getImageUrl()).into(picImg);
        } else {
            Toast.makeText(this, "Trip data is not available", Toast.LENGTH_SHORT).show();
        }
    }

    public void goTobooking(View view) {
        if(trip != null) {
            Intent i = new Intent(getApplicationContext(), booking.class);
            i.putExtra("trip", trip);
            startActivity(i);
        }else{
            Toast.makeText(this, "Trip data is not available", Toast.LENGTH_SHORT).show();
        }
    }
}
