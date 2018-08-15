package com.example.user.contentproviderexample;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] projection = {
                FriendsContract.Columns._ID,
                FriendsContract.Columns.NAME,
                FriendsContract.Columns.EMAIL,
                FriendsContract.Columns.PHONE
        };
        ContentResolver contentR = getContentResolver();
        ContentValues values = new ContentValues();
        values.put(FriendsContract.Columns.NAME, "Sam");
        values.put(FriendsContract.Columns.EMAIL, "sam@gmail.com");
        values.put(FriendsContract.Columns.PHONE, "+13676254985");
        Uri uri = contentR.insert(FriendsContract.CONTENT_URI, values);


        ContentResolver contentResolver = getContentResolver();
        Cursor cursor = contentResolver.query(FriendsContract.CONTENT_URI,
                projection,
                null,
                null,
                FriendsContract.Columns.NAME);
        if (cursor != null) {
            Log.d(TAG, "count: " + cursor.getCount());
            // перебор элементов
            while (cursor.moveToNext()) {
                for (int i = 0; i < cursor.getColumnCount(); i++) {
                    Log.d(TAG, cursor.getColumnName(i) + " : " + cursor.getString(i));
                }
                Log.d(TAG, "=========================");
            }
            cursor.close();
        } else {
            Log.d(TAG, "Cursor is null");
        }
    }

}