package com.example.sltime.adapters;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.sltime.view.BusFragment;
import com.example.sltime.view.PendaltagFragment;
import com.example.sltime.view.RoutInformationListFragment;
import com.example.sltime.view.TrainFragment;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdapter extends FragmentPagerAdapter {
   private final List<Fragment>fragmentList = new ArrayList<>();
   private final List<String>fragmentContain = new ArrayList<>();

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public void addFragment(Fragment fm,String contain) {

       fragmentList.add(fm);
       fragmentContain.add(contain);
    }

    @Override
    public Fragment getItem(int position) {
        /*switch(position){
            case 0:
                TrainFragment trainFragment = new TrainFragment();
                return trainFragment;
            case 1:
                BusFragment busFragment = new BusFragment();
                return busFragment;
            case 2:
                PendaltagFragment pendaltagFragment = new PendaltagFragment();
                return pendaltagFragment;
               *//* default:
                    RoutInformationListFragment routInformationListFragment = new RoutInformationListFragment();
                    return routInformationListFragment;*/
        return fragmentList.get(position);


    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
      //  position = position+1;

        //return "Tab"+position;
        return null;
    }

}
