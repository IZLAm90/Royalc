package com.example.royal.Adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.royal.Model.EventModel;
import com.example.royal.Model.OfferModel;
import com.example.royal.R;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class OfferAdapter extends FirestoreRecyclerAdapter<OfferModel, OfferAdapter.CardHolder> {
    private Context mcontext;
    public OfferAdapter(@NonNull FirestoreRecyclerOptions<OfferModel> options,Context context) {
        super(options);
        mcontext=context;
    }

    @Override
    protected void onBindViewHolder(@NonNull OfferAdapter.CardHolder holder, int position, @NonNull OfferModel model) {
        holder.name.setText(model.getName());
        holder.date.setText(model.getDate());
        holder.desc.setText(model.getDesc());
     //   holder.image.setImageResource(model.getImage());
        Glide.with(mcontext).asBitmap().load(model.getImage()).dontAnimate().placeholder(R.drawable.of).into(holder.image);
    }

    public void deleteNote(int position) {
        getSnapshots().getSnapshot(position).getReference().delete();
    }

    @NonNull
    @Override
    public OfferAdapter.CardHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.offer_items, parent, false);

        return new CardHolder(view);
    }

    public class CardHolder extends RecyclerView.ViewHolder {

        private TextView name;
        private TextView date;
        private TextView desc;
       private ImageView image;

        public CardHolder(View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.offname);
            date = itemView.findViewById(R.id.offdate);
            desc = itemView.findViewById(R.id.offdesc);
            image = itemView.findViewById(R.id.imageof);
        }
    }
}
