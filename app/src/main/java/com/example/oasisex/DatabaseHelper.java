package com.example.oasisex;


import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.database.Cursor;

import com.example.oasisex.model.MyTrip;
import com.example.oasisex.model.TripPackage;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Database Name and Version
    private static final String DATABASE_NAME = "trip_database";
    private static final int DATABASE_VERSION = 1;

    // Users Table
    private static final String TABLE_USERS = "users";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_PASSWORD = "password";
    private static final String COLUMN_AGE = "age";

    // TripPackage Table
    private static final String TABLE_TRIP_PACKAGES = "trip_packages";
    private static final String COLUMN_PACKAGE_ID = "package_id";
    private static final String COLUMN_DESTINATION = "destination";
    private static final String COLUMN_ACCOMMODATION = "accommodation";
    private static final String COLUMN_DESCRIPTION = "description";
    private static final String COLUMN_PRICE = "price";
    private static final String COLUMN_DURATION = "duration";
    private static final String COLUMN_START_DATE = "start_date";
    private static final String COLUMN_END_DATE = "end_date";
    private static final String COLUMN_RALLY_POINT = "rally_point";
    private static final String COLUMN_ACTIVITY_DESCRIPTION = "activity_description";
    private static final String COLUMN_RATING = "rating";
    private static final String COLUMN_TRIP_NAME = "trip_name";
    private static final String COLUMN_IMG_SOURCE = "imgSrc";

    // UserTripPackage Table
    private static final String TABLE_USER_TRIP_PACKAGES = "user_trip_packages";
    private static final String COLUMN_USER_PACKAGE_BOOK_ID = "USER_PACKAGE_BOOK_ID";
    private static final String COLUMN_USER_EMAIL = "user_email";
    private static final String COLUMN_TRIP_PACKAGE_ID = "trip_package_id";
    private static final String COLUMN_NUM_INDIVIDUALS = "num_individuals";
    private static final String COLUMN_NOTES = "notes";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create Users Table
        String createUserTableQuery = "CREATE TABLE " + TABLE_USERS + " ("
                + COLUMN_EMAIL + " TEXT PRIMARY KEY,"
                + COLUMN_NAME + " TEXT,"
                + COLUMN_PASSWORD + " TEXT,"
                + COLUMN_AGE + " INTEGER)";
        db.execSQL(createUserTableQuery);

        // Create TripPackage Table
        String createTripPackageTableQuery = "CREATE TABLE " + TABLE_TRIP_PACKAGES + " ("
                + COLUMN_PACKAGE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_DESTINATION + " TEXT,"
                + COLUMN_ACCOMMODATION + " TEXT,"
                + COLUMN_DESCRIPTION + " TEXT,"
                + COLUMN_PRICE + " REAL,"
                + COLUMN_DURATION + " INTEGER,"
                + COLUMN_IMG_SOURCE + " INTEGER,"
                + COLUMN_START_DATE + " TEXT,"
                + COLUMN_END_DATE + " TEXT,"
                + COLUMN_RALLY_POINT + " TEXT,"
                + COLUMN_ACTIVITY_DESCRIPTION + " TEXT,"
                + COLUMN_RATING + " REAL," // Include the new "rating" column
                + COLUMN_TRIP_NAME + " TEXT)";
        db.execSQL(createTripPackageTableQuery);

        // Create UserTripPackage Table
        String createUserTripPackageTableQuery = "CREATE TABLE " + TABLE_USER_TRIP_PACKAGES + " ("
                + COLUMN_USER_PACKAGE_BOOK_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_USER_EMAIL + " TEXT,"
                + COLUMN_TRIP_PACKAGE_ID + " INTEGER,"
                + COLUMN_NUM_INDIVIDUALS + " INTEGER,"
                + COLUMN_NOTES + " TEXT,"
                + " FOREIGN KEY (" + COLUMN_USER_EMAIL + ") REFERENCES " + TABLE_USERS + "(" + COLUMN_EMAIL + "),"
                + " FOREIGN KEY (" + COLUMN_TRIP_PACKAGE_ID + ") REFERENCES " + TABLE_TRIP_PACKAGES + "(" + COLUMN_PACKAGE_ID + "))";
        db.execSQL(createUserTripPackageTableQuery);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older tables if exist
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER_TRIP_PACKAGES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TRIP_PACKAGES);
        // Create tables again
        onCreate(db);
    }

    public long addUser(String email, String name, String password, int age) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_EMAIL, email);
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_PASSWORD, password);
        values.put(COLUMN_AGE, age);
        long result = db.insert(TABLE_USERS, null, values);
        db.close();
        return result;
    }
