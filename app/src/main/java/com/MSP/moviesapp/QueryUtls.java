package com.MSP.moviesapp;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.JsonReader;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class QueryUtls {
    String  name;
    String overView ;
    String keepImageUrl  ;
    String addToImageUrl = "https://image.tmdb.org/t/p/w600_and_h900_bestv2/" ;
    String url1 ;



    protected void getData() {

       url1= "https://api.themoviedb.org/3/movie/550?api_key=d41f1774744fae16950ee66bfbd67399\n";
        URL url = null;
        JsonReader json1 ;
        try {
            url = new URL(url1);

            HttpsURLConnection link = (HttpsURLConnection) url.openConnection();

            InputStream input = link.getInputStream();
            InputStreamReader inputReader = new InputStreamReader(input, "utf-8");

            String getData = inputReader.toString();



            JSONObject root = new JSONObject(getData);

             name = root.getString("original_title");
             overView = root.getString("overview");

//second way of parsing (both not working)
          /*  json1 = new JsonReader(inputReader);
            json1.beginObject();
            while (json1.hasNext()) {
                String key = json1.nextName();
                if (key.equals("original_title")) {
                    name = json1.nextString();
                }
                if(key.equals("overview")){

                    overView = json1.nextString() ;

                }
                if(key.equals("poster_path")){

                    keepImageUrl=addToImageUrl+json1.nextString() ;
                }


                else {
                    json1.skipValue();
                }}*/



} catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


}
