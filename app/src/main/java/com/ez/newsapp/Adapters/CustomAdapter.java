package com.ez.newsapp.Adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
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
        View v = LayoutInflater.from(context).inflate(R.layout.item, parent, false);


        return new HeckylViewHolder(v, onItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull HeckylViewHolder holders, int position) {

    final HeckylViewHolder holder = holders;

    NewsItems model = mNewsItems.get(position);

        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(Utils.getRandomDrawbleColor());
        requestOptions.error(Utils.getRandomDrawbleColor());
        requestOptions.diskCacheStrategy(DiskCacheStrategy.ALL);
        requestOptions.centerCrop();

        if (model.getImgUrl() != null){

            Glide.with(context)
                    .load(model.getImgUrl())
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



        } else {

        }


            holder.title.setText(model.getTitle());
        holder.desc.setText(model.getDescription());
        holder.source.setText(model.getSource());
        holder.author.setText(model.getName());
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


        TextView title, desc, source, author;
        ImageView imageView;
        ProgressBar progressBar;
        OnItemClickListener onItemClickListener;



        public HeckylViewHolder(@NonNull View itemView, OnItemClickListener onItemClickListener) {
            super(itemView);

            itemView.setOnClickListener(this);
            title = itemView.findViewById(R.id.title);
            desc = itemView.findViewById(R.id.desc);
            source = itemView.findViewById(R.id.source);
            author = itemView.findViewById(R.id.author);

            progressBar = itemView.findViewById(R.id.progress_load_photo);

            this.onItemClickListener = CustomAdapter.onItemClickListener;

        }

        @Override
        public void onClick(View v) {
            onItemClickListener.OnItemClick(v, getAdapterPosition());
        }
    }


}
