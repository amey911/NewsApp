package com.ez.newsapp.Adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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
import com.ez.newsapp.HeckylModels.HeckylNews;
import com.ez.newsapp.HeckylModels.NewsItems;
import com.ez.newsapp.R;
import com.ez.newsapp.Utils;

import java.util.List;

public class HeckylAdapter extends RecyclerView.Adapter<HeckylAdapter.HeckylViewHolder> {

    private List<NewsItems> newsItems;
    private Context context;
    private static OnItemClickListener onItemClickListener;


    public HeckylAdapter(List<NewsItems> newsItems, Context context) {
        this.newsItems = newsItems;
        this.context = context;
    }

    @NonNull
    @Override
    public HeckylViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
        return new HeckylViewHolder(view, onItemClickListener);

    }

    @Override
    public void onBindViewHolder(@NonNull final HeckylViewHolder holder, int position) {

        final HeckylViewHolder heckylViewHolder = holder;
        NewsItems item = newsItems.get(position);

        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(Utils.getRandomDrawbleColor());
        requestOptions.error(Utils.getRandomDrawbleColor());
        requestOptions.diskCacheStrategy(DiskCacheStrategy.ALL);
        requestOptions.centerCrop();

        Glide.with(context)
                .load(item.getImgUrl())
                .apply(requestOptions)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        holder.progressBar.setVisibility(View.GONE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        holder.progressBar.setVisibility(View.GONE);
                        return false;
                    }
                })
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(holder.imageView);

        holder.title.setText(item.getTitle());
        holder.desc.setText(item.getDescription());
        holder.source.setText(item.getSource());

    }

    @Override
    public int getItemCount() {
        return 0;
    }


    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;

    }


    public interface OnItemClickListener {

         void OnItemClick(View view, int position);
    }


    public class HeckylViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

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

            progressBar = itemView.findViewById(R.id.progress_load_photo);

            this.onItemClickListener = HeckylAdapter.onItemClickListener;

        }

        @Override
        public void onClick(View v) {
            onItemClickListener.OnItemClick(v, getAdapterPosition());

        }
    }

}
