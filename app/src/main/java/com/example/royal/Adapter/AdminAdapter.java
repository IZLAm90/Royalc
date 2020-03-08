package com.example.royal.Adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.royal.Model.AdminModel;
import com.example.royal.Model.OfferModel;
import com.example.royal.R;
import com.example.royal.ui.UserDialog;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.dynamic.SupportFragmentWrapper;

public class AdminAdapter extends FirestoreRecyclerAdapter<AdminModel, AdminAdapter.CardHolder> {
    public AdminAdapter(@NonNull FirestoreRecyclerOptions<AdminModel> options) {
        super(options);
    }
    @Override
    protected void onBindViewHolder(@NonNull AdminAdapter.CardHolder holder, int position, @NonNull AdminModel model) {
        holder.name.setText(model.getName());
        holder.email.setText(model.getEmail());

    }
    @NonNull
    @Override
    public AdminAdapter.CardHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.admin_items, parent, false);
        return new CardHolder(view);
    }
    public class CardHolder extends RecyclerView.ViewHolder   {
        private TextView name;
        private TextView email;
        private Object FragmentManager;
        public CardHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.nameadmin);
            email = itemView.findViewById(R.id.emailadmin);
        }
    }
}
