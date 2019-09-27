package com.example.slide3_anc;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

class ContactsHolder extends RecyclerView.ViewHolder {
    public TextView tvName,tvPhone,tvID;

    public ContactsHolder(@NonNull View itemView) {
        super(itemView);
        tvName=itemView.findViewById(R.id.tvName);
        tvPhone=itemView.findViewById(R.id.tvPhone);
        tvID=itemView.findViewById(R.id.tvId);
    }
}
