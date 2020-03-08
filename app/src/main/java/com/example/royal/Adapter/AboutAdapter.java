package com.example.royal.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.royal.Model.AboutModel;
import com.example.royal.Model.TableItems;
import com.example.royal.R;

import java.util.ArrayList;

public class AboutAdapter extends RecyclerView.Adapter<AboutAdapter.AboutHolder> {
    public AboutAdapter(ArrayList<AboutModel> aboutModels) {
        this.aboutModels = aboutModels;
    }

    public AboutAdapter() {
    }

    private ArrayList<AboutModel> aboutModels;
    @NonNull
    @Override
    public AboutHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AboutHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.about_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull AboutHolder holder, int position) {
        AboutModel current=aboutModels.get(position);
        holder.image.setImageResource(current.getImage());
        holder.Title.setText(current.getTitle());
        holder.desc.setText(current.getDe());
    }

    @Override
    public int getItemCount() {
        return aboutModels.size();
    }

    public class AboutHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView Title;
        TextView desc;
        public AboutHolder(@NonNull View itemView) {
            super(itemView);
            image=itemView.findViewById(R.id.aboutimage);
            Title=itemView.findViewById(R.id.nameabout);
            desc=itemView.findViewById(R.id.shortd);

        }
    }
}
