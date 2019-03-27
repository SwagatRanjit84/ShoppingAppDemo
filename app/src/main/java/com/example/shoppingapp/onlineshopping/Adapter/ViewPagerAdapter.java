package com.example.shoppingapp.onlineshopping.Adapter;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.shoppingapp.onlineshopping.fragment.FragmentOne;
import com.example.shoppingapp.onlineshopping.fragment.FragmentThree;
import com.example.shoppingapp.onlineshopping.fragment.FragmentTwo;

/**
 * Created by shoppingapp on 10/4/16.
 */

public class ViewPagerAdapter extends FragmentPagerAdapter {
    int mNumOfTabs;

    public ViewPagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs= NumOfTabs;


    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
            FragmentOne tab1=new FragmentOne();
                return tab1;
            case 1:
                FragmentTwo tab2=new FragmentTwo();
                return tab2;
            case 2:
                FragmentThree tab3=new FragmentThree();
                return tab3;
            default:
                return null;

        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
