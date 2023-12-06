package com.example.realtimesubway.api;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class OpenApi {
    Context context;

    public OpenApi(Context context){
        this.context = context;
    }

    public JSONArray checkTrainInfo(String lineNm) throws IOException, JSONException {
        String format = "json";
        String apiUrl = "http://swopenapi.seoul.go.kr/api/subway";
        String key = "6146654c43686f6a36374d686c4671";
        String service = "realtimePosition";

        StringBuilder urlBuilder = new StringBuilder(apiUrl);
        urlBuilder.append("/" + URLEncoder.encode(key, "UTF-8"));
        urlBuilder.append("/" + URLEncoder.encode(format, "UTF-8"));
        urlBuilder.append("/" + URLEncoder.encode(service, "UTF-8"));
        urlBuilder.append("/" + URLEncoder.encode("1", "UTF-8"));
        urlBuilder.append("/" + URLEncoder.encode("999", "UTF-8"));

        urlBuilder.append("/" + URLEncoder.encode(lineNm, "UTF-8"));

        return parseJson(urlBuilder, service);
    }

    public JSONArray parseJson(StringBuilder urlBuilder, String service) throws IOException, JSONException {
        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");

        BufferedReader rd;
        if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();
        String original = sb.toString();

        JSONObject jsonObject_1 = new JSONObject(original);

        JSONArray jsonArray = null;

        if (jsonObject_1.isNull("realtimePositionList")){
            jsonArray = jsonObject_1.optJSONArray("message");
        }else {

            jsonArray = jsonObject_1.getJSONArray("realtimePositionList");
        }
        return jsonArray;
    }
}
