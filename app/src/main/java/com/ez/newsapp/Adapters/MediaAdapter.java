package com.ez.newsapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ez.newsapp.HeckylModels.NewsItems;
import com.ez.newsapp.R;

import java.util.List;

public class MediaAdapter extends RecyclerView.Adapter<MediaAdapter.HeckylViewHolder> {

    private List<NewsItems> mNewsItems;
    private Context context;
    private static OnItemClickListener onItemClickListener;


    public MediaAdapter(List<NewsItems> mNewsItems, Context context) {
        this.mNewsItems = mNewsItems;
        this.context = context;
    }

    @NonNull
    @Override
    public HeckylViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.media_item_heckyl, parent, false);


        return new HeckylViewHolder(v, onItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull HeckylViewHolder holders, int position) {

        final HeckylViewHolder holder = holders;

        NewsItems model = mNewsItems.get(position);


        holder.title.setText(model.getTitle());
        holder.name.setText(model.getName());


        String unixdate = model.getLastFetch();

        holder.date.setText(unixdate);



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


        TextView title, name,  date;
        //        ImageView imageView;
//        ProgressBar progressBar;
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

            title = itemView.findViewById(R.id.meida_item_title);
            name = itemView.findViewById(R.id.meida_item_name);
            date = itemView.findViewById(R.id.meida_item_date);



            this.onItemClickListener = onItemClickListener;

        }

        @Override
        public void onClick(View v) {
            onItemClickListener.OnItemClick(v, getAdapterPosition());
        }
    }


}
