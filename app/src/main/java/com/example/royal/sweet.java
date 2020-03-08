package com.example.royal;


import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.royal.Adapter.MenuAdapter;
import com.example.royal.Model.MenuItems;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;


/**
 * A simple {@link Fragment} subclass.
 */
public class sweet extends Fragment {
    private ArrayList<MenuItems> menuItems;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private MenuAdapter mAdapter;
    private View v;
    int qqq = 0;
    public sweet() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       v=inflater.inflate(R.layout.fragment_sweet2, container, false);
        mRecyclerView=v.findViewById(R.id.recyclerviewsw);
        mAdapter = new MenuAdapter(menuItems);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new GridLayoutManager(getActivity(),3);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new MenuAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position,int icon) {
                changeItem(position,menuItems.get(position).getLove());
            }

        });
        return v;
    }
    public void createlist() {
        menuItems = new ArrayList<>();
        menuItems.add(new MenuItems(R.drawable.ic_mug, "tea", "10$", R.drawable.ic_favori));
        menuItems.add(new MenuItems(R.drawable.ic_mug, "tea", "10$", R.drawable.ic_favori));
        menuItems.add(new MenuItems(R.drawable.ic_mug, "tea", "10$", R.drawable.ic_favori));
        menuItems.add(new MenuItems(R.drawable.ic_mug, "tea", "10$", R.drawable.ic_favori));
        menuItems.add(new MenuItems(R.drawable.ic_mug, "tea", "10$", R.drawable.ic_favori));
        menuItems.add(new MenuItems(R.drawable.ic_mug, "tea", "10$", R.drawable.ic_favori));
        menuItems.add(new MenuItems(R.drawable.ic_mug, "tea", "10$", R.drawable.ic_favori));
        menuItems.add(new MenuItems(R.drawable.ic_mug, "tea", "10$", R.drawable.ic_favori));
        menuItems.add(new MenuItems(R.drawable.ic_mug, "tea", "10$", R.drawable.ic_favori));
        menuItems.add(new MenuItems(R.drawable.ic_mug, "tea", "10$", R.drawable.ic_favori));
        menuItems.add(new MenuItems(R.drawable.ic_mug, "tea", "10$", R.drawable.ic_favori));
        menuItems.add(new MenuItems(R.drawable.ic_mug, "tea", "10$", R.drawable.ic_favori));
        menuItems.add(new MenuItems(R.drawable.ic_mug, "tea", "10$", R.drawable.ic_favori));
        menuItems.add(new MenuItems(R.drawable.ic_mug, "tea", "10$", R.drawable.ic_favori));
        menuItems.add(new MenuItems(R.drawable.ic_mug, "tea", "10$", R.drawable.ic_favori));
        menuItems.add(new MenuItems(R.drawable.ic_mug, "tea", "10$", R.drawable.ic_favori));
        menuItems.add(new MenuItems(R.drawable.ic_mug, "tea", "10$", R.drawable.ic_favori));
        menuItems.add(new MenuItems(R.drawable.ic_mug, "tea", "10$", R.drawable.ic_favori));
        menuItems.add(new MenuItems(R.drawable.ic_mug, "tea", "10$", R.drawable.ic_favori));
        menuItems.add(new MenuItems(R.drawable.ic_mug, "tea", "10$", R.drawable.ic_favori));
        menuItems.add(new MenuItems(R.drawable.ic_mug, "tea", "10$", R.drawable.ic_favori));
        menuItems.add(new MenuItems(R.drawable.ic_mug, "tea", "10$", R.drawable.ic_favori));


    }
    public void changeItem(int position, int icon) {
        String p = menuItems.get(position).getPrice();
        String su = p.substring(0, 2);
        int x = Integer.parseInt(su);
        if (icon == R.drawable.ic_favori) {
            menuItems.get(position).changlove(R.drawable.ic_favorite_black_24dp);
            qqq = qqq + x ;
            saveData(qqq);
            Toast.makeText(getActivity(), qqq + "", Toast.LENGTH_LONG).show();
            mAdapter.notifyItemChanged(position);
        } else if (icon == R.drawable.ic_favorite_black_24dp) {
            menuItems.get(position).changlove(R.drawable.ic_favori);
            qqq = qqq - x ;
            if(qqq<=0){
                qqq=0;
                saveData(qqq);
                Toast.makeText(getActivity(), qqq + "ooo", Toast.LENGTH_LONG).show();
            }
            else{
                saveData(qqq);
                Toast.makeText(getActivity(), qqq + "nn", Toast.LENGTH_LONG).show();
            }

            mAdapter.notifyItemChanged(position);

        }
    }
    public void saveData(int qq) {
        SharedPreferences sharedPreferences =getActivity().getSharedPreferences("shares",MODE_PRIVATE) ;
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("sum",qq);
        editor.apply();
        Toast.makeText(getActivity(), "Data saved", Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createlist();
    }

}
