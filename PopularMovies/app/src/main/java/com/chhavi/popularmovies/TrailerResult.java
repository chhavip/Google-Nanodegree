package com.chhavi.popularmovies;

import java.util.ArrayList;

/**
 * Created by chhavi on 5/2/16.
 */
public class TrailerResult {

    int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<ReviewResult.ReviewResultInner> getResults() {
        return results;
    }

    public void setResults(ArrayList<ReviewResult.ReviewResultInner> results) {
        this.results = results;
    }

    ArrayList<ReviewResult.ReviewResultInner> results;
}
