package com.example.sltime.view;


import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.sltime.R;
import com.example.sltime.api.ApiClient;

import org.json.JSONException;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 * A simple {@link Fragment} subclass.
 */
public class PendaltagFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener{
SwipeRefreshLayout swipeRefreshLayout;
public TextView time;
Context context;
String id=" ";
    public PendaltagFragment() {
        // Required empty public constructor

    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
     View view1 = inflater.inflate(R.layout.fragment_pendaltag, container, false);
     context = getActivity();
         ApiClient apiClient=new ApiClient();
         ArrayList<String> rtl=new ArrayList<String>();
        try {

//            System.out.println(this.getArguments().getChar("c"));
            rtl=apiClient.getRouteInformation(id,1000,"Trains");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        ListView listView =view1.findViewById(R.id.pendaltåg_list);
        ArrayAdapter<String> listofPe = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1
                ,GetStringArray(rtl));
        listView.setAdapter(listofPe);

        return view1;


    }

    public void setId(String id) {
        this.id=id;
    }

    public static String[] GetStringArray(ArrayList<String> arr)
    {

        // declaration and initialise String Array
        String str[] = new String[arr.size()];

        // ArrayList to Array Conversion
        for (int j = 0; j < arr.size(); j++) {

            // Assign each value to String array
            str[j] = arr.get(j);
        }

        return str;
    }

    @Override
    public void onRefresh() {
        swipeRefreshLayout.setOnRefreshListener(this);


    }
}
