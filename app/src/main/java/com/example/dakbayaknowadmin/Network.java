package com.example.dakbayaknowadmin;

import android.app.Application;

import com.google.firebase.database.FirebaseDatabase;

public class Network extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
    }
}
