package com.example.oasisex;

import android.os.Bundle;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.app.AppCompatActivity;

import com.example.oasisex.model.TripPackage;

public class mainPage extends AppCompatActivity {

    private Button signInButton;
    private TextView registerNowText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainpage); // Correct the layout name if necessary

        signInButton = findViewById(R.id.button2);
        registerNowText = findViewById(R.id.textView4);

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Start LoginActivity
                Intent intent = new Intent(mainPage.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        registerNowText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Start signUp Activity
                Intent intent = new Intent(mainPage.this, signUp.class);
                startActivity(intent);
            }
        });

        DatabaseHelper dbHelper = new DatabaseHelper(this);
        TripPackage tripPackage = (TripPackage) dbHelper.getTripPackageDetails(1);
        if (tripPackage == null) {
            dbHelper.addTripPackage("Abha", "$1500/3 Days", "Embark on a 3-night journey through time to the oldest Najdi villages and explore the unique Al-Turaif in Diriyah for a truly unique experience. Your trip includes a tour of Riyadh, where you can enjoy visiting some of the best tourist areas in the capital, including the National Museum, Murabba Palace, and Masmak Fortress, for a comprehensive experience. You will also be able to visit the King Abdullah Financial Center and the amazing facilities."
                    , 300, 3,
                    "25-4-2024","28-4-2025",
                    "","",4.5,
                    "Discover the spirit of ancient Arabia.", R.drawable.abha_rc);


            dbHelper.addTripPackage("Jedahh", "$2000/3 Days", "Embark on a 3-night journey through time to the oldest Najdi villages and explore the unique Al-Turaif in Diriyah for a truly unique experience. Your trip includes a tour of Riyadh, where you can enjoy visiting some of the best tourist areas in the capital, including the National Museum, Murabba Palace, and Masmak Fortress, for a comprehensive experience. You will also be able to visit the King Abdullah Financial Center and the amazing facilities."
                    , 450, 3,
                    "1-5-2024","3-5-2025",
                    "","",4.8,
                    "Sun And Sea.", R.drawable.jed_rc);

            dbHelper.addTripPackage("Riyadh, Diriyah", "$2500/3 Days", "Embark on a 3-night journey through time to the oldest Najdi villages and explore the unique Al-Turaif in Diriyah for a truly unique experience. Your trip includes a tour of Riyadh, where you can enjoy visiting some of the best tourist areas in the capital, including the National Museum, Murabba Palace, and Masmak Fortress, for a comprehensive experience. You will also be able to visit the King Abdullah Financial Center and the amazing facilities."
                    , 2500, 3,
                    "1-5-2024","3-5-2025",
                    "","",5,
                    "Discover the spirit of ancient Arabia", R.drawable.riyadh_rc);


            dbHelper.addTripPackage("Dammam", "$900/2 Days", "Embark on a 3-night journey through time to the oldest Najdi villages and explore the unique Al-Turaif in Diriyah for a truly unique experience. Your trip includes a tour of Riyadh, where you can enjoy visiting some of the best tourist areas in the capital, including the National Museum, Murabba Palace, and Masmak Fortress, for a comprehensive experience. You will also be able to visit the King Abdullah Financial Center and the amazing facilities."
                    , 900, 2,
                    "1-5-2024","3-5-2025",
                    "","",4.9,
                    "Discover the spirit of ancient Arabia", R.drawable.bk);

            dbHelper.addTripPackage("Makkah", "$2000/2 Days", "Embark on a 3-night journey through time to the oldest Najdi villages and explore the unique Al-Turaif in Diriyah for a truly unique experience. Your trip includes a tour of Riyadh, where you can enjoy visiting some of the best tourist areas in the capital, including the National Museum, Murabba Palace, and Masmak Fortress, for a comprehensive experience. You will also be able to visit the King Abdullah Financial Center and the amazing facilities."
                    , 2000, 2,
                    "3-5-2024","6-5-2025",
                    "","",5,
                    "Discover the spirit of ancient Arabia", R.drawable.makkah);

        }
    }
}
