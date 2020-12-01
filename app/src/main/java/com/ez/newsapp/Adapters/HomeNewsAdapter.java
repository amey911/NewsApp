package com.ez.newsapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ez.newsapp.HeckylModels.NewsItems;
import com.ez.newsapp.R;

import java.util.List;

public class HomeNewsAdapter extends RecyclerView.Adapter<HomeNewsAdapter.HeckylViewHolder> {

    private List<NewsItems> mNewsItems;
    private Context context;
    private static OnItemClickListener onItemClickListener;


    public HomeNewsAdapter(List<NewsItems> mNewsItems, Context context) {
        this.mNewsItems = mNewsItems;
        this.context = context;
    }

    @NonNull
    @Override
    public HeckylViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.home_news_item, parent, false);


        return new HeckylViewHolder(v, onItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull HeckylViewHolder holders, int position) {

    final HeckylViewHolder holder = holders;

    NewsItems model = mNewsItems.get(position);


        holder.hTitle.setText(model.getTitle());
        holder.hSrc.setText(model.getSource());
        holder.hDesc.setText(model.getDescription());

        String unixdate = model.getLastFetch();



        String sentiment = model.getSentiment();

        switch (sentiment) {

            case "0":
//blue
//                holder.underline.setImageResource(R.drawable.blue_underline);
                holder.homeItemRelLayout.setBackgroundResource(R.drawable.news_rec_item_neutral);

                break;

            case "1":
//green
//                holder.underline.setImageResource(R.drawable.green_underline);
                holder.homeItemRelLayout.setBackgroundResource(R.drawable.news_rec_item_green);
                    break;

            case "2":
//red
//                holder.underline.setImageResource(R.drawable.red_underline);
                holder.homeItemRelLayout.setBackgroundResource(R.drawable.new_srec_item_red);
                break;

        }







//        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
//        long milliSeconds= Long.parseLong(unixdate);
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTimeInMillis(milliSeconds);
//        String formattedTime =formatter.format(calendar.getTime());





//
//        unixdate.replaceAll("/", "");
//        unixdate.replaceAll("Date", "");
////        unixdate.replaceAll(+ ,"");
////        unixdate.replaceAll(( ,"");
//
//        long unixSeconds = Long.parseLong(unixdate);
//// convert seconds to milliseconds
//        Date date = new java.util.Date(unixSeconds*1000L);
//// the format of your date
//        SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
//// give a timezone reference for formatting (see comment at the bottom)
//        sdf.setTimeZone(java.util.TimeZone.getTimeZone("GMT-4"));
//        String formattedDate = sdf.format(date);
//
//
//
//        holder.date.setText(formattedDate);

//
//        RequestOptions requestOptions = new RequestOptions();
//        requestOptions.placeholder(Utils.getRandomDrawbleColor());
//        requestOptions.error(Utils.getRandomDrawbleColor());
//        requestOptions.diskCacheStrategy(DiskCacheStrategy.ALL);
//        requestOptions.centerCrop();

//        if (model.getImgUrl() != null){
//
//            Glide.with(context)
//                    .load(model.getImgUrl())
//                    .apply(requestOptions)
//                    .listener(new RequestListener<Drawable>() {
//                        @Override
//                        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
//                            holder.progressBar.setVisibility(View.GONE);
//                            return false;
//                        }
//
//                        @Override
//                        public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
//                            holder.progressBar.setVisibility(View.GONE);
//                            return false;
//                        }
//                    })
//                    .transition(DrawableTransitionOptions.withCrossFade())
//                    .into(holder.imageView);
//
//
//
//        } else {
//
//        }

//
//            holder.title.setText(model.getTitle());
//        holder.desc.setText(model.getDescription());
//        holder.source.setText(model.getSource());
//        holder.author.setText(model.getName());


//        holder.date.setText(model.getLastFetch());
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


        TextView hTitle, hDesc, hSrc, hDate;

        ImageView underline;

        RelativeLayout homeItemRelLayout;

        OnItemClickListener onItemClickListener;



        public HeckylViewHolder(@NonNull View itemView, OnItemClickListener onItemClickListener) {
            super(itemView);



            hTitle = itemView.findViewById(R.id.home_news_item_title);
            hSrc = itemView.findViewById(R.id.home_news_item_src);
            hDesc = itemView.findViewById(R.id.home_news_item_desc);
            homeItemRelLayout = itemView.findViewById(R.id.news_item_home_rel_layout);




            itemView.setOnClickListener(this);



            this.onItemClickListener = HomeNewsAdapter.onItemClickListener;

        }

        @Override
        public void onClick(View v) {
            onItemClickListener.OnItemClick(v, getAdapterPosition());
        }
    }


}
