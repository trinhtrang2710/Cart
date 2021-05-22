package com.example.ecommerceapp;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpHandler {
    private  static final String TAG = HttpHandler.class.getSimpleName();

    public HttpHandler() {
    }
    public  String convertStreamToString(String reqUrl){
        String respone = null;
        try {
            URL url = new URL(reqUrl);
            HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();
            BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            StringBuffer sb = new StringBuffer();
            String line="";
            while ((line=br.readLine()) != null){
                sb.append(line);
            }
            sb.toString();
            urlConnection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  respone;
    }
}
