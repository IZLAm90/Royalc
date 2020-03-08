package com.example.royal.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.royal.Model.MenuItems;
import com.example.royal.R;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class MenuAdapter extends RecyclerView.Adapter <MenuAdapter.MenuHolder>{
    private ArrayList<MenuItems> menulist;
    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(int position,int icon);
    }

    public MenuAdapter(ArrayList<MenuItems> menulist) {
        this.menulist = menulist;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }
    @NonNull
    @Override
    public MenuHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MenuHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.menu_items,parent,false),mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MenuHolder holder, int position) {

        MenuItems current=menulist.get(position);

        holder.image.setImageResource(current.getImage());
        holder.name.setText(current.getName());
        holder.price.setText(current.getPrice());
        holder.love.setImageResource(current.getLove());
    }

    @Override
    public int getItemCount() {
        return menulist.size();
    }

    public class MenuHolder extends RecyclerView.ViewHolder {
       ImageView image;
        TextView name;
        TextView price;
        ImageView love;

        public MenuHolder(@NonNull View itemView ,final OnItemClickListener listener) {
            super(itemView);

            image=itemView.findViewById(R.id.imgmenu);
            name=itemView.findViewById(R.id.menuname);
            price=itemView.findViewById(R.id.menuprice);
            love=itemView.findViewById(R.id.love);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position,love.getId());
                        }
                    }
                }
            });
        }
    }
}
