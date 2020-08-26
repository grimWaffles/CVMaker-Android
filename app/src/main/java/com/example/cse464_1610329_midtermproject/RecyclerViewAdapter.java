package com.example.cse464_1610329_midtermproject;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cse464_1610329_midtermproject.model.CV;

import java.io.File;
import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter< RecyclerViewAdapter.Viewholder> {

    ArrayList<CV> cvList;
    public RecyclerViewAdapter(ArrayList<CV> cvList){

        this.cvList=new ArrayList<>();
        this.cvList=cvList;
    }

    @Override
    public RecyclerViewAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        CardView cardView=(CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_main,parent,false);
        return new Viewholder(cardView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.Viewholder viewholder, int i) {
        CardView cardView=viewholder.cardView;

        CV cv=cvList.get(i);

        ImageView img=cardView.findViewById(R.id.card_img);
        TextView name=cardView.findViewById(R.id.cv_cname);
        TextView location=cardView.findViewById(R.id.cv_cpath);

        if(cv.getVariant().equals("variant1")){
            img.setImageResource(R.drawable.mycv);
        }
        else{
            img.setImageResource(R.drawable.mycv);
        }
        name.setText(cv.getName());
        location.setText(cv.getPath());
    }

    @Override
    public int getItemCount() {

        if(cvList!=null){
            return cvList.size();
        }
        else{
            return 0;
        }
    }

    public static class Viewholder extends RecyclerView.ViewHolder {

        private CardView cardView;

        public Viewholder(CardView itemView) {
            super(itemView);
            cardView=itemView;
        }
    }
}