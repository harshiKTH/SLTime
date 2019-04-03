package com.example.sltime.view;

import android.app.Dialog;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.example.sltime.R;
import com.example.sltime.adapters.RouteInformationAdapter;
import com.example.sltime.adapters.ViewPagerAdapter;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Main2Activity extends AppCompatActivity implements NumberPicker.OnValueChangeListener {
    SwipeRefreshLayout swipeRefreshLayout;
    private final String APIKEY_2 = "c58553c52e3546b3b8e7551ccdbf158d";
    private JSONObject apiResponseCallB;
    private ArrayList<String> arrList;
    private boolean bus, metro,train;
    private String siteId = null;
    private RouteInformationAdapter ria;
    private String apiURl;
    private int searchMin = 5;
    Calendar calendar = Calendar.getInstance();
    Date date;
    Button btnSubway ;
    Button btnBuss ;
    Button btnPendel;

    int btnColorActive, btnColorDefault;

private ViewPager viewPager;
private TabLayout mTabLayout;
private ViewPagerAdapter viewPagerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        mTabLayout = findViewById(R.id.tabs);
        viewPager = findViewById(R.id.container);
        setupViewPager(viewPager);

       TabLayout tabLayout = findViewById(R.id.tabs);

tabLayout.addTab(tabLayout.newTab().setText("bus").setIcon(R.drawable.ic_directions_bus_black_24dp));
        tabLayout.setupWithViewPager(viewPager);



    }
    private void setupViewPager(ViewPager viewPager){
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragment(new BusFragment(),"TRAIN");
        viewPagerAdapter.addFragment(new TrainFragment(),"BUSS");
        viewPagerAdapter.addFragment(new PendaltagFragment(),".......");
        viewPager.setAdapter(viewPagerAdapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.timepicker, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            case R.id.myicon:
                final Dialog d = new Dialog(Main2Activity.this);
                d.setTitle("Välj Tid");

                d.setContentView(R.layout.layout_alert);

                Button btnOk = (Button) d.findViewById(R.id.buttonok);
                Button btnCancel = (Button) d.findViewById(R.id.buttonCancel);

                final NumberPicker np = (NumberPicker) d.findViewById(R.id.numberPicker);
                final String[] displayedValues = new String[11];
                int start = 0;
                for (int i = 0; i < displayedValues.length; i++) {
                    start = start + 5;
                    displayedValues[i] = Integer.toString(start);


                }


                np.setMaxValue(10); // max value 10
                np.setMinValue(0);   // min value 0
                np.setDisplayedValues(displayedValues);
                np.setWrapSelectorWheel(false);
                np.setOnValueChangedListener(this);


                np.setOnValueChangedListener((new NumberPicker.
                        OnValueChangeListener() {
                    public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                        searchMin = Integer.decode(displayedValues[picker.getValue()]);
                        System.out.println("Item clicked ............... value " + searchMin);

                    }
                }));
                btnOk.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    //    getRouteInformation();
                       // updateInfo();
                        d.dismiss();
                    }
                });
                btnCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        d.dismiss();
                    }
                });

                d.show();
        }
        return true;

    }

    @Override
    public void onValueChange(NumberPicker picker, int oldVal, int newVal) {

    }



}
