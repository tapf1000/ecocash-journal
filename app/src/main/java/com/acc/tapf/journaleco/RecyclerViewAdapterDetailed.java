package com.acc.tapf.journaleco;

import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

public class RecyclerViewAdapterDetailed extends RecyclerView.Adapter<RecyclerViewAdapterDetailed.DetailedViewHolder> {

    List<SmsDetails> list;
    String name;
    public RecyclerViewAdapterDetailed(List<SmsDetails> list){
        this.list = list;
    }

    @NonNull
    @Override
    public DetailedViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.details_list, viewGroup, false);
        DetailedViewHolder detailedViewHolder = new DetailedViewHolder(view);
        return detailedViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DetailedViewHolder detailedViewHolder, int i) {
        detailedViewHolder.textViewDate.setText(list.get(i).getDate());
        detailedViewHolder.textViewDetailedBody.setText(list.get(i).getBody());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class DetailedViewHolder extends RecyclerView.ViewHolder{
        TextView textViewDetailedBody;
        TextView textViewDate;
        ConstraintLayout layout;
        public DetailedViewHolder(View view){
            super(view);
            layout = view.findViewById(R.id.detailsLayout);
            textViewDetailedBody = view.findViewById(R.id.textViewDetail);
            textViewDate = view.findViewById(R.id.textViewDate);
        }
    }
}
