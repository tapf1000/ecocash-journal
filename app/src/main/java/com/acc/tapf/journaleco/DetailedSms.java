package com.acc.tapf.journaleco;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;
import java.util.Set;

public class DetailedSms extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_sms);
        Intent intent = getIntent();
        String name = intent.getStringExtra(RecyclerViewAdapterNames.KEY);
        ResolveNames resolveNames = new ResolveNames(DetailedSms.this);
        loadRecyclerView(resolveNames.retrieveRoslvedSms(name));
    }
    public Set<SmsDetails> loadMessageList(){
        GetTextSenderName readSmsFromInbox = new GetTextSenderName("EcoCash:", DetailedSms.this);
        readSmsFromInbox.searchInbox();
        return readSmsFromInbox.getSetData();
    }

    public void loadRecyclerView(List<SmsDetails> list){
        RecyclerView recyclerView = findViewById(R.id.recyclerViewDetails);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        RecyclerViewAdapterDetailed recyclerViewAdapter = new RecyclerViewAdapterDetailed( list);
        recyclerView.setAdapter(recyclerViewAdapter);
    }
}
