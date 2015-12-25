package com.chhavi.popularmovies;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    MovieAdapter movieAdapter;
    ArrayList<Result> results;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final GridView gridView = (GridView)findViewById(R.id.movies_grid);


        if(savedInstanceState == null ||!savedInstanceState.containsKey("movies")) {
            String popular_movie_url = getResources().getString(R.string.BASE_MOVIE_URL) + getResources().getString(R.string.popularity_citerion) + "&api_key=" + getResources().getString(R.string.API_KEY);
            String rating_movie_url = getResources().getString(R.string.BASE_MOVIE_URL) + getResources().getString(R.string.rating_criterion) + "&api_key=" + getResources().getString(R.string.API_KEY);
            GsonRequest gsonRequest = new GsonRequest(popular_movie_url, Movie.class, null, new Response.Listener() {
                @Override
                public void onResponse(Object response) {
                    Movie movie = (Movie) response;
                    results  = movie.getResults();
                    Log.e("movie", results.get(0).getTitle());
                    movieAdapter = new MovieAdapter(MainActivity.this, results);
                    gridView.setAdapter(movieAdapter);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();

                }
            });
            VolleyHelper.getInstance(getApplicationContext()).addToRequestQueue(gsonRequest);
        }else{
           results = savedInstanceState.getParcelableArrayList("movies");
            movieAdapter = new MovieAdapter(MainActivity.this, results);
            gridView.setAdapter(movieAdapter);

        }


        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Result clickedMovie = movieAdapter.getItem(position);
                Intent intent = new Intent(MainActivity.this, MovieDetail.class);
                intent.putExtra("movie", clickedMovie);
                startActivity(intent);
            }
        });


    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putParcelableArrayList("movies",results);
        super.onSaveInstanceState(outState);
    }
}
