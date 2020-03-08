package com.example.royal.Adapter;


import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.royal.Model.TModel;
import com.example.royal.R;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class ManeyAdapter extends FirestoreRecyclerAdapter<TModel, ManeyAdapter.CardHolder> {
    private FirebaseAuth mAuth;
    private FirebaseFirestore db;
    int price;

    public ManeyAdapter(@NonNull FirestoreRecyclerOptions<TModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull ManeyAdapter.CardHolder holder, int position, @NonNull TModel model) {
        holder.tabel.setText(model.getTabel() + "");
        holder.price.setText(model.getPrice() + "");
        price = model.getPrice();
    }

    public void addpoint(int price) {
        db = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        Map<String, Object> point = new HashMap<>();
        point.put("Ponits", price / 10);
        db.collection("Points").document(mAuth.getUid())
                .set(point)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.w("documentReference", "data is added sucssesfully");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("documentReference", "Error adding document", e);
                    }
                });


    }

    public void minseponit(int price) {
        db = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        Map<String, Object> point = new HashMap<>();
        point.put("Ponits", price / 10);
        db.collection("Points").document(mAuth.getUid())
                .set(point)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.w("documentReference", "data is added sucssesfully");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("documentReference", "Error adding document", e);
                    }
                });


    }

    public void deleteNote(int position, int sh) {
        if (sh == 20) {
            getSnapshots().getSnapshot(position).getReference().delete();//left
        } else if (sh == 10) {
            String is = getSnapshots().getSnapshot(position).getString("tabel");
            if (is.length() <= 2) {
                addpoint(price);
                getSnapshots().getSnapshot(position).getReference().delete();//right
            } else {
                minseponit(0);
                getSnapshots().getSnapshot(position).getReference().delete();//right
            }
        }
    }

    @NonNull
    @Override
    public ManeyAdapter.CardHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tabel_cash, parent, false);
        return new CardHolder(view);
    }

    public class CardHolder extends RecyclerView.ViewHolder {
        private TextView tabel;
        private TextView price;

        public CardHolder(View itemView) {
            super(itemView);
            tabel = itemView.findViewById(R.id.tabnum);
            price = itemView.findViewById(R.id.addprice);
        }
    }
}
