package com.chhavi.popularmovies;

import android.os.Parcel;
import android.os.Parcelable;

import com.android.volley.toolbox.StringRequest;

import java.util.ArrayList;

/**
 * Created by chhavi on 24/12/15.
 */
public class Movie implements Parcelable{
    private int page;
    private ArrayList<Result> results;

    protected Movie(Parcel in) {
        page = in.readInt();
     //   Result[] resultsL = results.toArray(Result[]);
         in.readTypedList(results, Result.CREATOR);
       // in.readTypedArray(results.toArray(new Result[results.size()]), Result.CREATOR);
      //  results = in.readArrayList(Result);
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    public Movie(int page, ArrayList<Result> results) {
        this.page = page;
        this.results = results;
    }

    public ArrayList<Result> getResults() {
        return results;
    }

    public void setResults(ArrayList<Result> results) {
        this.results = results;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }



    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(page);
        dest.writeTypedList(results);
    }



}
