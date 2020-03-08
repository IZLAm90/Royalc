package com.example.royal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.royal.Adapter.ManeyAdapter;
import com.example.royal.Model.TModel;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class TableCash extends AppCompatActivity {
    private RecyclerView recyclerView;
    Toolbar toolbar;
    ManeyAdapter adapter;
    FirebaseFirestore db =  FirebaseFirestore.getInstance();
    CollectionReference notebookRef = db.collection("Tabels");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_cash);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        toolbar=findViewById(R.id.toolbarr);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Casher");
        startRecyclerView();
    }

    private void startRecyclerView() {
        Query query = notebookRef;
        FirestoreRecyclerOptions options = new FirestoreRecyclerOptions.Builder<TModel>()
                .setQuery(query,TModel.class)
                .build();
        adapter = new ManeyAdapter(options);
        Toast.makeText(getApplicationContext(),options.toString(),Toast.LENGTH_LONG).show();
        recyclerView = findViewById(R.id.recyclerviewtc);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        new ItemTouchHelper(new ItemTouchHelper
                .SimpleCallback(0,ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
               switch (direction){
                   case ItemTouchHelper.LEFT:
                       adapter.deleteNote(viewHolder.getAdapterPosition(),20);
                       break;
                   case ItemTouchHelper.RIGHT:
                       adapter.deleteNote(viewHolder.getAdapterPosition(),10);
                            break;

               }


            }
        }).attachToRecyclerView(recyclerView);
    }
    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}
