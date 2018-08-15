package com.example.user.contentprovider;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.user.contentprovider.ContentProvider.R;
import com.example.user.contentprovider.data.HotelContract;

public class EditorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);

        if (selection.equals(getString(R.string.gender_female))) {
            mGender = HotelContract.GuestEntry.GENDER_FEMALE; // Кошка
        } else if (selection.equals(getString(R.string.gender_male))) {
            mGender = HotelContract.GuestEntry.GENDER_MALE; // Кот
        } else {
            mGender = HotelContract.GuestEntry.GENDER_UNKNOWN; // Не определен
        }
    }
}
