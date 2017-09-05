package com.example.sinelnikovserhii.sharesurl;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by sinelnikovserhii on 05.05.17.
 */
public class InformationActivity extends AppCompatActivity {
     @BindView(R.id.tvAnswer) TextView tvAnswer;
     @BindView(R.id.tvChange) TextView tvChange;
     @BindView(R.id.tvCost) TextView tvCost;
     Gson gson = new Gson();
     List<Shares> sharesList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_graph);
        ButterKnife.bind(this);

        ShareService shareService = new ShareService();
        String[] indexes = new String[]{};
        sharesList = new ArrayList<>();
        final Shares sharesGet = new Shares();

        final Call<JsonObject> call = shareService.getShares(indexes);
        call.enqueue((new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.isSuccessful()) {
                    Shares[] shares = gson.fromJson(response.body().getAsJsonObject("query").getAsJsonObject("results").getAsJsonArray("quote"), Shares[].class);
                    setShares(Arrays.asList(shares));
                    tvChange.setText(sharesGet.getChangeCourse());
                    tvCost.setText(sharesGet.getCost());
                } else {
                    ResponseBody errorBody = response.errorBody();
                    try {
                        tvAnswer.setText(errorBody.string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                tvAnswer.setText("Что-то пошло не так: ");
            }
        }));
    }

    public void setShares(List<Shares> shares) {
        tvChange.append(shares.toString());
    }
}