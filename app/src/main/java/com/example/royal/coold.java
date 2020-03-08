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
public class coold extends Fragment {
    private ArrayList<MenuItems> menuItems;
    private RecyclerView mRecyclerView;
    private MenuAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private View v;
    int qqq = 0;

    public coold() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_coold2, container, false);
        mRecyclerView = v.findViewById(R.id.recyclerviewco);
        mAdapter = new MenuAdapter(menuItems);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new GridLayoutManager(getActivity(), 3);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new MenuAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, int icon) {
                changeItem(position, menuItems.get(position).getLove());

            }

        });

        return v;
    }

    public void createlist() {
        menuItems = new ArrayList<>();
        menuItems.add(new MenuItems(R.drawable.ic_mug, "بيبسى دايت", "10$", R.drawable.ic_favori));
        menuItems.add(new MenuItems(R.drawable.ic_milk, "بيبسى عادي", "10$", R.drawable.ic_favori));
        menuItems.add(new MenuItems(R.drawable.ic_soda_1, "سفن اب", "10$", R.drawable.ic_favori));
        menuItems.add(new MenuItems(R.drawable.ic_wine_glass, "ميرندا تفاح", "10$", R.drawable.ic_favori));
        menuItems.add(new MenuItems(R.drawable.ic_water, "ميرندا برتقال", "10$", R.drawable.ic_favori));
        menuItems.add(new MenuItems(R.drawable.ic_mug, "ميرندا خوخ", "10$", R.drawable.ic_favori));
        menuItems.add(new MenuItems(R.drawable.ic_mug, "فيروز تفاح", "10$", R.drawable.ic_favori));
        menuItems.add(new MenuItems(R.drawable.ic_mug, "فيروز كوكتيل", "10$", R.drawable.ic_favori));
        menuItems.add(new MenuItems(R.drawable.ic_mug, "فيروز خوخ", "10$", R.drawable.ic_favori));
        menuItems.add(new MenuItems(R.drawable.ic_mug, "شويبس اناناس", "10$", R.drawable.ic_favori));
        menuItems.add(new MenuItems(R.drawable.ic_mug, "شويبس رمان", "10$", R.drawable.ic_favori));
        menuItems.add(new MenuItems(R.drawable.ic_mug, "شويبس خوخ", "10$", R.drawable.ic_favori));
        menuItems.add(new MenuItems(R.drawable.ic_mug, "ليمون كنتالوب", "10$", R.drawable.ic_favori));
        menuItems.add(new MenuItems(R.drawable.ic_mug, "بريل", "10$", R.drawable.ic_favori));
        menuItems.add(new MenuItems(R.drawable.ic_mug, "موسى", "10$", R.drawable.ic_favori));
        menuItems.add(new MenuItems(R.drawable.ic_mug, "ريد بول", "10$", R.drawable.ic_favori));
        menuItems.add(new MenuItems(R.drawable.ic_mug, "مو سي", "10$", R.drawable.ic_favori));
        menuItems.add(new MenuItems(R.drawable.ic_mug, "بريل ليمون", "10$", R.drawable.ic_favori));
        menuItems.add(new MenuItems(R.drawable.ic_mug, "جهينة ميكس", "10$", R.drawable.ic_favori));
        menuItems.add(new MenuItems(R.drawable.ic_mug, "مياه معدنيه", "10$", R.drawable.ic_favori));
        menuItems.add(new MenuItems(R.drawable.ic_mug, "مانجو", "10$", R.drawable.ic_favori));
        menuItems.add(new MenuItems(R.drawable.ic_mug, "فراوله", "10$", R.drawable.ic_favori));
        menuItems.add(new MenuItems(R.drawable.ic_cocktail, "جوافة", "10$", R.drawable.ic_favori));
        menuItems.add(new MenuItems(R.drawable.ic_cocktail, "كيو ي", "10$", R.drawable.ic_favori));
        menuItems.add(new MenuItems(R.drawable.ic_soda_1, "اناناس", "10$", R.drawable.ic_favori));
        menuItems.add(new MenuItems(R.drawable.ic_water, "خوخ", "10$", R.drawable.ic_favori));
        menuItems.add(new MenuItems(R.drawable.ic_cocktail, "تفاح", "10$", R.drawable.ic_favori));
        menuItems.add(new MenuItems(R.drawable.ic_milk, "برتقال", "10$", R.drawable.ic_favori));
        menuItems.add(new MenuItems(R.drawable.ic_tea_1, "رمان", "10$", R.drawable.ic_favori));
        menuItems.add(new MenuItems(R.drawable.ic_mug, "افوكادو", "10$", R.drawable.ic_favori));
        menuItems.add(new MenuItems(R.drawable.ic_mug, "افوكادو ميكس", "10$", R.drawable.ic_favori));
        menuItems.add(new MenuItems(R.drawable.ic_mug, "افوكادو مكسرات", "10$", R.drawable.ic_favori));
        menuItems.add(new MenuItems(R.drawable.ic_mug, "موز باللبن", "10$", R.drawable.ic_favori));
        menuItems.add(new MenuItems(R.drawable.ic_mug, "حليب", "10$", R.drawable.ic_favori));
        menuItems.add(new MenuItems(R.drawable.ic_mug, "ايس كريم", "10$", R.drawable.ic_favori));
        menuItems.add(new MenuItems(R.drawable.ic_mug, "نعناع ابيض", "10$", R.drawable.ic_favori));
        menuItems.add(new MenuItems(R.drawable.ic_mug, "بلح باللبن", "10$", R.drawable.ic_favori));
        menuItems.add(new MenuItems(R.drawable.ic_mug, "تين", "10$", R.drawable.ic_favori));
        menuItems.add(new MenuItems(R.drawable.ic_mug, "ليمون", "10$", R.drawable.ic_favori));
        menuItems.add(new MenuItems(R.drawable.ic_mug, "برتقال و جزر", "10$", R.drawable.ic_favori));
        menuItems.add(new MenuItems(R.drawable.ic_mug, "ليمون نعناع", "10$", R.drawable.ic_favori));
        menuItems.add(new MenuItems(R.drawable.ic_mug, "ليمون فراوله", "10$", R.drawable.ic_favori));
        menuItems.add(new MenuItems(R.drawable.ic_mug, "بطيخ", "10$", R.drawable.ic_favori));
        menuItems.add(new MenuItems(R.drawable.ic_mug, "بطيخ بالنعناع", "10$", R.drawable.ic_favori));
        menuItems.add(new MenuItems(R.drawable.ic_mug, "توت ازرق", "10$", R.drawable.ic_favori));
        menuItems.add(new MenuItems(R.drawable.ic_mug, "توت احمر", "10$", R.drawable.ic_favori));
        menuItems.add(new MenuItems(R.drawable.ic_mug, "فراوله بالنعناع", "10$", R.drawable.ic_favori));
        menuItems.add(new MenuItems(R.drawable.ic_mug, "كيوى بالنعناع", "10$", R.drawable.ic_favori));
        menuItems.add(new MenuItems(R.drawable.ic_mug, "كنتالوب", "10$", R.drawable.ic_favori));
        menuItems.add(new MenuItems(R.drawable.ic_mug, "tea", "10$", R.drawable.ic_favori));
        menuItems.add(new MenuItems(R.drawable.ic_mug, "tea", "10$", R.drawable.ic_favori));

    }

    public void changeItem(int position, int icon) {
        String p = menuItems.get(position).getPrice();
        String su = p.substring(0, 2);
        int x = Integer.parseInt(su);
        if (icon == R.drawable.ic_favori) {
            menuItems.get(position).changlove(R.drawable.ic_favorite_black_24dp);
            qqq = qqq + x;
            saveData(qqq);
            Toast.makeText(getActivity(), qqq + "", Toast.LENGTH_LONG).show();
            mAdapter.notifyItemChanged(position);
        } else if (icon == R.drawable.ic_favorite_black_24dp) {
            menuItems.get(position).changlove(R.drawable.ic_favori);
            qqq = qqq - x;
            if (qqq <= 0) {
                qqq = 0;
                saveData(qqq);
                Toast.makeText(getActivity(), qqq + "ooo", Toast.LENGTH_LONG).show();
            } else {
                saveData(qqq);
                Toast.makeText(getActivity(), qqq + "nn", Toast.LENGTH_LONG).show();
            }

            mAdapter.notifyItemChanged(position);

        }
    }

    public void saveData(int qq) {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("sharec", MODE_PRIVATE);
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
