package com.example.slide3_anc;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.provider.CallLog;
import android.telecom.Call;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;
import java.util.List;

public class Bai2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bai2);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void callLogs(View view) {
        if (ContextCompat.checkSelfPermission(Bai2.this, Manifest.permission.READ_CALL_LOG)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted
            ActivityCompat.requestPermissions(Bai2.this,
                    new String[]{Manifest.permission.READ_CALL_LOG},
                    888);
            //request Code dùng để phân biệt nhiều xin quyền khác nhau
            Toast.makeText(this, "chưa có quyèn", Toast.LENGTH_SHORT).show();
        } else {
            run();
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void run() {

        String s = "";
        if (checkSelfPermission(Manifest.permission.READ_CALL_LOG) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    Activity#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for Activity#requestPermissions for more details.
            return;
        }
        String [] projection=new String[]{
                CallLog.Calls.DATE,
                CallLog.Calls.NUMBER,
                CallLog.Calls.DURATION

        };
        Cursor cursor = this.getContentResolver().query(CallLog.Calls.CONTENT_URI,
                projection, CallLog.Calls.DURATION+"<?", new String[]{"30"}, CallLog.Calls.DATE + " Asc");
//        int number = cursor.getColumnIndex(CallLog.Calls.NUMBER);
//
//        int date = cursor.getColumnIndex(CallLog.Calls.DATE);
//        int duration = cursor.getColumnIndex(CallLog.Calls.DURATION);
        if (cursor != null) {
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                while (cursor.isAfterLast() == false) {
                        for(int i=0;i< cursor.getColumnCount();i++) {
                            s += cursor.getString(i)+" - ";
                        }
                        s+="\n";
                    cursor.moveToNext();
                }
                cursor.close();

                Log.e("goi", s);
                TextView tv = findViewById(R.id.tvCallLogs);
                tv.setText(s);
            }
        }
    }
}
