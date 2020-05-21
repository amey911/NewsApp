//package com.ez.newsapp.Activities;
//
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.recyclerview.widget.DefaultItemAnimator;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import android.os.Bundle;
//import android.widget.Adapter;
//import android.widget.Toast;
//
//import com.ez.newsapp.Adapters.HeckylAdapter;
//import com.ez.newsapp.HeckylApi.HeckylApiClient;
//import com.ez.newsapp.HeckylApi.HeckylInterface;
//import com.ez.newsapp.HeckylModels.HeckylNews;
//import com.ez.newsapp.HeckylModels.NewsItems;
//import com.ez.newsapp.R;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Response;
//import retrofit2.Retrofit;
//import retrofit2.converter.gson.GsonConverterFactory;
//
//public class ExampleMain extends AppCompatActivity {
//
//    private RecyclerView recyclerView;
//    private RecyclerView.LayoutManager layoutManager;
//    private List<NewsItems> newsItems = new ArrayList<>();
//    private HeckylAdapter adapter;
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_example_main);
//
//        recyclerView = findViewById(R.id.heckyl_rec_view);
//        layoutManager = new LinearLayoutManager(ExampleMain.this);
//        recyclerView.setLayoutManager(layoutManager);
//        recyclerView.setItemAnimator(new DefaultItemAnimator());
//        recyclerView.setNestedScrollingEnabled(false);
//
//
////
//        LoadNews();
//
//
////        Retrofit retrofit = new Retrofit.Builder()
////                .baseUrl("http://216.185.108.228/Find1Svc/API/JSON/News.svc/")
////                .addConverterFactory(GsonConverterFactory.create())
////                .build();
////
////    HeckylInterface heckylInterface = retrofit.create(HeckylInterface.class);
////
////    Call<HeckylNews> callNews = heckylInterface.getHeckylNews(1, "ISIN", "INE009A01021", 0 , 1 );
////
////    callNews.enqueue(new Callback<HeckylNews>() {
////        @Override
////        public void onResponse(Call<HeckylNews> call, Response<HeckylNews> response) {
////
////            if (response.isSuccessful() && response.body().getNewsItems() != null) {
////
////
////                    if (newsItems.isEmpty()) {
////                        newsItems.clear();
////
////                    }
////
////                    newsItems = response.body().getNewsItems();
////                    adapter = new HeckylAdapter(newsItems, ExampleMain.this);
////                    recyclerView.setAdapter(adapter);
////                    adapter.notifyDataSetChanged();
////
////
////
////
////                }else {
////                    Toast.makeText(ExampleMain.this, "no result,", Toast.LENGTH_LONG).show();
////                }
////            }
////
////
////
////
////
////        @Override
////        public void onFailure(Call<HeckylNews> call, Throwable t) {
////
////        }
////    });
//
//    }
//
//
//    public void LoadNews() {
//        HeckylInterface heckylInterface = HeckylApiClient.getApiClient().create(HeckylInterface.class);
//
//        Call<HeckylNews> callHeckylNews;
//        callHeckylNews = heckylInterface.getHeckylNews(1, "ISIN", "INE009A01021", 0, 1);
//
//        callHeckylNews.enqueue(new Callback<HeckylNews>() {
//            @Override
//            public void onResponse(Call<HeckylNews> call, Response<HeckylNews> response) {
//
//                if (response.isSuccessful() && response.body().getNewsItems() != null) {
//
//
//                    if (newsItems.isEmpty()) {
//                        newsItems.clear();
//
//                    }
//
//                    newsItems = response.body().getNewsItems();
//                    adapter = new HeckylAdapter(newsItems, ExampleMain.this);
//                    recyclerView.setAdapter(adapter);
//                    adapter.notifyDataSetChanged();
//
//
//                } else {
//                    Toast.makeText(ExampleMain.this, "no result,", Toast.LENGTH_LONG).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<HeckylNews> call, Throwable t) {
//
//            }
//        });
//
//
//    }
//}
//
//
