package com.example.slide3_anc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

public class Bai3 extends AppCompatActivity {

    private ImageView imgAnh;
    private static final int PICK_IMAGE_REQUEST = 100;
    private static final int GALLERY_REQUEST_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bai3);

        imgAnh=findViewById(R.id.imgAnh);
    }

        public void showImages(View v) {
            if (ContextCompat.checkSelfPermission(Bai3.this, Manifest.permission.READ_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {
                // Permission is not granted
                ActivityCompat.requestPermissions(Bai3.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        888);
                //request Code dùng để phân biệt nhiều xin quyền khác nhau
                Toast.makeText(this, "chưa có quyèn", Toast.LENGTH_SHORT).show();
            } else {
                run();
            }
        }

    private void run() {
        //Create an Intent with action as ACTION_PICK
        Intent intent=new Intent(Intent.ACTION_PICK);
        // Sets the type as image/*. This ensures only components of type image are selected
        intent.setType("image/*");
        //We pass an extra array with the accepted mime types. This will ensure only components with these MIME types as targeted.
        String[] mimeTypes = {"image/jpeg", "image/png"};
        intent.putExtra(Intent.EXTRA_MIME_TYPES,mimeTypes);
        // Launching the Intent
        startActivityForResult(intent,GALLERY_REQUEST_CODE);
    }

    public void onActivityResult(int requestCode,int resultCode,Intent data) {
        // Result code is RESULT_OK only if the user selects an Image
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK)
            switch (requestCode) {
                case GALLERY_REQUEST_CODE:
                    //data.getData return the content URI for the selected Image
                    Uri selectedImage = data.getData();
                    String[] filePathColumn = {MediaStore.Images.Media.DATA};
                    // Get the cursor
                    Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
                    // Move to first row
                    cursor.moveToFirst();
                    //Get the column index of MediaStore.Images.Media.DATA
                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                    //Gets the String value in the column
                    String imgDecodableString = cursor.getString(columnIndex);
                    cursor.close();
                    // Set the Image in ImageView after decoding the String
                    imgAnh.setImageBitmap(BitmapFactory.decodeFile(imgDecodableString));
                    break;
            }


//    public void showImages(View v) {
//        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
//        intent.setType("image/*");
//        startActivityForResult(intent, PICK_IMAGE_REQUEST);
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        switch (requestCode) {
//            case PICK_IMAGE_REQUEST:
//                if (resultCode == RESULT_OK) {
//                    Uri selectedImage = data.getData();
//
//                    // method 1
//                    try {
//                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), selectedImage);
//                        imgAnh.setImageBitmap(bitmap);
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//
//                    // method 2
//
//                    //try {
//                    //    InputStream imageStream = getContentResolver().openInputStream(selectedImage);
//                    //    Bitmap yourSelectedImage = BitmapFactory.decodeStream(imageStream);
//                    //    imageStream.close(;
//                    //   iv.setImageBitmap(yourSelectedImage);
//                    //} catch (FileNotFoundException e) {
//                    //    e.printStackTrace();
//                    //}
//
//                    // method 3
//                    // iv.setImageURI(selectedImage);
//                }
//                break;
//        }
//    }
    }
    }
