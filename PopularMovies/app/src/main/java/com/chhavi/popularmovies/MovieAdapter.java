package com.chhavi.popularmovies;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by chhavi on 24/12/15.
 */
public class MovieAdapter extends ArrayAdapter<Result> {
    Context context;

    public MovieAdapter(Context context, List<Result> objects) {
        super(context, 0, objects);
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // return super.getView(position, convertView, parent);
        Result movie = getItem(position);

        // Adapters recycle views to AdapterViews.
        // If this is a new View object we're getting, then inflate the layout.
        // If not, this view already has the layout inflated from a previous call to getView,
        // and we modify the View widgets as usual.
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.movie_item, parent, false);
        }

        ImageView iconView = (ImageView) convertView.findViewById(R.id.movie_poster);
        String imageUrl = context.getResources().getString(R.string.BASE_IMAGE_URL) + context.getResources().getString(R.string.phone_size)
                + movie.getPoster_path();
        Picasso.with(context).load(imageUrl).into(iconView);
        // iconView.setImageResource(androidFlavor.image);

        TextView movieTitle = (TextView) convertView.findViewById(R.id.movie_title);
        movieTitle.setText(movie.getTitle());

        return convertView;
    }


}
