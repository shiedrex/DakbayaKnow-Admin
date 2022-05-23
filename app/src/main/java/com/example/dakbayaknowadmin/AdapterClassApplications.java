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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;

public class AdapterClassApplications extends FirebaseRecyclerAdapter<Applications, AdapterClassApplications.myViewHolder> {
    ArrayList<Applications> list;
    Context context;
    Dialog dialog;
    int mExpandedPosition = -1;
    int previousExpandedPosition = -1;

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

        String govIdUri,vaccCardUri;

        govIdUri=model.getGovIdImage();
        vaccCardUri=model.getVaccCardImage();

        Picasso.get().load(govIdUri).into(holder.govIdImage);
        Picasso.get().load(vaccCardUri).into(holder.vaccCardImage);

        if (holder.heal.getText().toString().contains("Good Condition")) {
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

        final boolean isExpanded = position==mExpandedPosition;
        holder.govIdImageLayout.setVisibility(isExpanded?View.VISIBLE:View.GONE);
        holder.travLayout.setVisibility(isExpanded?View.VISIBLE:View.GONE);
        holder.origLayout.setVisibility(isExpanded?View.VISIBLE:View.GONE);
        holder.travDateLayout.setVisibility(isExpanded?View.VISIBLE:View.GONE);
        holder.arrivDateLayout.setVisibility(isExpanded?View.VISIBLE:View.GONE);
        holder.govidLayout.setVisibility(isExpanded?View.VISIBLE:View.GONE);
        holder.govIdImageLayout.setVisibility(isExpanded?View.VISIBLE:View.GONE);
        holder.vaccCardImageLayout.setVisibility(isExpanded?View.VISIBLE:View.GONE);
        holder.itemView.setActivated(isExpanded);

        if (isExpanded)
            previousExpandedPosition = position;

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mExpandedPosition = isExpanded ? -1:position;
                notifyItemChanged(previousExpandedPosition);
                notifyItemChanged(position);
            }
        });

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
        ImageView govIdImage, vaccCardImage;
        Button app, dec;
        LinearLayout travLayout, origLayout, travDateLayout, arrivDateLayout, govidLayout, govIdImageLayout, vaccCardImageLayout;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            //textview
            us = itemView.findViewById(R.id.user);
            des = itemView.findViewById(R.id.destination);
            stat = itemView.findViewById(R.id.status);
            heal = itemView.findViewById(R.id.health);
            trav = itemView.findViewById(R.id.travellerType);
            orig = itemView.findViewById(R.id.origin);
            travDate = itemView.findViewById(R.id.travelDate);
            arrivDate = itemView.findViewById(R.id.arrivalDate);
            govid = itemView.findViewById(R.id.govId);
            govIdImage = itemView.findViewById(R.id.govIdImage);
            vaccCardImage = itemView.findViewById(R.id.vaccCardImage);
            //button
            app = itemView.findViewById(R.id.approve);
            dec = itemView.findViewById(R.id.decline);
            //layout
            travLayout = itemView.findViewById(R.id.travellerTypeLayout);
            origLayout = itemView.findViewById(R.id.originLayout);
            travDateLayout = itemView.findViewById(R.id.travelDateLayout);
            arrivDateLayout = itemView.findViewById(R.id.arrivalDateLayout);
            govidLayout = itemView.findViewById(R.id.govIdLayout);
            govIdImageLayout = itemView.findViewById(R.id.govIdImageLayout);
            vaccCardImageLayout = itemView.findViewById(R.id.vaccCardImageLayout);
        }
    }
}


