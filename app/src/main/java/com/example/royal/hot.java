package com.example.royal;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.royal.Adapter.MenuAdapter;
import com.example.royal.Model.MenuItems;
import com.example.royal.ui.Calculate;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static android.content.Context.MODE_PRIVATE;
import static android.provider.Telephony.Mms.Part.TEXT;


/**
 * A simple {@link Fragment} subclass.
 */
public class hot extends Fragment {
    FirebaseAuth auth = FirebaseAuth.getInstance();
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    public ArrayList<MenuItems> menuItems;
    private RecyclerView mRecyclerView;
    private ArrayList<MenuItems> bookmark;
    private MenuAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private View v;
    int qqq = 0;

    public hot() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_hot2, container, false);
        bookmark = new ArrayList<>();
        mRecyclerView = v.findViewById(R.id.recyclerviewho);
        mAdapter = new MenuAdapter(menuItems);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new GridLayoutManager(getActivity(), 3);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new MenuAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, int icon) {
                changeItem(position, menuItems.get(position).getLove());
                loveidd(position);
            }
        });
        return v;

    }

    public void loveidd(int position) {
        Map<String, Object> loveid = new HashMap<>();
        String id = auth.getUid();
        for (int i = 0; i <= loveid.size(); i++) {
            loveid.put("name", menuItems.get(position).getName());
            loveid.put("price", menuItems.get(position).getPrice());
            loveid.put("love", menuItems.get(position).getLove());
            loveid.put("image", menuItems.get(position).getImage());

        }
        db.collection("Bookmark").document(id).collection(menuItems.get(position).getName())
                .add(loveid)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d("doooooooooooooooooon", "DocumentSnapshot successfully written!");

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("noooooooooooooooo", "Error writing document", e);
                    }
                });


    }

    public void createlist() {
        menuItems = new ArrayList<>();
        menuItems.add(new MenuItems(R.drawable.ic_mug, "قهوة تركي", "10$", R.drawable.ic_favori));
        menuItems.add(new MenuItems(R.drawable.ic_cocktail, "قهوة تركي دوبل", "10$", R.drawable.ic_favori));
        menuItems.add(new MenuItems(R.drawable.ic_tea_1, "قهوة اسبرسو سنجل", "10$", R.drawable.ic_favori));
        menuItems.add(new MenuItems(R.drawable.ic_mug, "قهوة اسبرسو دوبل", "10$", R.drawable.ic_favori));
        menuItems.add(new MenuItems(R.drawable.ic_mug, "قهوة فرنسي", "10$", R.drawable.ic_favori));
        menuItems.add(new MenuItems(R.drawable.ic_mug, "قهوة فرنسي دوبل", "10$", R.drawable.ic_favori));
        menuItems.add(new MenuItems(R.drawable.ic_mug, "نسكافيه 1x3", "10$", R.drawable.ic_favori));
        menuItems.add(new MenuItems(R.drawable.ic_mug, "هوت افوكادو", "10$", R.drawable.ic_favori));
        menuItems.add(new MenuItems(R.drawable.ic_mug, "كوفي ميكس", "10$", R.drawable.ic_favori));
        menuItems.add(new MenuItems(R.drawable.ic_mug, "كافيه لاتيه عادى", "10$", R.drawable.ic_favori));
        menuItems.add(new MenuItems(R.drawable.ic_mug, "كافيه لاتيه اسبرسو", "10$", R.drawable.ic_favori));
        menuItems.add(new MenuItems(R.drawable.ic_mug, "كابتشينو عادى", "10$", R.drawable.ic_favori));
        menuItems.add(new MenuItems(R.drawable.ic_mug, "كابتشينو اسبرسو", "10$", R.drawable.ic_favori));
        menuItems.add(new MenuItems(R.drawable.ic_mug, "قهوة سوري", "10$", R.drawable.ic_favori));
        menuItems.add(new MenuItems(R.drawable.ic_mug, "قهوة سورى دوبل", "10$", R.drawable.ic_favori));
        menuItems.add(new MenuItems(R.drawable.ic_mug, "قهوة نوتيلا", "10$", R.drawable.ic_favori));
        menuItems.add(new MenuItems(R.drawable.ic_mug, "قهوة سبيشيال", "10$", R.drawable.ic_favori));
        menuItems.add(new MenuItems(R.drawable.ic_mug, "تسكافية", "10$", R.drawable.ic_favori));
        menuItems.add(new MenuItems(R.drawable.ic_mug, "نسكافية بلاك", "10$", R.drawable.ic_favori));
        menuItems.add(new MenuItems(R.drawable.ic_mug, "نسكافية كلاسيك", "10$", R.drawable.ic_favori));
        menuItems.add(new MenuItems(R.drawable.ic_mug, "هوت شوكليت ايطالى", "10$", R.drawable.ic_favori));
        menuItems.add(new MenuItems(R.drawable.ic_mug, "هوت موكا", "10$", R.drawable.ic_favori));
        menuItems.add(new MenuItems(R.drawable.ic_mug, "هوت سايدر", "10$", R.drawable.ic_favori));
        menuItems.add(new MenuItems(R.drawable.ic_mug, "قرفه", "10$", R.drawable.ic_favori));
        menuItems.add(new MenuItems(R.drawable.ic_mug, "زنجبيل", "10$", R.drawable.ic_favori));
        menuItems.add(new MenuItems(R.drawable.ic_mug, "ينسون", "10$", R.drawable.ic_favori));
        menuItems.add(new MenuItems(R.drawable.ic_mug, "ليمون", "10$", R.drawable.ic_favori));
        menuItems.add(new MenuItems(R.drawable.ic_mug, "شاى اخضر", "10$", R.drawable.ic_favori));
        menuItems.add(new MenuItems(R.drawable.ic_mug, "شاى نكهات", "10$", R.drawable.ic_favori));
        menuItems.add(new MenuItems(R.drawable.ic_mug, "نعناع", "10$", R.drawable.ic_favori));
        menuItems.add(new MenuItems(R.drawable.ic_mug, "سحلب", "10$", R.drawable.ic_favori));
        menuItems.add(new MenuItems(R.drawable.ic_mug, "كركديه", "10$", R.drawable.ic_favori));
        menuItems.add(new MenuItems(R.drawable.ic_mug, "كوكتيل ساخن", "10$", R.drawable.ic_favori));
        menuItems.add(new MenuItems(R.drawable.ic_mug, "قرفة زنجبيل", "10$", R.drawable.ic_favori));
        menuItems.add(new MenuItems(R.drawable.ic_mug, "شاى براد", "10$", R.drawable.ic_favori));
        menuItems.add(new MenuItems(R.drawable.ic_mug, "حليب", "10$", R.drawable.ic_favori));
        menuItems.add(new MenuItems(R.drawable.ic_mug, "عسل", "10$", R.drawable.ic_favori));
        menuItems.add(new MenuItems(R.drawable.ic_mug, "فواكه", "10$", R.drawable.ic_favori));
        menuItems.add(new MenuItems(R.drawable.ic_mug, "مكسرات", "10$", R.drawable.ic_favori));


    }

    public void changeItem(int position, int icon) {
        String p = menuItems.get(position).getPrice();
        String su = p.substring(0, 2);
        int x = Integer.parseInt(su);
        Intent result=getActivity().getIntent();
        int table=result.getIntExtra("Table",0);

        if (icon == R.drawable.ic_favori) {
            menuItems.get(position).changlove(R.drawable.ic_favorite_black_24dp);
            if(table==20){
            qqq = qqq + x;
            saveData(qqq);
            Toast.makeText(getActivity(), qqq + "", Toast.LENGTH_LONG).show();
            mAdapter.notifyItemChanged(position);}
        } else if (icon == R.drawable.ic_favorite_black_24dp) {
            menuItems.get(position).changlove(R.drawable.ic_favori);
            if(table==20) {
                qqq = qqq - x;
                if (qqq <= 0) {
                    qqq = 0;
                    saveData(qqq);
                    Toast.makeText(getActivity(), qqq + "ooo", Toast.LENGTH_LONG).show();
                } else {
                    saveData(qqq);
                    Toast.makeText(getActivity(), qqq + "nn", Toast.LENGTH_LONG).show();
                }
            }

            mAdapter.notifyItemChanged(position);

        }
    }

    public void saveData(int qq) {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("share", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("sum", qq);
        editor.apply();
       Toast.makeText(getActivity(), "Data saved", Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createlist();
    }


}
