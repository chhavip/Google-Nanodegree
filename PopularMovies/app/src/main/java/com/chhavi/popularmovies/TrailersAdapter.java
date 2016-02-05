package com.chhavi.popularmovies;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.List;

/**
 * Created by chhavi on 5/2/16.
 */
public class TrailersAdapter extends ArrayAdapter<ReviewResult.ReviewResultInner> {
    Context context;
    public TrailersAdapter(Context context, int resource, List<ReviewResult.ReviewResultInner> objects) {
        super(context, resource, objects);
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return super.getView(position, convertView, parent);
    }
}
