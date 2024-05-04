package com.example.oasisex;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.oasisex.model.AllTrip;
import com.example.oasisex.model.TripPackage;

public class PayPage extends AppCompatActivity {

    private TextView totalFeeTxt;
    private TextView taxTxt;
    private TextView totalTxt;
    private TextView feeEachItem;
    private ImageView pic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.paypage);

        totalFeeTxt = (TextView)findViewById(R.id.totalFeeTxt);
        taxTxt = (TextView)findViewById(R.id.taxTxt);
        totalTxt = (TextView)findViewById(R.id.totalTxt);
        feeEachItem = (TextView)findViewById(R.id.feeEachItem);
        pic = (ImageView)findViewById(R.id.pic);

        TextView editLbl = (TextView)findViewById(R.id.editLbl);
        TextView cancelLbl = (TextView)findViewById(R.id.cancelLbl);

        ImageView backBtn = (ImageView)findViewById(R.id.backBtn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        editLbl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        cancelLbl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(PayPage.this, mainPage.class);
                startActivity(i);
            }
        });


        Intent myIntent = getIntent();
        TripPackage trip = (TripPackage) myIntent.getSerializableExtra("trip");
        SessionManager session = new SessionManager(getApplicationContext());
        String no_of_ppl = myIntent.getStringExtra("no_of_ppl");
        //String email = myIntent.getStringExtra("email");
        String note = myIntent.getStringExtra("note");

        Double price = trip.getPrice();
        totalFeeTxt.setText(""+price);
        feeEachItem.setText(""+price+" SAR");
        taxTxt.setText(""+(price*0.1));
        totalTxt.setText(""+(price + price*0.1));

        pic.setImageResource(trip.getImgSrc());

        Button payBtn = (Button)findViewById(R.id.payBtn);
        payBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(PayPage.this)
                        .setTitle("Confirm of Paying")
                        .setMessage("Are you sure you want to pay?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                DatabaseHelper db = new DatabaseHelper(getApplicationContext());
                                if(db.addUserTripPackage(session.getEmail(), trip.getPakckage_id(),Integer.parseInt(no_of_ppl), note)){
                                    Toast.makeText(PayPage.this, "Trip booked successfully", Toast.LENGTH_LONG).show();
                                    Intent i = new Intent(PayPage.this, Trips.class);
                                    startActivity(i);
                                }else{
                                    Toast.makeText(PayPage.this, "Trip not booked", Toast.LENGTH_LONG).show();
                                }
                            }
                        })
                        .setNegativeButton(android.R.string.no, null)
                        .show();


            }
        });
    }
}