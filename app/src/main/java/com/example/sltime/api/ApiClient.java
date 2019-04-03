package com.example.sltime.api;

import org.json.JSONObject;

public class ApiClient {
    public JSONObject callUrl(String apiURl){
        JSONObject apiResponse = null;
        GetTravelInformation gti = new GetTravelInformation(apiURl);
        //gti.executeOnExecutor(AsyncTask.SERIAL_EXECUTOR);
        try {
            apiResponse = gti.get();
        }catch(Exception e)
        {
            System.out.println(e.getMessage());
            apiResponse = null;
        }

        return apiResponse;
    }


}
