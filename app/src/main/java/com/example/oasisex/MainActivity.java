package com.example.oasisex;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.oasisex.adapter.MyTripAdapter;
import com.example.oasisex.model.MyTrip;

import java.util.ArrayList;
import java.util.List;


import android.text.Editable;
import android.text.TextWatcher;

import android.widget.EditText;
import android.content.Intent;
import android.view.View;

//import com.example.oasisex.database.DatabaseHelper;


public class MainActivity extends AppCompatActivity {
    RecyclerView  myTripRecycler;
    MyTripAdapter myTripAdapter;
    private List<MyTrip> myTripList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SessionManager session = new SessionManager(getApplicationContext());
        /*List<MyTrip> myTripList =new ArrayList<>();
        myTripList.add(new MyTrip("Discover the spirit of ancient Arabia","Riyadh,Diriyah","$1500/3 Days",R.drawable.riyadh_rc));
        setMyTripRecycler(myTripList);*/

        // Find the EditText for search
        EditText searchEditText = findViewById(R.id.editTextText);
        // Create a DatabaseHelper instance
        DatabaseHelper dbHelper = new DatabaseHelper(this);


        ImageView homeImgView = findViewById(R.id.homeImgView);
        homeImgView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Trips.class);
                startActivity(intent);
            }
        });




        // User account ImageView
        ImageView imageViewUser = findViewById(R.id.imageViewUser);
        imageViewUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Account.class);
                startActivity(intent);
            }
        });

        ImageView imageViewCart = findViewById(R.id.cartImbView);
        imageViewCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PayPage.class);  // Assuming ManagementCart is an activity
                startActivity(intent);
            }
        });




        // Retrieve reservations for the current user
        myTripList = dbHelper.getUserReservations(session.getEmail());

        // Set up the RecyclerView with the retrieved reservations
        setMyTripRecycler(myTripList);

        // Set up the search functionality
        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}


            @Override
            public void afterTextChanged(Editable editable) {
                String query = editable.toString().toLowerCase().trim();
                if(query.equals("")){
                    myTripList = dbHelper.getUserReservations(session.getEmail());
                    myTripAdapter.updateList(myTripList);
                }else {
                    Log.d("MainActivity", "Search query: " + query); // Add logging statement
                    List<MyTrip> filteredList = filter(myTripList, query);
                    myTripAdapter.updateList(filteredList);
                }
            }

        });

    }


    private void setMyTripRecycler(List<MyTrip> myTripList){
        myTripRecycler=findViewById(R.id.res_trip_recycler);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        myTripRecycler.setLayoutManager(layoutManager);
        myTripAdapter=new MyTripAdapter(this,myTripList);
        myTripRecycler.setAdapter(myTripAdapter);
    }

    private List<MyTrip> filter(List<MyTrip> myList, String query) {
        List<MyTrip> filteredList = new ArrayList<>();
        for (MyTrip trip : myList) {
            if (trip.getPlaceName().toLowerCase().contains(query) || trip.getCountry().toLowerCase().contains(query)) {
                filteredList.add(trip);
            }
        }
        return filteredList;
    }

    public void goToTripsPage(View view) {
        Intent intent = new Intent(this, Trips.class);
        startActivity(intent);
    }

    public void goToAccountPage(View view) {
        Intent intent = new Intent(this, Account.class);
        startActivity(intent);
    }


    
}