package com.example.oasisex;

import android.content.Context;
import android.widget.Toast;

import com.example.oasisex.model.TripPackage;

import java.util.ArrayList;

public class ManagementCart {
    private Context context;
    private DatabaseHelper db;

    public ManagementCart(Context context) {
        this.context = context;
        this.db = new DatabaseHelper(context);  // Fixed constructor name
    }

    public void insertTrip(TripPackage trip) {
        ArrayList<TripPackage> listCart = getListCart();
        boolean existAlready = false;
        int index = 0;
        for (int i = 0; i < listCart.size(); i++) {
            if (listCart.get(i).getDestination().equals(trip.getDestination()) && listCart.get(i).getStartDate().equals(trip.getStartDate())) {
                existAlready = true;
                index = i;
                break;
            }
        }
        if (existAlready) {
            // Assuming you might want to add to an existing trip package's quantity or handle similarly
            // This is a placeholder for any logic you want when the trip already exists in the cart
        } else {
            listCart.add(trip);
        }
        db.saveTripPackageList(listCart);  // Assumed method to save list to DB
        Toast.makeText(context, "Added to your Cart", Toast.LENGTH_SHORT).show();
    }

    public ArrayList<TripPackage> getListCart() {
        return db.getTripPackageList();  // Assumed method to fetch list from DB
    }

    public double getTotalFee() {
        ArrayList<TripPackage> listItems = getListCart();
        double fee = 0;
        for (TripPackage item : listItems) {
            fee += (item.getPrice());  // Assuming no need to multiply by quantity as it seems to be a booking scenario
        }
        return fee;
    }

    /*public void minusNumberItem(ArrayList<TripPackageDomain> listItems, int position) {
        if (listItems.get(position).getDuration() == 1) {  // Assuming duration might represent a quantity-like attribute
            listItems.remove(position);
        } else {
            listItems.get(position).setDuration(listItems.get(position).getDuration() - 1);
        }
        db.saveTripPackageList(listItems);  // Update the list in DB
    }

    public void plusNumberItem(ArrayList<TripPackageDomain> listItems, int position) {
        listItems.get(position).setDuration(listItems.get(position).getDuration() + 1);
        db.saveTripPackageList("CartList", listItems);  // Update the list in DB
    }*/
}
