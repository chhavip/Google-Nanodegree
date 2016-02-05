package com.chhavi.popularmovies;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chhavi on 5/2/16.
 */
public class ReviewListAdapter extends RecyclerView.Adapter<ReviewListAdapter.ViewHolder> {

    private Context context;
    List<ReviewResult.ReviewResultInner> resultList;

    public ReviewListAdapter(Context context, ArrayList<ReviewResult.ReviewResultInner> list){
        this.context = context;
        resultList = list;
    }

    @Override
    public ReviewListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.review_card_layout, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ReviewListAdapter.ViewHolder holder, int position) {
        holder.review.setText(resultList.get(position).getContent());
        Log.e("content", resultList.get(position).getContent());

    }

    @Override
    public int getItemCount() {
        return resultList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView review;
    /*    TextView date;
        CardView card;
        TextView time;*/
        public ViewHolder(View itemView) {
            super(itemView);
            review = (TextView)itemView.findViewById(R.id.review_text);
           /* card = (CardView)itemView.findViewById(R.id.card_view);
            date = (TextView)itemView.findViewById(R.id.date3);
            time = (TextView)itemView.findViewById(R.id.time3);*/
        }
    }
}
