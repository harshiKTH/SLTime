package com.example.sltime.api;
import android.os.AsyncTask;

import com.example.sltime.model.Location;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;


public class ApiClient extends AsyncTask {
    private String apiURl = "";
    private String[] apiResponse = null;
    private JSONObject apiResponseCallA = null;
    private String searchLocationStr="";
    private final String APIKEY_1 = "e561a9f413074449b04f55c99e099d2b";
    private final String APIKEY_2 = "c58553c52e3546b3b8e7551ccdbf158d";

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


    public ArrayList<String> getRouteInformation(String siteId, int searchMin, String trasportType) throws JSONException {
        apiURl = "http://api.sl.se/api2/realtimedeparturesv4.json?key=" + APIKEY_2 + "&siteid=" + siteId + "&timewindow=" + searchMin;
        System.out.println(" API URL : " + apiURl);
        ApiClient apiClient = new ApiClient();
        JSONObject apiResponseCallB = apiClient.callUrl(apiURl);
        ArrayList<String> arrList=new ArrayList();
        int lineNo=1;
        if (apiResponseCallB != null) {

            System.out.println("Not null");
                JSONObject realTidInfoJObject = apiResponseCallB.getJSONObject("ResponseData");
                Iterator<String> isr = realTidInfoJObject.keys();
                while (isr.hasNext()) {
                    System.out.println("Iterating");
                    String trasType;
                    trasType = isr.next();
                    if (trasType.equalsIgnoreCase(trasportType)) {
                        JSONArray lineInfoJArr = realTidInfoJObject.getJSONArray(trasportType);
                        for (int l = 0; l < lineInfoJArr.length(); l++) {

                            JSONObject tempObj = lineInfoJArr.getJSONObject(l);
                            System.out.println(tempObj.get("DisplayTime"));
                            arrList.add(tempObj.get("DisplayTime") + "     mot " + tempObj.get("Destination") +
                                    "\n" + tempObj.get("TransportMode")
                                    + " : " + tempObj.get("LineNumber"));
                            lineNo++;

                        }
                    }
                }
            }

            if(arrList.isEmpty()){
                arrList.add("Det finns inga avgånger inom den angivna tidsramen...." + trasportType);

            }
            return arrList;


        }


    @Override
    protected Object doInBackground(Object[] objects) {
        return null;
    }
}


