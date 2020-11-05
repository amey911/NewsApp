package com.ez.newsapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.ez.newsapp.HeckylModels.EntitySentiments;
import com.ez.newsapp.R;

import java.util.List;

public class SentimentsSwipeAdapter extends PagerAdapter {
//    private List<NewsItems> swipeItems;
//    private LayoutInflater layoutInflater;
//    private Context context;

    private List<EntitySentiments> entitySentiments;
    private LayoutInflater layoutInflater;
    private Context context;


    public SentimentsSwipeAdapter(List<EntitySentiments> entitySentiments, Context context) {
        this.entitySentiments = entitySentiments;
        this.context = context;
    }


    @Override
    public int getCount() {
        return entitySentiments.size();
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



        TextView sTitle, sDesc, sDate;

        sTitle = view.findViewById(R.id.swipe_item_title);
        sDesc = view.findViewById(R.id.swipe_item_desc);
        sDate = view.findViewById(R.id.swipe_item_date);


        sTitle.setText(entitySentiments.get(position).getEntitiyCode());
        sDesc.setText(entitySentiments.get(position).getEntityType());
        sDate.setText(entitySentiments.get(position).getPositiveNegative());

        container.addView(view, 0);


        return view;
    }
}
