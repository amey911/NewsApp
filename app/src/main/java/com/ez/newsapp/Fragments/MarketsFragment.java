package com.ez.newsapp.Fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.ez.newsapp.HeckylApi.HeckylApiClient;
import com.ez.newsapp.HeckylApi.HeckylInterface;
import com.ez.newsapp.HeckylModels.EntitySentiments;
import com.ez.newsapp.HeckylModels.Sentiment;
import com.ez.newsapp.R;
import com.github.mikephil.charting.charts.CandleStickChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.CandleData;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MarketsFragment extends Fragment {


    private LineChart marketLineChart;
    private CandleStickChart marketCandleStickChart;

    List<EntitySentiments> sentiVals = new ArrayList<>();

    List<Integer> positiveVals = new ArrayList<>();
    List<Integer> negativeVals = new ArrayList<>();
    List<Integer> neutralVals = new ArrayList<>();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_markets, container,false);

        marketCandleStickChart = view.findViewById(R.id.market_candle_chart);
        marketLineChart = view.findViewById(R.id.market_line_chart);





        LineDataSet lineDataSet = new LineDataSet(sentimentsValues(), "sentimentSet1");
        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(lineDataSet);

        LineData data = new LineData(dataSets);


        marketLineChart.setData(data);
        marketLineChart.invalidate();



       return view;



    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



//        getSentimentsSeries("1", "ISIN", "US38259P7069");
    }








    private void getSentimentsSeries(String asset, String entityType, String entityCode) {


        HeckylInterface heckylInterface = HeckylApiClient.getApiClient().create(HeckylInterface.class);

        final Call<Sentiment> sentimentsCall;


        sentimentsCall = heckylInterface.getSentimentTimeSeries(asset, entityType, entityCode);

        sentimentsCall.enqueue(new Callback<Sentiment>() {
            @Override
            public void onResponse(Call<Sentiment> call, Response<Sentiment> response) {



                if (response.isSuccessful() && response.body().getEntitySentiments() != null) {

                    if (sentiVals.isEmpty()){

                        sentiVals.clear();

                        sentiVals = response.body().getEntitySentiments();





                    }

                }


            }

            @Override
            public void onFailure(Call<Sentiment> call, Throwable t) {

            }
        });




        for (int i =0; i < sentiVals.size(); i++) {
            int positive = sentiVals.get(i).getPositive();
            int neutral = sentiVals.get(i).getNeutral();
            int negative = sentiVals.get(i).getNegative();

            positiveVals.add(i, positive);
            negativeVals.add(i, negative);
            neutralVals.add(i, neutral);


            Log.e("TAG", "onResponse: "+sentiVals.get(i).getPositive() );


        }


    }


    private ArrayList<Entry> sentimentsValues()
    {

        ArrayList<Entry> chartVals = new ArrayList<Entry>();

        for (int i = 0; i<positiveVals.size(); i++) {

            int posval = positiveVals.get(i);

            chartVals.add(new Entry(i, posval));

        }


        return chartVals;
    }





}
