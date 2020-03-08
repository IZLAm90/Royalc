package com.example.royal.ui;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import java.util.List;

public class ViewModelOffer extends ViewModel {
    private LiveData<List<QueryDocumentSnapshot>> modeloffer;
    private Repository repository;
    public void init( String Collectionpath, String fieldname,Object value){
        if(modeloffer!=null){
            return;
        }
        repository=new Repository().getInstance();
        modeloffer=repository.getDataWhereEqualTo(Collectionpath,fieldname,value);
    }
        public LiveData<List<QueryDocumentSnapshot>> getOffer(){
        return modeloffer;
        }


}
