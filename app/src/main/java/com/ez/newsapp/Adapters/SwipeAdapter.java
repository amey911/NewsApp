package com.ez.newsapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.ez.newsapp.HeckylModels.NewsItems;
import com.ez.newsapp.R;

import java.util.List;

public class SwipeAdapter extends PagerAdapter {

private List<NewsItems> swipeItems;
private LayoutInflater layoutInflater;
private Context context;

    public SwipeAdapter(List<NewsItems> swipeItems, Context context) {
        this.swipeItems = swipeItems;
        this.context = context;
    }

    @Override
    public int getCount() {
        return swipeItems.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = layoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.swipe_item, container, false);

        ImageView sImageView;
        RelativeLayout swipeRelative;
        TextView sTitle, sSrc, sDate;

        sTitle = view.findViewById(R.id.swipe_item_title);
        sSrc = view.findViewById(R.id.swipe_item_desc);
        sDate = view.findViewById(R.id.swipe_item_date);

        swipeRelative = view.findViewById(R.id.swipe_item_relative_layout);


        String sentiment = swipeItems.get(position).getSentiment();


        switch (sentiment) {

            case "0":
//blue

//                swipeRelative.setBackgroundColor(R.drawable.swipe_background_blue);
                swipeRelative.setBackgroundResource(R.drawable.swipe_sentiment_neutral);

                break;

            case "1":
//green
//                swipeRelative.setBackgroundColor(R.drawable.swipe_background_green);
                swipeRelative.setBackgroundResource(R.drawable.swipe_sentiment_positive);

                break;

            case "2":
//red
//                swipeRelative.setBackgroundColor(R.drawable.swipe_background_red);
                swipeRelative.setBackgroundResource(R.drawable.swipe_sentiment_negative);

                break;

        }




        sTitle.setText(swipeItems.get(position).getTitle());
        sSrc.setText(swipeItems.get(position).getSource());
        sDate.setText(swipeItems.get(position).getLastFetch());



        container.addView(view, 0);


        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
//        super.destroyItem(container, position, object);
    }
}
