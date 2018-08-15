package com.example.user.picasopetrofit;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

class MovieViewHolder extends RecyclerView.ViewHolder {

    public ImageView imageView;
    public MovieViewHolder(View itemView) {
        super(itemView);
        imageView = (ImageView) itemView.findViewById(R.id.imageView);
    }
}
