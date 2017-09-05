package com.example.sinelnikovserhii.sharesurl;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    RecyclerView mRecyclerView;
    List<Shares> sharesList;
    Gson gson = new Gson();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ShareService service = new ShareService();
        sharesList = new ArrayList<>();
        final String[] indexes = new String[] {"AAPL","GPRO","GOOG","GGB","SNAP","TSLA","CSCO"};


        mRecyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        final MyRecycleViewAdapter adapter = new MyRecycleViewAdapter(sharesList, new MyRecycleViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Shares share) {
                Intent intent = new Intent(MainActivity.this, SharesActivity.class);
                intent.putExtra(SharesActivity.KEY_SHARE, share);
                startActivity(intent);
            }
        });

        mRecyclerView.setAdapter(adapter);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mRecyclerView.getContext(),
                ((LinearLayoutManager)mRecyclerView.getLayoutManager()).getOrientation());
        mRecyclerView.addItemDecoration(dividerItemDecoration);


        final Call<JsonObject> call = service.getShares(indexes);

        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if(response.isSuccessful()){
                    Shares[] shares = gson.fromJson(response.body().getAsJsonObject("query").getAsJsonObject("results").getAsJsonArray("quote"), Shares[].class);
                    ((MyRecycleViewAdapter)mRecyclerView.getAdapter()).setSharesList(Arrays.asList(shares));
                } else {
                    ResponseBody errorBody = response.errorBody();
                    try {
                        Toast.makeText(MainActivity.this, errorBody.string(),
                                Toast.LENGTH_SHORT).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                System.out.print("Чето пошло не так");

            }
        });
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = new Intent(MainActivity.this,SharesActivity.class);
        startActivity(intent);
        return super.onOptionsItemSelected(item);
    }
}