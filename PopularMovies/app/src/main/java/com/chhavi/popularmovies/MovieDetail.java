package com.chhavi.popularmovies;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;
import com.github.paolorotolo.expandableheightlistview.ExpandableHeightListView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

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

    ArrayList<ReviewResult.ReviewResultInner> trailerResults;
    @Bind(R.id.fab_fav)
    FloatingActionButton fabFav;
    @Bind(R.id.fab_share)
    FloatingActionButton fabShare;
    @Bind(R.id.right_labels)
    FloatingActionsMenu rightLabels;
    @Bind(R.id.view)
    CardView view;
    @Bind(R.id.trailer_head)
    TextView trailerHead;
    @Bind(R.id.trailer_image_view)
    ImageView trailerImageView;
    @Bind(R.id.trailer_text)
    TextView trailerText;
    @Bind(R.id.trailer_details)
    LinearLayout trailerDetails;
    @Bind(R.id.trailer_details_card)
    CardView trailerDetailsCard;
    @Bind(R.id.reviews_head)
    TextView reviewsHead;


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

        adapter = new ReviewListAdapter(MovieDetail.this, reviewResultInners);
        reviewsList.setAdapter(adapter);

        trailerResults = new ArrayList<>();
        addTrailers();

        trailerDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ReviewResult.ReviewResultInner trailer = trailerResults.get(0);
                try {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + trailer.getId()));
                    startActivity(intent);
                } catch (ActivityNotFoundException ex) {
                    Intent intent = new Intent(Intent.ACTION_VIEW,
                            Uri.parse("http://www.youtube.com/watch?v=" + trailer.getId()));
                    startActivity(intent);
                }
            }
        });
    /*    final TrailersAdapter trailersAdapter = new TrailersAdapter(MovieDetail.this, trailerResults);
        trailersexpandableListview.setAdapter(trailersAdapter);*/

/*
        trailersexpandableListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ReviewResult.ReviewResultInner trailer = trailersAdapter.getItem(position);
                try {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + id));
                    startActivity(intent);
                } catch (ActivityNotFoundException ex) {
                    Intent intent = new Intent(Intent.ACTION_VIEW,
                            Uri.parse("http://www.youtube.com/watch?v=" + trailer.getId()));
                    startActivity(intent);
                }
            }
        });*/


        fabFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!movie.isFavourite()) {
                    FavouriteMovie favouriteMovie = new FavouriteMovie(movie.getRelease_date(), movie.getOverview(), movie.getVote_average(), movie.getTitle(), reviewResultInners, movie.getMovie_id(), movie.getPoster_path());
                    favouriteMovie.save();
                    movie.setIsFavourite(true);
                    fabFav.setIcon(R.drawable.ic_star_black_18dp);
                } else {
                    movie.setIsFavourite(false);
                    fabFav.setIcon(R.drawable.ic_star_border_black_18dp);
                    List<FavouriteMovie> favouriteMovie = FavouriteMovie.find(FavouriteMovie.class, "title = ?", movie.getTitle());
                    favouriteMovie.get(0).delete();
                }
            }
        });


        fabShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent share = new Intent(Intent.ACTION_SEND);
                share.setType("text/plain");
                share.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);

                share.putExtra(Intent.EXTRA_SUBJECT, "Watch This Trailer!");
                share.putExtra(Intent.EXTRA_TEXT, "http://www.youtube.com/watch?v=" + trailerResults.get(0).getId());

                startActivity(Intent.createChooser(share, "Share link!"));

            }
        });


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

    public void addReviews() {
        String reviews_url = getResources().getString(R.string.BASE_MOVIE_URL) + movie.getMovie_id() + "/reviews" + "?api_key=" + getResources().getString(R.string.API_KEY);

        GsonRequest gsonRequest = new GsonRequest(reviews_url, ReviewResult.class, null, new Response.Listener() {
            @Override
            public void onResponse(Object response) {
                ReviewResult reviewResult = (ReviewResult) response;
                //  reviewResultInners = reviewResult.getResults();
                for (int i = 0; i < reviewResult.getResults().size(); i++)
                    reviewResultInners.add(reviewResult.getResults().get(i));
                adapter.notifyDataSetChanged();
                if (reviewResultInners.size() == 0) {
                    reviewsList.setVisibility(View.GONE);
                    reviewsHead.setVisibility(View.GONE);
                }else{
                    reviewsList.setMinimumHeight(reviewResultInners.size() * 100);
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("error", error.getMessage());
            }
        });
        VolleyHelper.getInstance(getApplicationContext()).addToRequestQueue(gsonRequest);
    }

    public void addTrailers() {
        final String trailers_result = getResources().getString(R.string.BASE_MOVIE_URL) + movie.getMovie_id() + "/videos" + "?api_key=" + getResources().getString(R.string.API_KEY);

        GsonRequest gsonRequest = new GsonRequest(trailers_result, TrailerResult.class, null, new Response.Listener() {
            @Override
            public void onResponse(Object response) {
                TrailerResult reviewResult = (TrailerResult) response;
                for (int i = 0; i < reviewResult.getResults().size(); i++)
                    trailerResults.add(reviewResult.getResults().get(i));
                if (trailerResults.size() != 0) {
                    trailerText.setText(trailerResults.get(0).getName());
                } else
                    trailerDetailsCard.setVisibility(View.GONE);
                //  adapter.notifyDataSetChanged();
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
