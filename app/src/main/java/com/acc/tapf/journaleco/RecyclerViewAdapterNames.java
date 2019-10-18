package com.acc.tapf.journaleco;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Set;

public class RecyclerViewAdapterNames extends RecyclerView.Adapter<RecyclerViewAdapterNames.NamesViewHolder> {
    ArrayList<SmsDetails> smsDetailsSet;
    public static final String KEY = "com.acc.tapf.journaleco.NAME";
    Context context;
    public RecyclerViewAdapterNames(Context context, Set<SmsDetails> smsDetailsSet){
        this.smsDetailsSet = new ArrayList<>();
        this.smsDetailsSet.addAll(smsDetailsSet);
        this.context = context;

    }
    public void setDataSet(Set<SmsDetails> smsDetailsSet){
        this.smsDetailsSet.addAll(smsDetailsSet);
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull NamesViewHolder namesViewHolder, int i) {
        final int position = i;
        namesViewHolder.textViewNames.setText(smsDetailsSet.get(i).getName());
        namesViewHolder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,DetailedSms.class);
                intent.putExtra(KEY, smsDetailsSet.get(position).getName());
                context.startActivity(intent);
            }
        });
    }

    @NonNull
    @Override
    public RecyclerViewAdapterNames.NamesViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.name_list, viewGroup, false);
        NamesViewHolder namesViewHolder = new NamesViewHolder(view);
        return namesViewHolder;
    }

    @Override
    public int getItemCount() {
        return smsDetailsSet.size();
    }

    public static class NamesViewHolder extends RecyclerView.ViewHolder{
        LinearLayout layout;
        TextView textViewNames;
        public NamesViewHolder(View view){
            super(view);
            layout = itemView.findViewById(R.id.nameLayout);
            textViewNames = itemView.findViewById(R.id.textViewName);
        }
    }
}
