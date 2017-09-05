package com.example.sinelnikovserhii.sharesurl;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by sinelnikovserhii on 03.05.17.
 */

public class MyRecycleViewAdapter extends Adapter<MyRecycleViewAdapter.MyViewHolder> {

    private List<Shares> mSharesList;
    private OnItemClickListener onClickListener;

    public MyRecycleViewAdapter(List<Shares> sharesList, OnItemClickListener onItemClick) {
        this.mSharesList = sharesList;
        this.onClickListener = onItemClick;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Shares shares = mSharesList.get(position);
        holder.mIndex.setText(shares.getIndex());
        holder.mNameText.setText(shares.getName());
        holder.mCost.setText(shares.getCost());
    }

    public void setSharesList(List<Shares> sharesList) {
        mSharesList.clear();
        mSharesList.addAll(sharesList);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mSharesList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tvName) TextView mNameText;
        @BindView(R.id.tvIndex) TextView mIndex;
        @BindView(R.id.tvCost) TextView mCost;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        @OnClick(R.id.tvName)
        public void onClickName(View view) {
            onClickListener.onItemClick(mSharesList.get(getAdapterPosition()));
        }
    }

    interface OnItemClickListener {

         void onItemClick(Shares share);
    }
}