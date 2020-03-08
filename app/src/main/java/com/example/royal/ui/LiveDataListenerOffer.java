package com.example.royal.ui;

import android.util.Log;

import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class LiveDataListenerOffer extends LiveData<DocumentSnapshot> implements EventListener<QuerySnapshot> {
    private ListenerRegistration listenerRegistration;
    private MutableLiveData<List<QueryDocumentSnapshot>> listMutableLiveData;
    private Query query;

    public LiveDataListenerOffer(Query query) {
        this.query = query;
        listMutableLiveData = new MutableLiveData<>();
        listenerRegistration = query.addSnapshotListener(this);
    }


    @Override
    public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
        if (queryDocumentSnapshots != null && e == null) {

            ArrayList<QueryDocumentSnapshot> mylist = new ArrayList<>();
            for (QueryDocumentSnapshot snapshotL : queryDocumentSnapshots) {
                mylist.add(snapshotL);
            }
            listMutableLiveData.setValue(mylist);
        } else {
            SendLog(e.getMessage());

        }
    }


    @Override
    protected void onActive() {
        super.onActive();
        listenerRegistration = query.addSnapshotListener(this);
    }

    @Override
    protected void onInactive() {
        super.onInactive();
        listenerRegistration.remove();
        listenerRegistration = null;
    }

    public void SendLog(String massage) {
        Log.i("LiveDataListenerOffer", massage);

    }

    public MutableLiveData<List<QueryDocumentSnapshot>> getLiveDaya() {
        return listMutableLiveData;

    }
}
