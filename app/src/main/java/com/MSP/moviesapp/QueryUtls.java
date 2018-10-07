package com.MSP.moviesapp;

import android.app.Activity;
import android.app.usage.UsageEvents;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.EventLog;
import android.util.JsonReader;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;
import android.os.AsyncTask;
public  class QueryUtls {
        String  name;
        String overView ;
        String keepImageUrl  ;
        String addToImageUrl = "https://image.tmdb.org/t/p/w600_and_h900_bestv2/" ;
        String url1 ="https://api.themoviedb.org/3/movie/550?api_key=d41f1774744fae16950ee66bfbd67399\n" ;
        String jsonResponse = "" ;
        InputStream is = null ;
        HttpsURLConnection urlConnection = null ;
        MovieDetails movieDetails ;


    public  String makeHttpRequest (URL url1) throws IOException {

       try {
           urlConnection = (HttpsURLConnection) url1.openConnection();
           urlConnection.setRequestMethod("GET");
           urlConnection.setReadTimeout(10000);
           urlConnection.setConnectTimeout(15000);
           urlConnection.connect();
           is = urlConnection.getInputStream();
           jsonResponse = readFromStream(is) ;

       }
       catch (IOException e ){}


       finally {
           if (urlConnection != null) {
               urlConnection.disconnect();
           }
           if (is != null) {
               // function must handle java.io.IOException here
               is.close();
           }



    return jsonResponse ;  }


}
    private String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }

    public MovieDetails jsonparser(String jsonfile){
        try {
            JSONObject json = new JSONObject(jsonfile) ;

            if(json != null){

                name = json.getString("original_title");
                overView = json.getString("overview");
                return new MovieDetails(name,overView,0,0) ;
            }



        }


        catch (JSONException e){}







    return null ;}







}
