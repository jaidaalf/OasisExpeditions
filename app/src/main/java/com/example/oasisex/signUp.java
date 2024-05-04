package com.example.oasisex;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Intent;


public class signUp extends AppCompatActivity {

    EditText etName, etEmail, etPassword, etAge;
    Button btnSignUp;
    DatabaseHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sighup);

        etName = findViewById(R.id.etName);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        etAge = findViewById(R.id.etAge);
        btnSignUp = findViewById(R.id.btnSignUp);
        DB = new DatabaseHelper(this);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = etName.getText().toString();
                String email = etEmail.getText().toString();
                String password = etPassword.getText().toString();
                String ageStr = etAge.getText().toString();

                if(name.isEmpty() || email.isEmpty() || password.isEmpty() || ageStr.isEmpty()) {
                    Toast.makeText(signUp.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                } else {
                    int age = Integer.parseInt(ageStr); // Convert age to integer
                    long result = DB.addUser(email, name, password, age);
                    if(result > 0) {
                        Toast.makeText(signUp.this, "Registered successfully", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(signUp.this, mainPage.class);
                        startActivity(intent);
                        finish(); // Prevents going back to the signUp screen with the back button
                    } else {
                        Toast.makeText(signUp.this, "Registration failed or user already exists", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
