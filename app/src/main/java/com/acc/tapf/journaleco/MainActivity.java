package com.acc.tapf.journaleco;

import android.Manifest;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class MainActivity extends AppCompatActivity {

    Set<SmsDetails> list;
    private RecyclerViewAdapterNames recyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CheckForReadPermission checkForReadPermission = new CheckForReadPermission(MainActivity.this);
        if(checkForReadPermission.checkPermission(Manifest.permission.READ_SMS)){
            list = loadMessageList();
            loadRecyclerView();
        }else {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.READ_SMS}, checkForReadPermission.REQUSET_CODE_PERMISSION_READ_SMS);

        }


    }

    public Set<SmsDetails> loadMessageList(){
        GetTextSenderName readSmsFromInbox = new GetTextSenderName("EcoCash:", MainActivity.this);
        readSmsFromInbox.searchInbox();
        return readSmsFromInbox.getSetData();
    }

    public void loadRecyclerView(){
        RecyclerView recyclerView = findViewById(R.id.recyclerViewNames);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewAdapter = new RecyclerViewAdapterNames(this, list);
        recyclerView.setAdapter(recyclerViewAdapter);
    }

    private void initEditText(){
        final EditText etSearch = findViewById(R.id.et_search);
        etSearch.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_UP){
                    String s = etSearch.getText().toString();
                    char [] chars = s.toCharArray();
                    recyclerViewAdapter.setDataSet(getSingleSms(chars));
                }
                return false;
            }
        });
    }

    private Set getSingleSms(char... chars){
        Set<SmsDetails> newList = new HashSet<>();
        for (SmsDetails sms :list) {
            char[] s = sms.name.toCharArray();
            for (int i = 0; i < chars.length; i++){
                if(s[i] == chars[i])
                    newList.add(sms);
            }
        }
        return newList;
    }
}
