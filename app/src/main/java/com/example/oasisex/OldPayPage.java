package com.example.oasisex;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.oasisex.adapter.CartAdapter;
import com.example.oasisex.databinding.PaypageBinding;

public class OldPayPage extends AppCompatActivity {
    private PaypageBinding binding;
    private RecyclerView.Adapter adapter;
    private ManagementCart managementCart;
    private double tax;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = PaypageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        managementCart = new ManagementCart(this);

        setVariable();
        calculateCart();
        initList();
    }

    private void initList() {
        if (managementCart.getListCart().isEmpty()) {
            binding.emptyTxt.setVisibility(View.VISIBLE);
            binding.scrollviewCart.setVisibility(View.GONE);
        } else {
            binding.emptyTxt.setVisibility(View.GONE);
            binding.scrollviewCart.setVisibility(View.VISIBLE);
        }

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        //binding.cardView.setLayoutManager(linearLayoutManager);
        //adapter = new CartAdapter(managementCart.getListCart(), this);
        //binding.cardView.setAdapter(adapter);
    }

    private void calculateCart() {
        double percentTax = 0.02; // 2% tax
        double delivery = 10; // 10 Dollar

        double subtotal = managementCart.getTotalFee();
        tax = Math.round(subtotal * percentTax * 100.0) / 100;

        double total = Math.round((subtotal + tax + delivery) * 100) / 100;
        double itemTotal = Math.round(subtotal * 100) / 100;

        binding.totalFeeTxt.setText("$" + itemTotal);
        binding.taxTxt.setText("$" + tax);
        //binding.deliveryTxt.setText("$" + delivery);
        binding.totalTxt.setText("$" + total);
    }

    private void setVariable() {
        binding.backBtn.setOnClickListener(v -> finish());
    }



    public void confirmDeleteTripPackage(int tripPackageId) {
        new AlertDialog.Builder(this)
                .setTitle("Confirm Deletion")
                .setMessage("Are you sure you want to delete this trip package?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                       /* if (DatabaseHelper.deleteUserTripPackage(OldPayPage.this, tripPackageId)) {
                            Toast.makeText(OldPayPage.this, "Trip Package deleted successfully", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(OldPayPage.this, "Error deleting trip package", Toast.LENGTH_SHORT).show();
                        }*/
                    }
                })
                .setNegativeButton(android.R.string.no, null)
                .show();
    }



    public void onEditClick(View view) {
        int tripId = getTripId(); // Implement this method to retrieve the trip ID
        editTrip(tripId);
    }

    private void editTrip(int tripId) {
        Intent intent = new Intent(OldPayPage.this, EditPage.class);
        intent.putExtra("TRIP_ID", tripId);
        startActivity(intent);
    }

    private int getTripId() {

        return 0; // Placeholder
    }
}
