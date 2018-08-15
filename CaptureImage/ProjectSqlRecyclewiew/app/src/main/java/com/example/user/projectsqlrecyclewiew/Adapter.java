package com.example.user.projectsqlrecyclewiew;

import android.content.Context;
import android.graphics.Movie;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class Adapter  extends RecyclerView.Adapter {


    private List<Movie> mContactList;
    private LayoutInflater mInflater;
    private Context mContext;


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

    }

    @Override
    public int getItemCount(){
        return (mContactList==null) ? 0 : mContactList.size();}

    public void setmMovieList(List<Movie> mContactList){
        this.mContactList=new ArrayList<Movie>();
        this.mContactList.addAll(mContactList);
        notifyDataSetChanged();

    }
}

