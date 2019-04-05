package com.example.sltime.view;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sltime.R;
import com.example.sltime.adapters.LocationAdapter;
import com.example.sltime.api.ApiClient;
import com.example.sltime.model.Location;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    LocationAdapter locationAdapter;
    private String apiURl = null;
    private JSONObject apiResponseCallA = null;
    private String searchLocationStr=null;
    private final String APIKEY_1 = "e561a9f413074449b04f55c99e099d2b";
    private FloatingActionButton floatingActionButton;

    EditText searchLocation;
    TextView lastSearch;

    ListView listView;
    public static final  String myLocation="savekey";
    SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        locationAdapter = new LocationAdapter(this,R.layout.station_list);
        listView = findViewById(R.id.listViewResult);
        listView.setAdapter(locationAdapter);
        Button myBtn = findViewById(R.id.myButton);
        lastSearch = findViewById(R.id.lastSearch);
        floatingActionButton = findViewById(R.id.fab);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("    Trip Time suggestion");
        }

        searchLocation = findViewById(R.id.searchLocation) ;
        sharedPreferences = this.getSharedPreferences("prefs", Context.MODE_PRIVATE);
        if(sharedPreferences.contains(myLocation)){
            searchLocationStr = sharedPreferences.getString(myLocation,"");
            searchLocation.setText(searchLocationStr);
            lastSearch.setText(getString(R.string.history)+" "+searchLocationStr);
            getRouteInformation();

        }



floatingActionButton.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        dialog();
       
    }
});





        myBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ConnectivityManager cm = (ConnectivityManager) getBaseContext().getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
                boolean isConnected = activeNetwork != null &&
                        activeNetwork.isConnectedOrConnecting();
                if(!isConnected) {
                    Toast.makeText(getApplicationContext(), R.string.internetConection, Toast.LENGTH_SHORT).show();
                } else {

                    locationAdapter.clear();
                    locationAdapter.notifyDataSetChanged();

                    searchLocationStr = searchLocation.getText().toString().trim();
                    SharedPreferences sharedPreferences = getSharedPreferences("prefs", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString(myLocation, searchLocationStr);
                    editor.commit();

                    if(sharedPreferences.contains(myLocation)){
                        searchLocation.setText(sharedPreferences.getString(myLocation,""));
                        lastSearch.setText(getString(R.string.history)+" "+sharedPreferences.getString(myLocation,""));

                    }

                    if (!searchLocationStr.isEmpty()) {
                        getRouteInformation();
                    } else {
                        Toast.makeText(getApplicationContext(), R.string.emptyFild, Toast.LENGTH_SHORT).show();
                    }
                }


            }
        });




        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Location selectedLocation = (Location) listView.getItemAtPosition(position);
                Intent intent =new Intent(MainActivity.this,Main2Activity.class);
                intent.putExtra("siteid",selectedLocation.getSiteId());
                startActivity(intent);
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.setting, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
       return true;
    }

    public void dialog(){
        AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
        AlertDialog.Builder setIcon = builder.setIcon(android.R.drawable.ic_dialog_info);

        builder.setTitle("Choose your feeling");
        final String []itemsId=new String[]{"Happy","Not Happy content","Too slow","Hate"};
        final boolean []checkedItems=new boolean[]{true,true,false,false};
        builder.setMultiChoiceItems(itemsId, checkedItems,new DialogInterface.OnMultiChoiceClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which, boolean ischeck) {
                checkedItems[which]=ischeck;
            }
        });

        builder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int whick) {
                String text="";
                for(int i=0;i<itemsId.length;i++)
                {
                    text+=checkedItems[i]?itemsId[i]+",":"";
                }

                Toast.makeText(MainActivity.this, text, Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
        builder.show();
    }




        public void getRouteInformation(){

        apiURl = "http://api.sl.se/api2/typeahead.json?key="+ APIKEY_1 +"&searchstring=" + searchLocationStr +"&stationsonly=true";
        ApiClient apiClient = new ApiClient();
        apiResponseCallA = apiClient.callUrl(apiURl);
        if (apiResponseCallA != null) {
            try {
                JSONArray sites = apiResponseCallA.getJSONArray("ResponseData");
                if(sites.length() > 0) {
                    for (int i = 0; i < sites.length(); i++) {
                        JSONObject site = sites.getJSONObject(i);
                        Location location = new Location(site.getString("Name"), site.getString("SiteId"));
                        locationAdapter.add(location);
                    }
                } else {
                    Toast.makeText(getApplicationContext(), R.string.noStop, Toast.LENGTH_SHORT).show();
                }



            } catch (JSONException e) {
                System.out.println(e);


            }

        }

    }






}

