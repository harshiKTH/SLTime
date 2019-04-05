package com.example.sltime.adapters;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.sltime.view.BusFragment;
import com.example.sltime.view.PendaltagFragment;
import com.example.sltime.view.TrainFragment;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdapter extends FragmentPagerAdapter {
   private final List<Fragment>fragmentList = new ArrayList<>();
   private final List<String>tabTitel = new ArrayList<>();
  // private final List<Integer>image = new ArrayList<>();
    private Intent intent;

    public ViewPagerAdapter(FragmentManager fm, Intent intent) {

        super(fm);
        this.intent=intent;
    }




    public void addFragment(Fragment fm,String contain) {
        fragmentList.add(fm);
        tabTitel.add(contain);

    }

    @Override
    public Fragment getItem(int position) {
        String id=intent.getStringExtra("siteid");
        switch (position) {
            case 0:
                TrainFragment trainFragment = new TrainFragment();
              //trainFragment.setArguments(id);
                trainFragment.setId(id);
                System.out.println("train");
                return trainFragment;
            case 1:
                BusFragment busFragment = new BusFragment();
                System.out.println("buss");
                busFragment.setId(id);
                return busFragment;
            case 2:
                PendaltagFragment pendaltagFragment = new PendaltagFragment();
                System.out.println("pendelll");
                pendaltagFragment.setId(id);
                return pendaltagFragment;

        }
 return null;
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitel.get(position);
    }

}
