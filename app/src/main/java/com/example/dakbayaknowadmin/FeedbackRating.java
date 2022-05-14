package com.example.dakbayaknowadmin;

import android.app.Dialog;
import android.os.Bundle;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FeedbackRating extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference ref;
    FirebaseAuth fAuth;
    FirebaseUser firebaseUser;

    Dialog dialog;

    ArrayList<Feedback> list;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedbackrating);

        getSupportActionBar().setTitle("Feedback and Rating");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        fAuth = FirebaseAuth.getInstance();
        firebaseUser = fAuth.getCurrentUser();

        dialog = new Dialog(this);

        recyclerView = findViewById(R.id.rv);
        ref = FirebaseDatabase.getInstance().getReference().child("userFeedback");
        ref.keepSynced(true);

    }

    @Override
    protected void onStart() {
        super.onStart();
        if (ref != null) {
            ref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                        list = new ArrayList<>();
                        for (DataSnapshot ds : snapshot.getChildren()) {
                            list.add(ds.getValue(Feedback.class));
                        }
                        AdapterClassFeedback adapterClass = new AdapterClassFeedback(list);
                        recyclerView.setAdapter(adapterClass);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(FeedbackRating.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
