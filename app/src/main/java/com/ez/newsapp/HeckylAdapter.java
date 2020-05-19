package com.ez.newsapp;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

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
import com.ez.newsapp.HeckylModels.NewsItems;

import java.util.List;

public class HeckylAdapter extends RecyclerView.Adapter<HeckylAdapter.HeckylViewHolder> {

    private List<NewsItems> newsItems;
    private Context heckylContext;
    private static OnItemClickListener onItemClickListener;


    public HeckylAdapter(List<NewsItems> newsItems, Context heckylContext) {
        this.newsItems = newsItems;
        this.heckylContext = heckylContext;
    }

    @NonNull
    @Override
    public HeckylViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

    View hView = LayoutInflater.from(heckylContext).inflate(R.layout.item, parent, false);

        return new HeckylViewHolder(hView, onItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull HeckylViewHolder heckylHolders, int position) {

        final HeckylViewHolder heckylHolder = heckylHolders;

        NewsItems heckylModel = newsItems.get(position);

        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(Utils.getRandomDrawbleColor());
        requestOptions.error(Utils.getRandomDrawbleColor());
        requestOptions.diskCacheStrategy(DiskCacheStrategy.ALL);
        requestOptions.centerCrop();

        Glide.with(heckylContext)
                .load(heckylModel.getImgUrl())
                .apply(requestOptions)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        heckylHolder.progressBar.setVisibility(View.GONE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        heckylHolder.progressBar.setVisibility(View.GONE);
                        return false;
                    }
                })
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(heckylHolder.imageView);

        heckylHolder.title.setText(heckylModel.getTitle());
        heckylHolder.desc.setText(heckylModel.getDescription());
        heckylHolder.source.setText(heckylModel.getSource());




}




    @Override
    public int getItemCount() {
        return newsItems.size();
    }


    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemCLick(View view, int position);

    }


    public class HeckylViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView title, desc, source;
        ImageView imageView;
        ProgressBar progressBar;

        OnItemClickListener onItemClickListener;

        public HeckylViewHolder(@NonNull View itemView, OnItemClickListener onItemClickListener) {
            super(itemView);

            itemView.setOnClickListener(this);
            title = itemView.findViewById(R.id.title);
            desc = itemView.findViewById(R.id.desc);
            source = itemView.findViewById(R.id.source);

            this.onItemClickListener = HeckylAdapter.onItemClickListener;
        }

        @Override
        public void onClick(View v) {
            onItemClickListener.onItemCLick(v, getAdapterPosition());
        }
    }

}
