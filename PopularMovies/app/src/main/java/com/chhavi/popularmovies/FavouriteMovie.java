package com.chhavi.popularmovies;

import com.orm.SugarRecord;

import java.util.ArrayList;

/**
 * Created by chhavi on 6/2/16.
 */
public class FavouriteMovie extends SugarRecord {

    public FavouriteMovie() {
    }
    public FavouriteMovie(String release_date, String overview, float vote_average, String title, ArrayList<ReviewResult.ReviewResultInner> reviews, int movie_id, String poster_path) {
        this.release_date = release_date;
        this.overview = overview;
        this.vote_average = vote_average;
        this.title = title;
        this.reviews = reviews;
        this.movie_id = movie_id;
        this.poster_path = poster_path;
    }


    public FavouriteMovie(String overview, String original_title, String release_date, float vote_average, String title, ArrayList<ReviewResult.ReviewResultInner> reviews, int movie_id) {
        this.overview = overview;
        this.original_title = original_title;
        this.release_date = release_date;
        this.vote_average = vote_average;
        this.title = title;
        this.reviews = reviews;
        this.movie_id = movie_id;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public float getVote_average() {
        return vote_average;
    }

    public void setVote_average(float vote_average) {
        this.vote_average = vote_average;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<ReviewResult.ReviewResultInner> getReviews() {
        return reviews;
    }

    public void setReviews(ArrayList<ReviewResult.ReviewResultInner> reviews) {
        this.reviews = reviews;
    }

    private String overview;
    private String original_title;

    private String release_date;
    private float vote_average;
    private String title;
    ArrayList<ReviewResult.ReviewResultInner> reviews;

    public int getMovie_id() {
        return movie_id;
    }

    public void setMovie_id(int movie_id) {
        this.movie_id = movie_id;
    }

    private int movie_id;

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    private String poster_path;
}
