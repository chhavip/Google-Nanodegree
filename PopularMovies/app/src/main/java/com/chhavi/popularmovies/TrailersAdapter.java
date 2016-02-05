package com.chhavi.popularmovies;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by chhavi on 5/2/16.
 */
public class TrailersAdapter extends ArrayAdapter<ReviewResult.ReviewResultInner> {
    Context context;
    List<ReviewResult.ReviewResultInner> trailers;
    public TrailersAdapter(Context context, List<ReviewResult.ReviewResultInner> objects) {
        super(context, 0, objects);
        this.context = context;
        this.trailers = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.review_card_layout, parent, false);
        }
        TextView trailerText = (TextView)convertView.findViewById(R.id.review_text);
        trailerText.setText(trailers.get(position).getId());
        return  convertView;
    }

    @Override
    public int getCount() {
        return trailers.size();
    }
}
