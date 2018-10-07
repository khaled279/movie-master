package com.MSP.moviesapp;

import android.app.usage.UsageEvents;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.EventLog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    QueryUtls queryUtls = new QueryUtls();
    MovieDetails movieDetails = new MovieDetails("lol" , "lol Again ",0,0) ;
   String url1 = "https://api.themoviedb.org/3/movie/550?api_key=d41f1774744fae16950ee66bfbd67399\n" ;


    private class thisDoesEveryThing extends AsyncTask<String, String, Object> {

        @Override
        public Object doInBackground(String... strings) {
            URL url = null;
            try {
                url = new URL(url1);

            } catch (MalformedURLException e) {
                e.printStackTrace();
            }

            String jr = "";

            try {
                jr = queryUtls.makeHttpRequest(url);
            } catch (IOException e) {
                e.printStackTrace();
            }

            movieDetails = queryUtls.jsonparser(jr);
            return movieDetails;


        } }


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            thisDoesEveryThing nice = new thisDoesEveryThing() ;
            nice.execute();

            final ArrayList<MovieDetails> movies = new ArrayList<>();
            movies.add(new MovieDetails(movieDetails.getName(), movieDetails.getDes(), R.drawable.avengers, R.drawable.avengers_small));
            movies.add(new MovieDetails("Deadpool 2 (2018)",
                    getString(R.string.deadpool), R.drawable.deadpool, R.drawable.deadpool_small));
            movies.add(new MovieDetails("The Meg (2018)",
                    getString(R.string.themeg), R.drawable.themeg, R.drawable.themeg_small));
            movies.add(new MovieDetails("Mission: Impossible - Fallout (2018)",
                    getString(R.string.missionimpossible), R.drawable.missionimpossible, R.drawable.missionimpossible_small));
            movies.add(new MovieDetails("Black Panther (2018)",
                    getString(R.string.blackpanther), R.drawable.blackpanther, R.drawable.blackpanther_small));
            movies.add(new MovieDetails("Ant-Man and the Wasp (2018)",
                    getString(R.string.antman), R.drawable.antman, R.drawable.antman_small));

            ListView listview = (ListView) findViewById(R.id.listview);
            listview.setAdapter(new MyAdapter(movies));
            listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    MovieDetails selectedMovie = movies.get(i);
                    Intent intent = new Intent(getApplicationContext(), MovieDetailsActivity.class);

                    intent.putExtra("movie", selectedMovie);
                /*
                Bundle bundle = new Bundle();
                bundle.putString("name", selectedMovie.getName());
                bundle.putString("des", selectedMovie.getDes());
                bundle.putInt("imgId", selectedMovie.getImgId());

                intent.putExtras(bundle);
                */
                    startActivity(intent);
                }
            });
        }


        public class MyAdapter extends BaseAdapter {

            private ArrayList<MovieDetails> movies;

            public MyAdapter(ArrayList<MovieDetails> movies) {
                this.movies = movies;
            }

            @Override
            public int getCount() {
                return movies.size();
            }

            @Override
            public MovieDetails getItem(int i) {
                return movies.get(i);
            }

            @Override
            public long getItemId(int i) {
                return i;
            }

            @Override
            public View getView(int i, View view, ViewGroup viewGroup) {
                LayoutInflater inflater = getLayoutInflater();
                view = inflater.inflate(R.layout.list_item, null);

                ImageView img = view.findViewById(R.id.img);
                TextView txtName = view.findViewById(R.id.txtname);

                MovieDetails currentMovie = getItem(i);
                img.setImageResource(currentMovie.getsImgId());
                txtName.setText(currentMovie.getName());

                return view;
            }
        }

}
