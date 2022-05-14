package com.example.dakbayaknowadmin;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AdapterClassLGU extends RecyclerView.Adapter<AdapterClassLGU.myViewHolder> {
    ArrayList<LGU> list;
    Dialog dialog;
    Context context;

    public AdapterClassLGU(ArrayList<LGU> list, Context context){
        this.list = list;
        this.context = context;
    }
    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_holder3,parent,false);
        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {
        holder.ct.setText(list.get(position).getCity());
        holder.po.setText(list.get(position).getPolicy());
        holder.al.setText(list.get(position).getAlert());

        final LGU model = new LGU();

        dialog = new Dialog(holder.itemView.getContext());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.setContentView(R.layout.requirements_dialog);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                TextView lgu = dialog.findViewById(R.id.lgu);
                EditText policy = dialog.findViewById(R.id.policy);
                EditText alert = dialog.findViewById(R.id.alert);
                Button update = dialog.findViewById(R.id.updateButton);
                Button cancel = dialog.findViewById(R.id.cancelButton);

                dialog.show();

                lgu.setText(model.getCity());
                policy.setText(model.getPolicy());
                alert.setText(model.getAlert());

                update.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String po = policy.getText().toString();
                        String al = alert.getText().toString();

                        HashMap<Object, String> map = new HashMap<>();
                        map.put("policy", po);
                        map.put("alert", al);

                        if(map.containsKey(position)){
                            policy.setText(map.get(position));
                        }

                        dialog.dismiss();
                    }
                });
                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class myViewHolder extends RecyclerView.ViewHolder {
        TextView ct, po, al;
        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            ct = itemView.findViewById(R.id.city);
            po = itemView.findViewById(R.id.policy);
            al = itemView.findViewById(R.id.alert);

//            final LGU model = new LGU();
//
//            dialog = new Dialog(itemView.getContext());
//
//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    dialog.setContentView(R.layout.requirements_dialog);
//                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//
//                    TextView lgu = dialog.findViewById(R.id.lgu);
//                    EditText policy = dialog.findViewById(R.id.policy);
//                    EditText alert = dialog.findViewById(R.id.alert);
//                    Button update = dialog.findViewById(R.id.updateButton);
//                    Button cancel = dialog.findViewById(R.id.cancelButton);
//
//                    lgu.setText(ct.getText());
//                    policy.setText(po.getText());
//                    alert.setText(al.getText());
//
//                    dialog.show();
//
//                    update.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View view) {
//                            String po = policy.getText().toString();
//                            String al = alert.getText().toString();
//
//                            updateData(po,al);
//
//                            dialog.dismiss();
//                        }
//                    });
//                    cancel.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View view) {
//                            dialog.dismiss();
//                        }
//                    });
//                }
//            });
        }
    }
//    private void updateData(String po, String al) {
//        HashMap map = new HashMap();
//        map.put("policy", po);
//        map.put("alert", al);
//
//        if(map.containsKey(position)){
//            policy.setText(map.get(position));
//        }
//
//        FirebaseDatabase.getInstance().getReference().child("lgu").updateChildren(map)
//                .addOnCompleteListener(new OnCompleteListener() {
//                    @Override
//                    public void onComplete(@NonNull Task task) {
//                        if(task.isSuccessful()){
//                            Toast.makeText(context, "Successfully Updated", Toast.LENGTH_SHORT).show();
//                            dialog.dismiss();
//                        }else {
//                            Toast.makeText(context, "Failed To Update", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                });
//    }
}
