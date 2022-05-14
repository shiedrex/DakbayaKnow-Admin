package com.example.dakbayaknowadmin;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.RecyclerView;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lguupdates);

        getSupportActionBar().setTitle("Users' Applications");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ref = FirebaseDatabase.getInstance().getReference().child("applications");
        ref.keepSynced(true);
        recyclerView = findViewById(R.id.rv);
        searchView = findViewById(R.id.searchView);

        if(ref!=null){
            ref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if(snapshot.exists()) {
                        list = new ArrayList<>();
                        for(DataSnapshot ds : snapshot.getChildren()){
                            list.add(ds.getValue(Applications.class));
                        }
                        AdapterClassApplications adapterClass = new AdapterClassApplications(list, getApplicationContext());
                        recyclerView.setAdapter(adapterClass);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(UsersApplications.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
        if(searchView!=null){
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String s) {
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String s) {
                    search(s);
                    return false;
                }
            });
        }

    }

    @Override
    protected void onStart() {
        super.onStart();

    }
    private void search(String str) {
        ArrayList<Applications> myList = new ArrayList<>();
        for (Applications object : list) {
            if(object.getFullname().toLowerCase().contains(str.toLowerCase())){
                myList.add(object);
            }
        }
        AdapterClassApplications adapterClass = new AdapterClassApplications(myList, getApplicationContext());
        recyclerView.setAdapter(adapterClass);
    }
}