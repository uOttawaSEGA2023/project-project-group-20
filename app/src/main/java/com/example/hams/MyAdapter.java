package com.example.hams;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder>{
    //ADAPTER FOR PENDING USERS

    Context context;
    //list of patients to display
    List<Patient> patientList;

    public MyAdapter(Context context, List<Patient> patientList) {
        this.context = context;
        this.patientList = patientList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //create a viewholder to store all the instances of request_patient views
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.request_patient,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        //set the text to match the item from the list passed in
        holder.first.setText(patientList.get(position).getFirstName());
        holder.last.setText(patientList.get(position).getLastName());
        holder.email.setText(patientList.get(position).getEmailAddress());
        holder.address.setText(patientList.get(position).getAddress());
        holder.phoneNumber.setText(patientList.get(position).getPhoneNo());
        holder.healthCard.setText(patientList.get(position).getHealthCard());

        holder.accept.setText("Accept");
        holder.reject.setText("Reject");
    }

    @Override
    public int getItemCount() {
        return patientList.size();
    }
}