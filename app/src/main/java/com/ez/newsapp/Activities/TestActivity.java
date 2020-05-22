package com.ez.newsapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.ez.newsapp.HeckylApi.HeckylApiClient;
import com.ez.newsapp.HeckylApi.HeckylInterface;
import com.ez.newsapp.HeckylModels.HeckylNews;
import com.ez.newsapp.HeckylModels.NewsItems;
import com.ez.newsapp.Models.News;
import com.ez.newsapp.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TestActivity extends AppCompatActivity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        textView = findViewById(R.id.textVIew1);

        Retrofit testRetrofit = new Retrofit.Builder()
                .baseUrl("http://216.185.108.228/Find1Svc/API/JSON/News.svc/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        HeckylInterface heckylInterfaceTest = testRetrofit.create(HeckylInterface.class);

        final Call<HeckylNews> callTest = heckylInterfaceTest.getNews("1", "ISIN", "INE009A01021", "0", "1");


        callTest.enqueue(new Callback<HeckylNews>() {
            @Override
            public void onResponse(Call<HeckylNews> call, Response<HeckylNews> response) {

                if (!response.isSuccessful()) {
                    textView.setText("code"+response.code());
                    return;
                }

                HeckylNews newsTest =  response.body();
                String content = "";

                for (NewsItems news : newsTest.getNewsItems()) {
                    content += "Title " + news.getTitle();
                    content += "Description " + news.getDescription();
                    content += "source " +news.getSource();

                    textView.append(content);
                }

            }


            @Override
            public void onFailure(Call<HeckylNews> call, Throwable t) {
                textView.setText(t.getMessage());
            }
        });





    }
}
