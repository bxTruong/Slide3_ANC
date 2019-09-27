package com.example.slide3_anc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.loader.content.CursorLoader;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class openBai1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_bai1);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==888){
            Toast.makeText(this,"người dùng đã tương tác với dialog xin quyền" + grantResults[0] ,Toast.LENGTH_SHORT).show();

        }
    }

    public void loadContacts(View view) {
        if (ContextCompat.checkSelfPermission(openBai1.this, Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted
            ActivityCompat.requestPermissions(openBai1.this,
                    new String[]{Manifest.permission.READ_CONTACTS},
                    888);
            //request Code dùng để phân biệt nhiều xin quyền khác nhau
            Toast.makeText(this,"chưa có quyèn",Toast.LENGTH_SHORT).show();
        }else {
            run();
        }
    }

    private void run() {

        Uri.parse("content://contacts/people");

        Uri uri = ContactsContract.Contacts.CONTENT_URI;

        Cursor c;

        List<Contacts> contactsList = new ArrayList<>();

        String[] projection = new String[]
                {ContactsContract.Contacts._ID,
                        ContactsContract.Contacts.DISPLAY_NAME,
                        ContactsContract.Contacts.HAS_PHONE_NUMBER};

        CursorLoader cursorLoader = new CursorLoader(
                this,
                uri,
                projection,
                null,
                null,
                null);
        c = cursorLoader.loadInBackground();

        ContentResolver cr = getContentResolver();
        //---display the contact id and name and phone number----
        if (c.moveToFirst()) {
            do {
                //---get the contact id and name
                String contactID = c.getString(c.getColumnIndex(
                        ContactsContract.Contacts._ID));
                String contactDisplayName =
                        c.getString(c.getColumnIndex(
                                ContactsContract.Contacts.DISPLAY_NAME));
                Log.v("Content Providers", contactID + ", " +
                        contactDisplayName);

                String contactDisplayPhone = "";
                //---get phone number---
                int hasPhone =
                        c.getInt(c.getColumnIndex(
                                ContactsContract.Contacts.HAS_PHONE_NUMBER));
                if (hasPhone == 1) {
                    Cursor phoneCursor =
                            getContentResolver().query(
                                    ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
                                    ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " +
                                            contactID, null, null);
                    while (phoneCursor.moveToNext()) {
                        Log.e("Content Providers",
                                contactDisplayPhone = phoneCursor.getString(
                                        phoneCursor.getColumnIndex(
                                                ContactsContract.CommonDataKinds.Phone.NUMBER)));
                    }
                    phoneCursor.close();

                }
                contactsList.add(new Contacts(contactDisplayName, contactDisplayPhone));

            } while (c.moveToNext());

            RecyclerView rcContacts = findViewById(R.id.rcContacts);
            ContactsAdapters contactsAdapters = new ContactsAdapters(this, contactsList);
            rcContacts.setAdapter(contactsAdapters);

            LinearLayoutManager vertical = new LinearLayoutManager(this);
            rcContacts.setLayoutManager(vertical);
        }
    }
}
