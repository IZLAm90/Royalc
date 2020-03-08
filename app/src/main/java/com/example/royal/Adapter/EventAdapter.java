package com.example.royal.Adapter;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.royal.Model.EventModel;
import com.example.royal.R;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.common.collect.BiMap;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

public class EventAdapter extends FirestoreRecyclerAdapter<EventModel, EventAdapter.CardHolder> {
    Context mcontext;
    public EventAdapter(@NonNull FirestoreRecyclerOptions<EventModel> options, Context mcontext) {
        super(options);
       this.mcontext=mcontext;
    }

    @Override
    protected void onBindViewHolder(@NonNull EventAdapter.CardHolder holder, int position, @NonNull EventModel model) {
        holder.name.setText(model.getName());
        holder.date.setText(model.getDate());
        holder.desc.setText(model.getDesc());
      Glide.with(mcontext).asBitmap().load(model.getImage()).dontAnimate().placeholder(R.drawable.evv).into(holder.image);

       // holder.image.setImageResource(model.getImage());
    }

    public void deleteNote(int position) {
        getSnapshots().getSnapshot(position).getReference().delete();
    }

    @NonNull
    @Override
    public EventAdapter.CardHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.event_items, parent, false);
        return new CardHolder(view);
    }

    public class CardHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private TextView date;
        private TextView desc;
        private ImageView image;
        public CardHolder(View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.evename);
            date = itemView.findViewById(R.id.evedate);
            desc = itemView.findViewById(R.id.evedesc);
            image = itemView.findViewById(R.id.Eventimage);
        }
    }
}
