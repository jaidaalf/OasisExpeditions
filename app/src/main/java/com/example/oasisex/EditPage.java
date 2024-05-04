package com.example.oasisex;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.oasisex.model.MyTrip;
import com.example.oasisex.model.TripPackage;


public class EditPage extends AppCompatActivity {



    private EditText editTextNumPeople, editTextNotes;
    private Button btnEdit;
    private DatabaseHelper db;
    private String userEmail; // This should be set from a logged-in user session or passed extra
    private int book_id; // This should be dynamically assigned
    private TextView tripNameLbl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editpage); // Replace with your actual layout if different

        db = new DatabaseHelper(this);
        SessionManager session = new SessionManager( this);
        editTextNumPeople = findViewById(R.id.editTextNumPeople); // Ensure ID is correct in your XML
        editTextNotes = findViewById(R.id.editTextNotes); // Ensure ID is correct in your XML
        btnEdit = findViewById(R.id.btnEdit); // Ensure this ID is correct in your XML
        tripNameLbl = (TextView)findViewById(R.id.tripNameLbl);

        RelativeLayout rltv_layout = (RelativeLayout) findViewById(R.id.rltv_layout);
        ImageView backImageView = (ImageView) findViewById(R.id.backImageView);
        backImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateTripDetails();
            }
        });


        Intent myIntent = getIntent();
        TripPackage trip =   (TripPackage) myIntent.getSerializableExtra("trip");
         book_id =   myIntent.getIntExtra("book_id" , 0);

        if(trip != null){
            tripNameLbl.setText(trip.getTripName());
            rltv_layout.setBackgroundResource(trip.getImgSrc());

            MyTrip myTrip =  db.getReservation(book_id);
            editTextNumPeople.setText(myTrip.getPrice()); // number of indvid. saved in it.
            editTextNotes.setText(myTrip.getCountry());// Note saved in it
        }
        
    }

    private void updateTripDetails() {
        int numPeople;
        try {
            numPeople = Integer.parseInt(editTextNumPeople.getText().toString());
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Please enter a valid number of people", Toast.LENGTH_SHORT).show();
            return;
        }

        String notes = editTextNotes.getText().toString();

        if (db.updateUserTripPackage( book_id, numPeople, notes)) {
            Toast.makeText(this, "Trip updated successfully!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Failed to update trip.", Toast.LENGTH_SHORT).show();
        }
    }

    public void goToPayPage(View view) {
        Intent intent = new Intent(this,PayPage.class);

        startActivity(intent);
    }
}

