package com.example.user.projectsqlrecyclewiew;

import android.content.ContentValues;
import android.database.Cursor;
import android.graphics.Movie;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    Adapter Adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        Adapter = new Adapter();
        recyclerView.setAdapter(Adapter);

    }


    public void onClickAddName(View view) {
        ContentValues values = new ContentValues();
        values.put(Provider.NAME, ((EditText) findViewById(R.id.editText2)).getText().toString());
        values.put(Provider.GRADE, ((EditText) findViewById(R.id.editText3)).getText().toString());

        Uri uri = getContentResolver().insert(Provider.CONTENT_URL, values);


        Toast.makeText(getBaseContext(),
                uri.toString(), Toast.LENGTH_LONG).show();
    }



}
