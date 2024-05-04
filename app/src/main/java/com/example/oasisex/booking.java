package com.example.oasisex;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.example.oasisex.model.AllTrip;
import com.example.oasisex.model.TripPackage;


public class booking extends AppCompatActivity {
    private EditText no_of_ppl , email, note;
    private Button bookBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.booking);

        SessionManager session = new SessionManager(getApplicationContext());
        TextView tripNameLBl = (TextView) findViewById(R.id.tripNameLBl);
        no_of_ppl = (EditText) findViewById(R.id.no_of_ppl);
        email = (EditText) findViewById(R.id.email);
        note = (EditText) findViewById(R.id.note);
        bookBtn = (Button) findViewById(R.id.bookBtn);
        RelativeLayout rltv_layout = (RelativeLayout) findViewById(R.id.rltv_layout);
        ImageView backImageView = (ImageView) findViewById(R.id.backImageView);
        backImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Intent myIntent = getIntent();
        TripPackage trip = (TripPackage) myIntent.getSerializableExtra("trip");
        if(trip != null) {
            email.setText(session.getEmail());
            email.setEnabled(false);
            tripNameLBl.setText(trip.getTripName());
            rltv_layout.setBackgroundResource(trip.getImgSrc());
            bookBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String _no_of_ppl = no_of_ppl.getText().toString();
                    String _note = note.getText().toString();
                    String _email = email.getText().toString();
                    if (!_no_of_ppl.equals("") && !_note.equals("") && !_email.equals("")) {
                        Intent i = new Intent(booking.this, PayPage.class);
                        i.putExtra("no_of_ppl", no_of_ppl.getText().toString());
                        i.putExtra("note", note.getText().toString());
                        i.putExtra("trip", trip);
                        startActivity(i);
                    } else {
                        Toast.makeText(booking.this, "All fields must be set", Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
    }
}
