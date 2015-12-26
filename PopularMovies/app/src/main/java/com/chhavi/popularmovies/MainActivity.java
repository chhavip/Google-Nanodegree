package com.chhavi.popularmovies;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    MovieAdapter movieAdapter;
    String popular_movie_url;
    ArrayList<Result> results;
    String rating_movie_url;
    Movie movie;
    GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gridView = (GridView) findViewById(R.id.movies_grid);


        if (savedInstanceState == null || !savedInstanceState.containsKey("movies")) {
            popular_movie_url = getResources().getString(R.string.BASE_MOVIE_URL) + getResources().getString(R.string.popularity_citerion) + "?api_key=" + getResources().getString(R.string.API_KEY);
            rating_movie_url = getResources().getString(R.string.BASE_MOVIE_URL) + getResources().getString(R.string.rating_criterion) + "?api_key=" + getResources().getString(R.string.API_KEY);
            GsonRequest gsonRequest = new GsonRequest(popular_movie_url, Movie.class, null, new Response.Listener() {
                @Override
                public void onResponse(Object response) {
                    movie = (Movie) response;
                    results = movie.getResults();
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
        } else {
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
    public boolean onCreateOptionsMenu(Menu menu) {
        //   return super.onCreateOptionsMenu(menu);
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_activity_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //return super.onOptionsItemSelected(item);
        if (item.getItemId() == R.id.menu_sort) {
            new MaterialDialog.Builder(this)
                    .title("Sort By")
                    .items(R.array.sort_options)
                    .itemsCallbackSingleChoice(-1, new MaterialDialog.ListCallbackSingleChoice() {
                        @Override
                        public boolean onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {
                            String req_url;
                            switch (which) {
                                case 0:
                                    req_url = popular_movie_url;
                                    break;
                                case 1:
                                    req_url = rating_movie_url;
                                    break;
                                default:
                                    req_url = popular_movie_url;
                            }
                            GsonRequest gsonRequest = new GsonRequest(req_url, Movie.class, null, new Response.Listener() {
                                @Override
                                public void onResponse(Object response) {
                                    movie = (Movie) response;
                                    results = movie.getResults();
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
                            return true;
                        }
                    })
                    .positiveText("Apply")
                    .show();

        }
        return true;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putParcelableArrayList("movies", results);
        super.onSaveInstanceState(outState);
    }
}
