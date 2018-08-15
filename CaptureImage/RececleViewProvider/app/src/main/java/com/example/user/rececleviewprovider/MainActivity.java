package com.example.user.rececleviewprovider;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView =(RecyclerView) findViewById(R.id.news_list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

    }
    private void setAdapter() {

        // loading data from database by loadAll method
        List<NewsItem> newsItemList = mNewsItemDao.loadAll();


        // Creating adapter for recyclerview
        Adapter newsAdapter = new Adapter(newsItemList);
        mRecyclerView.setAdapter(Adapter);
    }
}
