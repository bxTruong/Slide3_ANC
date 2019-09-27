package com.example.slide3_anc;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ContactsAdapters extends RecyclerView.Adapter<ContactsHolder> {

    private Context context;
    private List<Contacts> contactsList;

    public ContactsAdapters(Context context, List<Contacts> contactsList) {
        this.context = context;
        this.contactsList = contactsList;
    }

    @NonNull
    @Override
    public ContactsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.contacts,parent,false);
        ContactsHolder contactsHolder=new ContactsHolder(view);
        return contactsHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ContactsHolder holder, int position) {
            Contacts contacts=contactsList.get(position);
            holder.tvID.setText((position+1)+"");
            holder.tvName.setText(contacts.getName());
            holder.tvPhone.setText(contacts.getPhone());
    }

    @Override
    public int getItemCount() {
        return contactsList.size();
    }
}
