package com.ez.newsapp.Adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.DimenRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.ez.newsapp.HeckylActivity;
import com.ez.newsapp.HeckylModels.NewsItems;
import com.ez.newsapp.R;
import com.ez.newsapp.Utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.HeckylViewHolder> {

    private List<NewsItems> mNewsItems;
    private Context context;
    private static OnItemClickListener onItemClickListener;


    public CustomAdapter(List<NewsItems> mNewsItems, Context context) {
        this.mNewsItems = mNewsItems;
        this.context = context;
    }

    @NonNull
    @Override
    public HeckylViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.news_item_heckyl, parent, false);


        return new HeckylViewHolder(v, onItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull HeckylViewHolder holders, int position) {

    final HeckylViewHolder holder = holders;

    NewsItems model = mNewsItems.get(position);


        holder.title.setText(model.getTitle());
        holder.source.setText(model.getSource());
        holder.desc.setText(model.getDescription());

        String unixdate = model.getLastFetch();


        String sentiment = model.getSentiment();


        switch (sentiment) {

            case "0":
//blue
//
                holder.newsItemRel.setBackgroundResource(R.drawable.news_rec_item_neutral);

                break;

            case "1":
//green
//
                holder.newsItemRel.setBackgroundResource(R.drawable.news_rec_item_green);
                break;

            case "2":
//red
//
                holder.newsItemRel.setBackgroundResource(R.drawable.new_srec_item_red);
                break;

        }




    }

    @Override
    public int getItemCount() {
        return mNewsItems.size();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;

    }



    public interface OnItemClickListener {

        void OnItemClick(View view, int position);
    }



    public class HeckylViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{


        TextView title, desc, source, date;
//        ImageView imageView;
//        ProgressBar progressBar;
        RelativeLayout newsItemRel;
        OnItemClickListener onItemClickListener;



        public HeckylViewHolder(@NonNull View itemView, OnItemClickListener onItemClickListener) {
            super(itemView);

//            itemView.setOnClickListener(this);
//            title = itemView.findViewById(R.id.title);
//            desc = itemView.findViewById(R.id.desc);
//            source = itemView.findViewById(R.id.source);
//            author = itemView.findViewById(R.id.author);
//
//            progressBar = itemView.findViewById(R.id.progress_load_photo);


            itemView.setOnClickListener(this);

            title = itemView.findViewById(R.id.news_item_title);
            desc = itemView.findViewById(R.id.news_item_desc);
            source = itemView.findViewById(R.id.news_item_src);
            date = itemView.findViewById(R.id.news_item_date);
            newsItemRel = itemView.findViewById(R.id.news_item_relative);



            this.onItemClickListener = CustomAdapter.onItemClickListener;

        }

        @Override
        public void onClick(View v) {
            onItemClickListener.OnItemClick(v, getAdapterPosition());
        }
    }


}
