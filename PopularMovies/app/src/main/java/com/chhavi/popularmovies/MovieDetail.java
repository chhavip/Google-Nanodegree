package com.chhavi.popularmovies;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_detail);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        Result movie = intent.getParcelableExtra("movie");
        Log.e("move", movie.getTitle());
        movieTitle.setText(movie.getTitle());
        String posterUrl = getResources().getString(R.string.BASE_IMAGE_URL) + getResources().getString(R.string.phone_size)
                + movie.getPoster_path();
        Picasso.with(this).load(posterUrl).into(moviePoster);
        movieSynopsis.setText(movie.getOverview());
        averageVote.setText(""+movie.getVote_average());
        releaseDate.setText(movie.getRelease_date());

    }
}
