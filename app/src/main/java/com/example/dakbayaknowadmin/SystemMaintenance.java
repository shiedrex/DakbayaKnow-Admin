package com.example.dakbayaknowadmin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class SystemMaintenance extends AppCompatActivity {

    Button lguPolicyAlerts, lguContacts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_system_maintenance);

        getSupportActionBar().setTitle("System Maintenance");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

}
