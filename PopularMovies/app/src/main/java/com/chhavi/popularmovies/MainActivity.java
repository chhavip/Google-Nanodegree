package com.chhavi.popularmovies;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.GridView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final GridView gridView = (GridView)findViewById(R.id.movies_grid);
        String popular_movie_url = getResources().getString(R.string.BASE_MOVIE_URL) + getResources().getString(R.string.popularity_citerion) + "&api_key=" + getResources().getString(R.string.API_KEY);
        String rating_movie_url = getResources().getString(R.string.BASE_MOVIE_URL) + getResources().getString(R.string.rating_criterion) + "&api_key=" + getResources().getString(R.string.API_KEY);
       // String popular_movies_url = "http://api.themoviedb.org/3/discover/movie?sort_by=popularity.desc&api_key=5534ea3819b464fb51bbf768d19dcf32";
        Log.e("url0",popular_movie_url);
        GsonRequest gsonRequest = new GsonRequest(popular_movie_url, Movie.class, null, new Response.Listener() {
            @Override
            public void onResponse(Object response) {
                Movie movie = (Movie)response;
                ArrayList<Movie.Result> results = movie.getResults();
                MovieAdapter movieAdapter = new MovieAdapter(MainActivity.this, results);
                Log.e("movie",results.get(0).getTitle());

                gridView.setAdapter(movieAdapter);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();

            }
        });
        VolleyHelper.getInstance(getApplicationContext()).addToRequestQueue(gsonRequest);


    }
}
