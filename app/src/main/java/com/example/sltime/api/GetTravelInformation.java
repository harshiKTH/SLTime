package com.example.sltime.api;

import android.os.AsyncTask;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class GetTravelInformation extends AsyncTask<String,String,JSONObject> {


    String apiURl = null;
    JSONObject apiResponse = null;
    public  GetTravelInformation(String apiURl ){
        this.apiURl = apiURl;

        executeOnExecutor(AsyncTask.SERIAL_EXECUTOR);
    }
    @Override
    protected JSONObject doInBackground(String... strings) {
        try {
            URL url = new URL(apiURl);
            URLConnection urlConn = url.openConnection();
            urlConn.setRequestProperty("Method","GET");
            urlConn.connect();
            BufferedReader bfr = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
            String response = bfr.readLine();
            if ( response != null )
                apiResponse = new JSONObject(response);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return apiResponse;
    }

    }

