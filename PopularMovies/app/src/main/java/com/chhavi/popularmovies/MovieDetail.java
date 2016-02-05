package com.chhavi.popularmovies;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by chhavi on 25/12/15.
 */
public class MovieDetail extends AppCompatActivity {


    @Bind(R.id.movie_poster)
    ImageView moviePoster;
    @Bind(R.id.movie_title)
    TextView movieTitle;
    @Bind(R.id.release_date)
    TextView releaseDate;
    @Bind(R.id.average_vote)
    TextView averageVote;
    @Bind(R.id.movie_synopsis)
    TextView movieSynopsis;
    @Bind(R.id.release_date_text)
    TextView releaseDateText;
    @Bind(R.id.vote_avg_text)
    TextView voteAvgText;
    @Bind(R.id.reviews_list)
    RecyclerView reviewsList;
    Result movie;
    ReviewListAdapter adapter;
    ArrayList<ReviewResult.ReviewResultInner> reviewResultInners;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_detail);
        ButterKnife.bind(this);
        Intent intent = getIntent();

        movie = intent.getParcelableExtra("movie");
        getSupportActionBar().setTitle(movie.getTitle());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        movieTitle.setText(movie.getTitle());
        String posterUrl = getResources().getString(R.string.BASE_IMAGE_URL) + getResources().getString(R.string.phone_size)
                + movie.getPoster_path();
        Picasso.with(this).load(posterUrl).into(moviePoster);
        movieSynopsis.setText(movie.getOverview());
        averageVote.setText("" + movie.getVote_average() + "/10");
        releaseDate.setText(movie.getRelease_date());

        reviewResultInners = new ArrayList<>();
        addReviews();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MovieDetail.this);
        reviewsList.setLayoutManager(linearLayoutManager);

          adapter  = new ReviewListAdapter(MovieDetail.this, reviewResultInners);
        reviewsList.setAdapter(adapter);



    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void addReviews(){
        String reviews_url =  getResources().getString(R.string.BASE_MOVIE_URL) + movie.getId() + "/reviews" + "?api_key=" + getResources().getString(R.string.API_KEY);

        GsonRequest gsonRequest = new GsonRequest(reviews_url, ReviewResult.class, null, new Response.Listener() {
            @Override
            public void onResponse(Object response) {
                ReviewResult reviewResult = (ReviewResult) response;
                //  reviewResultInners = reviewResult.getResults();
                for(int i=0;i<reviewResult.getResults().size();i ++)
                    reviewResultInners.add(reviewResult.getResults().get(i));
                Log.e("result", reviewResultInners.get(0).getContent());
                adapter.notifyDataSetChanged();




            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("error", error.getMessage());
            }
        });
        VolleyHelper.getInstance(getApplicationContext()).addToRequestQueue(gsonRequest);
    }
}
