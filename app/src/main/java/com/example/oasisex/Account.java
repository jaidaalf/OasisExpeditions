package com.example.oasisex;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;

public class Account extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account);

        // Button to log out
        Button btnLogOut = findViewById(R.id.button6);
        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Redirect to the mainPage (LoginActivity if it's used for login)
                Intent intent = new Intent(Account.this, mainPage.class);
                startActivity(intent);
                finish();  // Close the Account activity
            }
        });

        // Button for the Help Centre
        Button btnHelpCentre = findViewById(R.id.btnHelpCentre);
        btnHelpCentre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Redirect to HelpCentre Activity
                Intent intent = new Intent(Account.this, HelpCentre.class);
                startActivity(intent);
            }
        });
    }
}

