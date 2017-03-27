package com.example.makroid.tellit;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by himanshu on 25/3/17.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyHolder>{

    LayoutInflater inflater;
    public RecyclerAdapter(Context c) {
        inflater = LayoutInflater.from(c);
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.story_card_view,parent,false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 5;
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        public MyHolder(View itemView) {
            super(itemView);

        }
    }
}
