package com.example.royal.ui;

import androidx.lifecycle.LiveData;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.storage.FirebaseStorage;

import java.util.List;

public class Repository {
    private static Repository instace;

    static Repository getInstance() {
        if(instace==null){
            instace=new Repository();
        }
        return instace;
    }

public LiveData<List<QueryDocumentSnapshot>> getDataWhereEqualTo(String collectionpath,String fieldname,Object value){

    CollectionReference collectionReference= FirebaseFirestore.getInstance().collection(collectionpath);
    Query query=collectionReference.whereEqualTo(fieldname,value);
    LiveDataListenerOffer livedata=new LiveDataListenerOffer(query);
    return livedata.getLiveDaya();

}
}
