package com.example.dakbayaknowadmin;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class UsersApplications extends AppCompatActivity {

    DatabaseReference ref;
    ArrayList<Applications> list;
    RecyclerView recyclerView;
    SearchView searchView;
    AdapterClassApplications adapterClassApplications;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lguupdates);

        getSupportActionBar().setTitle("Users' Applications");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ref = FirebaseDatabase.getInstance().getReference().child("applications");
        ref.keepSynced(true);
        recyclerView = findViewById(R.id.rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        searchView = findViewById(R.id.searchView);

        FirebaseRecyclerOptions<Applications> options = new FirebaseRecyclerOptions.Builder<Applications>()
                .setQuery(ref, Applications.class)
                .build();

        adapterClassApplications = new AdapterClassApplications(options);
        recyclerView.setAdapter(adapterClassApplications);
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapterClassApplications.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapterClassApplications.stopListening();
    }
}