package com.example.dakbayaknowadmin;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class AdapterClassApplications extends FirebaseRecyclerAdapter<Applications, AdapterClassApplications.myViewHolder> {
    ArrayList<Applications> list;
    Context context;
    Dialog dialog;
    DatabaseReference appref;
    FirebaseAuth fAuth;

    public AdapterClassApplications(@NonNull FirebaseRecyclerOptions<Applications> options) {
        super(options);
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_holder, parent, false);
        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, @SuppressLint("RecyclerView") int position, @NonNull Applications model) {
        holder.us.setText(model.getFullname());
        holder.des.setText(model.getDestination());
        holder.stat.setText(model.getStatus());
        holder.heal.setText(model.getHealth());
        holder.trav.setText(model.getTravellerType());
        holder.orig.setText(model.getOrigin());
        holder.travDate.setText(model.getDeparture());
        holder.arrivDate.setText(model.getArrival());
        holder.govid.setText(model.getGovId());

        if (holder.heal.getText().toString().contains("Safe")) {
            holder.heal.setTextColor(Color.parseColor("#008000"));
        } else if (holder.heal.getText().toString().contains("Stay at Home")) {
            holder.heal.setTextColor(Color.parseColor("#FF0000"));
        }

        if (holder.stat.getText().toString().contains("Please upload required requirements (vaccinated)")) {
            holder.stat.setTextColor(Color.parseColor("#008000"));
        } else if (holder.stat.getText().toString().contains("Please upload required requirements (unvaccinated)")) {
            holder.stat.setTextColor(Color.parseColor("#FFA500"));
        } else if (holder.stat.getText().toString().contains("Pending")) {
            holder.stat.setTextColor(Color.parseColor("#FFFF00"));
        } else if (holder.stat.getText().toString().contains("Approved")) {
            holder.stat.setTextColor(Color.parseColor("#008000"));
        } else if (holder.stat.getText().toString().contains("Declined")) {
            holder.stat.setTextColor(Color.parseColor("#FF0000"));
        }

        holder.app.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HashMap<String, Object> user = new HashMap<>();
                user.put("status", "Approved");

                FirebaseDatabase.getInstance().getReference().child("applications")
                        .child(getRef(position).getKey()).updateChildren(user)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful()){
                                    Toast.makeText(holder.stat.getContext(), "Application Approved", Toast.LENGTH_SHORT).show();
                                }else{
                                    Toast.makeText(holder.stat.getContext(), "Error", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
        holder.dec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HashMap<String, Object> user = new HashMap<>();
                user.put("status", "Declined");

                FirebaseDatabase.getInstance().getReference().child("applications")
                        .child(getRef(position).getKey()).updateChildren(user)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful()){
                                    Toast.makeText(holder.stat.getContext(), "Application Declined", Toast.LENGTH_SHORT).show();
                                }else{
                                    Toast.makeText(holder.stat.getContext(), "Error", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
    }

    class myViewHolder extends RecyclerView.ViewHolder {
        TextView us, des, stat, heal, trav, orig, travDate, arrivDate, govid;
        Button app, dec;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            us = itemView.findViewById(R.id.user);
            des = itemView.findViewById(R.id.destination);
            stat = itemView.findViewById(R.id.status);
            heal = itemView.findViewById(R.id.health);
            trav = itemView.findViewById(R.id.travellerType);
            orig = itemView.findViewById(R.id.origin);
            travDate = itemView.findViewById(R.id.travelDate);
            arrivDate = itemView.findViewById(R.id.arrivalDate);
            govid = itemView.findViewById(R.id.govId);
            app = itemView.findViewById(R.id.approve);
            dec = itemView.findViewById(R.id.decline);

            dialog = new Dialog(itemView.getContext());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.setContentView(R.layout.applications_dialog);
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                    TextView user = dialog.findViewById(R.id.username);
                    TextView Des = dialog.findViewById(R.id.des);
                    TextView Stat = dialog.findViewById(R.id.stat);
                    TextView Heal = dialog.findViewById(R.id.heal);
                    TextView Traveller = dialog.findViewById(R.id.traveller);
                    TextView Orig = dialog.findViewById(R.id.orig);
                    TextView TravDate = dialog.findViewById(R.id.travDate);
                    TextView ArrivDate = dialog.findViewById(R.id.arrivDate);
                    TextView GovID = dialog.findViewById(R.id.govID);

                    Button ok = dialog.findViewById(R.id.okButton);

                    user.setText(us.getText().toString());
                    Des.setText(des.getText().toString());
                    Stat.setText(stat.getText().toString());
                    Heal.setText(heal.getText().toString());
                    Traveller.setText(trav.getText().toString());
                    Orig.setText(orig.getText().toString());
                    TravDate.setText(travDate.getText().toString());
                    ArrivDate.setText(arrivDate.getText().toString());
                    GovID.setText(govid.getText().toString());

                    dialog.show();

                    ok.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialog.dismiss();
                        }
                    });
                }
            });
        }
    }
}