/*
     public long addTripPackage(String destination, String accommodation, String description,
                               double price, int duration, String startDate, String endDate,
                               String rallyPoint, String activityDescription, double rating, String tripName) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_DESTINATION, destination);
        values.put(COLUMN_ACCOMMODATION, accommodation);
        values.put(COLUMN_DESCRIPTION, description);
        values.put(COLUMN_PRICE, price);
        values.put(COLUMN_DURATION, duration);
        values.put(COLUMN_START_DATE, startDate);
        values.put(COLUMN_END_DATE, endDate);
        values.put(COLUMN_RALLY_POINT, rallyPoint);
        values.put(COLUMN_ACTIVITY_DESCRIPTION, activityDescription);
        values.put(COLUMN_RATING, rating);
        values.put(COLUMN_TRIP_NAME, tripName);
        long result = db.insert(TABLE_TRIP_PACKAGES, null, values);
        db.close();
        return result;
    }*/

    public long addTripPackage(String destination, String accommodation, String description,
                              double price, int duration, String startDate, String endDate,
                               String rallyPoint, String activityDescription, double rating, String tripName,
                               int imgSrc) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_DESTINATION, destination);
        values.put(COLUMN_ACCOMMODATION, accommodation);
        values.put(COLUMN_DESCRIPTION, description);
        values.put(COLUMN_PRICE, price);
        values.put(COLUMN_DURATION, duration);
        values.put(COLUMN_START_DATE, startDate);
        values.put(COLUMN_END_DATE, endDate);
        values.put(COLUMN_RALLY_POINT, rallyPoint);
        values.put(COLUMN_ACTIVITY_DESCRIPTION, activityDescription);
        values.put(COLUMN_RATING, rating);
        values.put(COLUMN_TRIP_NAME, tripName);
        values.put(COLUMN_IMG_SOURCE, imgSrc);
        long result = -1;
        try {
            result = db.insert(TABLE_TRIP_PACKAGES, null, values);
            if (result == -1) {
                Log.e("DatabaseHelper", "Failed to insert trip into TripPackage table.");
            }
        } catch (SQLException e) {
            Log.e("DatabaseHelper", "Error inserting trip into TripPackage table: " + e.getMessage());
        } finally {
            db.close();
        }
        return result;
    }


    public boolean addUserTripPackage(String userEmail, long tripPackageId, int numIndividuals, String notes) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_EMAIL, userEmail);
        values.put(COLUMN_TRIP_PACKAGE_ID, tripPackageId);
        values.put(COLUMN_NUM_INDIVIDUALS, numIndividuals);
        values.put(COLUMN_NOTES, notes);
        long result = db.insert(TABLE_USER_TRIP_PACKAGES, null, values);
        db.close();
        return (result != -1);
    }


    public Boolean insertData(String username, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password", password);
        long result = MyDB.insert("users", null, contentValues);
        if(result==-1) return false;
        else
            return true;
    }

    public Boolean checkusername(String username) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where username = ?", new String[]{username});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }
    public Boolean checkusernamepassword(String email, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_USERS + " WHERE " + COLUMN_EMAIL + " = ? AND " + COLUMN_PASSWORD + " = ?", new String[] {email, password});
        if(cursor.getCount() > 0){
            cursor.close();
            return true;
        } else {
            cursor.close();
            return false;
        }
    }


    public List<MyTrip> getUserReservations(String userEmail) {
        List<MyTrip> reservations = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        // Query to retrieve reservations for the given user
        String query = "SELECT "
                + COLUMN_USER_PACKAGE_BOOK_ID + ", "
                + COLUMN_TRIP_NAME + ", "
                + COLUMN_DESTINATION + ", "
                + COLUMN_PRICE + ", "
                + COLUMN_DURATION +", "
                +TABLE_USER_TRIP_PACKAGES + "." + COLUMN_TRIP_PACKAGE_ID+
                " FROM " + TABLE_USER_TRIP_PACKAGES +
                " JOIN " + TABLE_TRIP_PACKAGES +
                " ON " + TABLE_USER_TRIP_PACKAGES + "." + COLUMN_TRIP_PACKAGE_ID +
                " = " + TABLE_TRIP_PACKAGES + "." + COLUMN_PACKAGE_ID +
                " WHERE " + COLUMN_USER_EMAIL + " = ?";

        Cursor cursor = db.rawQuery(query, new String[]{userEmail});
        // Logging statements to check column indexes
        int columnIndexUserPackageBookID = cursor.getColumnIndex( COLUMN_USER_PACKAGE_BOOK_ID);
        int columnIndexTripName = cursor.getColumnIndex(COLUMN_TRIP_NAME);
        int columnIndexDestination = cursor.getColumnIndex(COLUMN_DESTINATION);
        int columnIndexPrice = cursor.getColumnIndex(COLUMN_PRICE);
        int columnIndexDuration = cursor.getColumnIndex(COLUMN_DURATION);
        int columnIndexCOLUMN_TRIP_PACKAGE_ID = cursor.getColumnIndex(COLUMN_TRIP_PACKAGE_ID);

        Log.d("DatabaseHelper", "ColumnIndex TripName: " + columnIndexTripName);
        Log.d("DatabaseHelper", "ColumnIndex Destination: " + columnIndexDestination);
        Log.d("DatabaseHelper", "ColumnIndex Price: " + columnIndexPrice);
        Log.d("DatabaseHelper", "ColumnIndex Duration: " + columnIndexDuration);
        // Iterate through the cursor to fetch reservation data
        if (cursor != null && cursor.moveToFirst()) {
            do {
                // Extract reservation details from the cursor
                MyTrip reservation = new MyTrip(
                        cursor.getInt(columnIndexUserPackageBookID),
                        cursor.getString(columnIndexTripName),
                        cursor.getString(columnIndexDestination),
                        "$" + cursor.getDouble(columnIndexPrice) + "/" + cursor.getInt(columnIndexDuration) + " Days",
                        getImageResourceId(cursor.getString(columnIndexDestination)
                        ),
                        cursor.getInt(columnIndexCOLUMN_TRIP_PACKAGE_ID)
                );
                // Add the reservation to the list
                reservations.add(reservation);
            } while (cursor.moveToNext());
            // Close the cursor
            cursor.close();
        }
        // Close the database connection
        db.close();

        return reservations;
    }

    public MyTrip getReservation(int id) {
        MyTrip reservation = null;
        SQLiteDatabase db = this.getReadableDatabase();

        // Query to retrieve reservations for the given user
        String query = "SELECT "
                + COLUMN_TRIP_NAME + ", "
                + COLUMN_NUM_INDIVIDUALS + ", "
                + COLUMN_NOTES + ", "
                + COLUMN_DURATION +", "
                + COLUMN_IMG_SOURCE +", "
                +TABLE_USER_TRIP_PACKAGES + "." + COLUMN_TRIP_PACKAGE_ID+
                " FROM " + TABLE_USER_TRIP_PACKAGES +
                " JOIN " + TABLE_TRIP_PACKAGES +
                " ON " + TABLE_USER_TRIP_PACKAGES + "." + COLUMN_TRIP_PACKAGE_ID +
                " = " + TABLE_TRIP_PACKAGES + "." + COLUMN_PACKAGE_ID +
                " WHERE " + TABLE_USER_TRIP_PACKAGES + "." +COLUMN_TRIP_PACKAGE_ID + " = ?";

        Cursor cursor = db.rawQuery(query, new String[]{""+id});
        // Logging statements to check column indexes
        int columnIndexUserPackageBookID = 0;
        int columnIndexTripName = cursor.getColumnIndex(COLUMN_TRIP_NAME);
        int columnIndexNomOfIndv = cursor.getColumnIndex(COLUMN_NUM_INDIVIDUALS);
        int columnIndexNote = cursor.getColumnIndex(COLUMN_NOTES);
        int columnIndexDuration = cursor.getColumnIndex(COLUMN_DURATION);
        int columnIndexCOLUMN_TRIP_PACKAGE_ID = cursor.getColumnIndex(COLUMN_TRIP_PACKAGE_ID);
        int columnIndexCOLUMN_IMG_SOURCE = cursor.getColumnIndex(COLUMN_IMG_SOURCE);

        Log.d("DatabaseHelper", "ColumnIndex TripName: " + columnIndexTripName);
        Log.d("DatabaseHelper", "ColumnIndex Duration: " + columnIndexDuration);
        // Iterate through the cursor to fetch reservation data
        if (cursor != null && cursor.moveToFirst()) {
            do {
                // Extract reservation details from the cursor
                reservation = new MyTrip(
                        cursor.getInt(columnIndexUserPackageBookID),
                        cursor.getString(columnIndexTripName),
                        cursor.getString(columnIndexNote),
                        cursor.getString(columnIndexNomOfIndv),
                        cursor.getInt(columnIndexCOLUMN_IMG_SOURCE ),
                        cursor.getInt(columnIndexCOLUMN_TRIP_PACKAGE_ID)
                );
            } while (cursor.moveToNext());
            // Close the cursor
            cursor.close();
        }
        // Close the database connection
        db.close();

        return reservation;
    }


    // Method to get the image resource ID for a given destination
    private int getImageResourceId(String destination) {
        // You should implement this method to return the appropriate image resource ID based on the destination
        // For example, you can use a switch statement or a HashMap to map destinations to image resource IDs
        // For simplicity, let's assume you have implemented this method
        switch (destination) {
            case "Riyadh":
                return R.drawable.riyadh_rc;
            case "Abha":
                return R.drawable.abha_rc;
            case "Jeddah":
                return R.drawable.pic3;
            default:
                return 0; // Return 0 if image resource ID is not found
        }
    }

    public boolean updateUserTripPackage( int tripPackageId, int numIndividuals, String notes) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NUM_INDIVIDUALS, numIndividuals);
        values.put(COLUMN_NOTES, notes);

        // Construct the where clause to update the right entry
        String selection = COLUMN_USER_PACKAGE_BOOK_ID + " = ?";
        String[] selectionArgs = {  String.valueOf(tripPackageId) };

        // Perform the update
        int updateCount = db.update(TABLE_USER_TRIP_PACKAGES, values, selection, selectionArgs);
        db.close(); // Closing database connection
        return updateCount > 0;
    }

    public TripPackage getTripPackageDetails(int packageId) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from "+TABLE_TRIP_PACKAGES+" where "+COLUMN_PACKAGE_ID+" = ?",
                new String[]{""+packageId});
        // Logging statements to check column indexes
        TripPackage trip =null;
        // Iterate through the cursor to fetch reservation data
        if (cursor != null && cursor.moveToFirst()) {
            do {
                // Extract reservation details from the cursor
                trip = new TripPackage(
                        cursor.getInt(cursor.getColumnIndex(COLUMN_PACKAGE_ID)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_DESTINATION)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_ACCOMMODATION)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPTION)),
                        cursor.getDouble(cursor.getColumnIndex(COLUMN_PRICE)),
                        cursor.getInt(cursor.getColumnIndex(COLUMN_DURATION)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_START_DATE)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_END_DATE)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_RALLY_POINT)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_ACTIVITY_DESCRIPTION)),
                        cursor.getDouble(cursor.getColumnIndex(COLUMN_RATING)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_TRIP_NAME)),
                        cursor.getInt(cursor.getColumnIndex(COLUMN_IMG_SOURCE))
                );
                // Add the reservation to the list
            } while (cursor.moveToNext());
            // Close the cursor
            cursor.close();
        }
        // Close the database connection
        db.close();

        return trip;
    }

    public void saveTripPackageList(List<TripPackage> listCart) {
        /*for (TripPackage tripPackage : listCart) {
            addTripPackage(tripPackage.getDestination(), tripPackage.getAccommodation(),
                    tripPackage.getDescription(), tripPackage.getPrice(),
                    tripPackage.getDuration(), tripPackage.getStartDate(),
                    tripPackage.getEndDate(), tripPackage.getRallyPoint(),
                    tripPackage.getActivityDescription(), tripPackage.getRating(),
                    tripPackage.getTripName());
        }*/
    }


    public ArrayList<TripPackage> getTripPackageList() {
        ArrayList<TripPackage> tripPackages = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT * FROM " + TABLE_TRIP_PACKAGES;
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                TripPackage tripPackage = new TripPackage(
                        cursor.getInt(cursor.getColumnIndex(COLUMN_PACKAGE_ID)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_ACCOMMODATION)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_DESTINATION)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPTION)),
                        cursor.getDouble(cursor.getColumnIndex(COLUMN_PRICE)),
                        cursor.getInt(cursor.getColumnIndex(COLUMN_DURATION)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_START_DATE)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_END_DATE)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_RALLY_POINT)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_ACTIVITY_DESCRIPTION)),
                        cursor.getDouble(cursor.getColumnIndex(COLUMN_RATING)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_TRIP_NAME)),
                        cursor.getInt(cursor.getColumnIndex(COLUMN_IMG_SOURCE))
                );
                tripPackages.add(tripPackage);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return tripPackages;
    }
    public ArrayList<TripPackage> searchTripsPakages(String destination) {
        ArrayList<TripPackage> tripPackages = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT * FROM " + TABLE_TRIP_PACKAGES + " where "+COLUMN_DESTINATION+" like '%"+destination+"%'";
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                TripPackage tripPackage = new TripPackage(
                        cursor.getInt(cursor.getColumnIndex(COLUMN_PACKAGE_ID)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_ACCOMMODATION)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_DESTINATION)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPTION)),
                        cursor.getDouble(cursor.getColumnIndex(COLUMN_PRICE)),
                        cursor.getInt(cursor.getColumnIndex(COLUMN_DURATION)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_START_DATE)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_END_DATE)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_RALLY_POINT)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_ACTIVITY_DESCRIPTION)),
                        cursor.getDouble(cursor.getColumnIndex(COLUMN_RATING)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_TRIP_NAME)),
                        cursor.getInt(cursor.getColumnIndex(COLUMN_IMG_SOURCE))
                );
                tripPackages.add(tripPackage);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return tripPackages;
    }

    public  boolean deleteUserTripPackage(int book_id) {
        SQLiteDatabase db = this.getWritableDatabase();

        // Assuming "trip_package_id" is the column name that identifies records in the addUserTripPackage table
        return db.delete(TABLE_USER_TRIP_PACKAGES, COLUMN_USER_PACKAGE_BOOK_ID +"= ?", new String[] {String.valueOf(book_id)}) > 0;
    }



}

