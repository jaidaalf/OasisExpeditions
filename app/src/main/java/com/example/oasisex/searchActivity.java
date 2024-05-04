package com.example.oasisex;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.oasisex.adapter.AllTripAdapter;
import com.example.oasisex.adapter.RecentsAdapter;
import com.example.oasisex.model.TripPackage;

import java.util.ArrayList;
import java.util.List;

;

public class searchActivity extends AppCompatActivity {
    RecyclerView recentRecycler, allTripRecycler;
    RecentsAdapter recentsAdapter;
    AllTripAdapter allTripAdapter;
    private String searchWord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_trips);

        searchWord = getIntent().getStringExtra("searchWord");
        // Setup listeners for ImageView for Account, Cart, and Main Activity navigation
        setupImageViewListeners();

        // Setup the recycler views for recent trips and all trips
        setupRecyclers();
    }

    private void setupImageViewListeners() {
        EditText editTextText = (EditText)findViewById(R.id.editTextText);
        editTextText.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View view, int keyCode, KeyEvent keyevent) {
                //If the keyevent is a key-down event on the "enter" button
                if ((keyevent.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {

                    Intent i = new Intent(searchActivity.this, searchActivity.class);
                    i.putExtra("searcWord", editTextText.getText().toString());
                    startActivity(i);
                    return true;
                }
                return false;
            }
        });
        // User account ImageView
        ImageView imageViewUser = findViewById(R.id.imageViewUser);
        imageViewUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(searchActivity.this, Account.class);
                startActivity(intent);
            }
        });

        ImageView imageViewCart = findViewById(R.id.imageViewCart);
        imageViewCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(searchActivity.this, PayPage.class);  // Assuming ManagementCart is an activity
                startActivity(intent);
            }
        });

        ImageView imageViewPlane = findViewById(R.id.imageViewPlane);
        imageViewPlane.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(searchActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
        ImageView homeImgView = findViewById(R.id.homeImgView);
        homeImgView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(searchActivity.this,Trips.class);
                startActivity(intent);
            }
        });
    }

    private void setupRecyclers() {
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        List<TripPackage> allTripList  = dbHelper.searchTripsPakages(searchWord);
        setAllTripRecycler(allTripList);
    }

    private void setAllTripRecycler(List<TripPackage> allTripList) {
        allTripRecycler = findViewById(R.id.all_trip_recycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        allTripRecycler.setLayoutManager(layoutManager);
        allTripAdapter = new AllTripAdapter(this, allTripList);
        allTripRecycler.setAdapter(allTripAdapter);
    }
}
