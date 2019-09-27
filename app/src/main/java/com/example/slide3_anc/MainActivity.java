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
import android.content.Intent;
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

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Dung de truy van du lieu co tren thiet bi


    }





//        Cursor cursor=getContentResolver().query(uri,null,null,null,null);
//
//        List<Contacts> contactsList=new ArrayList<>();
//
//        if(cursor!=null){
//            if (cursor.getCount()>0){
//                cursor.moveToFirst();
//                //cứ mỗi giá trị tìm được thì kiểm tra xem có phải là giá trị cuối cùng không , nếu không thì next, nếu là giá trị cuối cùng thì ==flase
//                while (cursor.isAfterLast()==false) {
//
//                    String id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
//                    String name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
//                    int phone = cursor.getInt(cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER));
//
//                    String numberphone = "";
//                    if (phone == 1) {
//                        Cursor phoneCursor =
//                                getContentResolver().query(
//                                        ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
//                                        ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " +
//                                                id, null, null);
//                        while (phoneCursor.moveToNext()) {
//                            Log.e("Content Providers",
//                                    numberphone = phoneCursor.getString(
//                                            phoneCursor.getColumnIndex(
//                                                    ContactsContract.CommonDataKinds.Phone.NUMBER)));
//                        }
//                        phoneCursor.close();
//
//                        Contacts contacts = new Contacts(name, phone+"");
//
//                        contactsList.add(contacts);
//                        cursor.moveToNext();
//
//                }}
//                cursor.close();
//
//                RecyclerView rcContacts=findViewById(R.id.rcContacts);
//                ContactsAdapters contactsAdapters=new ContactsAdapters(this,contactsList);
//                rcContacts.setAdapter(contactsAdapters);
//
//                LinearLayoutManager vertical = new LinearLayoutManager(this);
//                rcContacts.setLayoutManager(vertical);
//
//            }
//        }


    public void openBai1(View view) {
        Intent intent=new Intent(this,openBai1.class);
        startActivity(intent);
    }

    public void openBai2(View view) {
        Intent intent=new Intent(this,Bai2.class);
        startActivity(intent);
    }

    public void openBai3(View view) {
        Intent intent=new Intent(this,Bai3.class);
        startActivity(intent);
    }
}
