package com.example.royal.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.royal.coold;
import com.example.royal.hot;
import com.example.royal.sweet;

public class PageAdapter extends FragmentPagerAdapter {
    private int numoftabs;

    public PageAdapter(@NonNull FragmentManager fm,int numoftabs) {
        super(fm);
        this.numoftabs=numoftabs;
    }



    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){

            case 0:
                return new hot();
            case 1:
                return  new coold();
            case  2:
                return  new sweet();
                default:
                    return null;

        }

    }


    @Override
    public int getItemPosition(@NonNull Object object) {
        return POSITION_NONE ;
    }

    @Override
    public int getCount() {
        return numoftabs;
    }
}
