package com.example.sinelnikovserhii.sharesurl;

import android.widget.ImageButton;

import com.google.gson.JsonObject;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Converter;

import static com.example.sinelnikovserhii.sharesurl.GraphAPI.retrofit;

/**
 * Created by sinelnikovserhii on 04.05.17.
 */

public class ShareService {

     SharesAPI sharesAPI = SharesAPI.retrofit.create(SharesAPI.class);
     GraphAPI graphAPI = retrofit.create(GraphAPI.class);

      public Call<JsonObject> getShares(String[] indexes) {
        return sharesAPI.getData(formQuery(indexes), getDataQueryMap());
      }

      public Call<JsonObject> getGraph(String index){
          return graphAPI.getData(getDataFromGraph(index));

      }

    public String formQuery(String[] indexes) {
        StringBuilder builder = new StringBuilder();
        builder.append("select * from yahoo.finance.quotes where symbol in (");
        for (int i = 0; i < indexes.length; i++) {
            builder.append("\"");
            builder.append(indexes[i]);
            builder.append("\",");
        }
        builder.deleteCharAt(builder.length() - 1);
        builder.append(")");
        return  builder.toString();
    }

    public static Map<String, String> getDataQueryMap() {
        HashMap<String, String> queryMap = new HashMap<String, String>();
        queryMap.put("format", "json");
        queryMap.put("diagnostics", "true");
        queryMap.put("env", "store://datatables.org/alltableswithkeys");
        queryMap.put("callback", "");
        return queryMap;
    }


    public static Map<String,String> getDataFromGraph(String index){
        Map<String,String> queryMap = new HashMap<>();
        queryMap.put("function","TIME_SERIES_MONTHLY");
        queryMap.put("symbol", index);
        queryMap.put("apikey","BTY4W61GBKG5L897");
        return queryMap;
    }

}