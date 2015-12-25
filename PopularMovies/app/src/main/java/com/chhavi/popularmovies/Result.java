package com.chhavi.popularmovies;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by chhavi on 25/12/15.
 */
public class Result implements Parcelable {


    protected Result(Parcel in) {
        poster_path = in.readString();
        overview = in.readString();
        original_title = in.readString();
        title = in.readString();
        release_date = in.readString();
        vote_average = in.readFloat();
    }

    public static final Creator<Result> CREATOR = new Creator<Result>() {
        @Override
        public Result createFromParcel(Parcel in) {
            return new Result(in);
        }

        @Override
        public Result[] newArray(int size) {
            return new Result[size];
        }
    };

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
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

    private String poster_path;
    private String overview;
    private String original_title;

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

    private String release_date;
    private float vote_average;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    private String title;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(poster_path);
        dest.writeString(overview);
        dest.writeString(original_title);
        dest.writeString(title);
        dest.writeString(release_date);
        dest.writeFloat(vote_average);
    }
}