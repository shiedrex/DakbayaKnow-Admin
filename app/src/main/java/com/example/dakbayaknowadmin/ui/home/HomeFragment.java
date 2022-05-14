package com.example.dakbayaknowadmin.ui.home;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.dakbayaknowadmin.FeedbackRating;
import com.example.dakbayaknowadmin.R;
import com.example.dakbayaknowadmin.SystemMaintenance;
import com.example.dakbayaknowadmin.UsersApplications;

public class HomeFragment extends Fragment {

    Activity context;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        context = getActivity();

        View root = inflater.inflate(R.layout.fragment_home, container, false);

        return root;

    }

    public void onStart() {
        super.onStart();

//        ImageButton systemmaintenenace = context.findViewById(R.id.systemMaintenance);
//        systemmaintenenace.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(context, SystemMaintenance.class);
//                startActivity(intent);
//            }
//        });

        ImageButton feedbackRating = context.findViewById(R.id.feedbackRating);
        feedbackRating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, FeedbackRating.class);
                startActivity(intent);
            }
        });

        ImageButton userApplications = context.findViewById(R.id.applications);
        userApplications.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UsersApplications.class);
                startActivity(intent);
            }
        });
    }

}