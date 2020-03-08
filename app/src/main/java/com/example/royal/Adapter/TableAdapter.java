package com.example.royal.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.royal.MenuActivity;
import com.example.royal.Model.TableItems;
import com.example.royal.R;

import java.util.ArrayList;

public class TableAdapter extends RecyclerView.Adapter<TableAdapter.TableHolder> {
   int i;
    private Context mContext ;
    private ArrayList<TableItems> tableItems;
    @NonNull
    @Override
    public TableHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TableHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.table_item,parent,false));
    }

    public TableAdapter() {
    }

    public TableAdapter(Context mContext, ArrayList<TableItems> tableItems,int i) {
        this.mContext = mContext;
        this.tableItems = tableItems;
        this.i=i;
    }

    @Override
    public void onBindViewHolder(@NonNull TableHolder holder, final int position) {
        TableItems current=tableItems.get(position);
        holder.tableid.setText(current.getNum());
        holder.tableimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(mContext, MenuActivity.class);
                if (i==5){
                    intent.putExtra("point",true);
                    intent.putExtra("index",position+1);
                    intent.putExtra("Table",20);
                    mContext.startActivity(intent);
                }else {
                    intent.putExtra("index",position+1);
                    intent.putExtra("Table",20);
                    mContext.startActivity(intent);
                }


            }
        });

    }

    @Override
    public int getItemCount() {
        return tableItems.size();
    }

    public class TableHolder extends RecyclerView.ViewHolder {
        ImageView tableimage;
        TextView tableid;
        LinearLayout layout;
        public TableHolder(@NonNull View itemView) {
            super(itemView);
            tableimage=itemView.findViewById(R.id.imgtable);
            tableid=itemView.findViewById(R.id.num);

        }
    }
}
