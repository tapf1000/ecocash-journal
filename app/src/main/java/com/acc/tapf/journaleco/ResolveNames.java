package com.acc.tapf.journaleco;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ResolveNames {
    List<SmsDetails> list;
    Context context;
    public ResolveNames(Context context) {
        list = new ArrayList<>();
       this.context = context;
    }

    public List<SmsDetails> retrieveRoslvedSms(String nameComparator){
        Cursor c = renderCursor();
        if (c.getColumnCount()>0){
            c.moveToFirst();
            while (c.moveToNext()){
                String add = c.getString(c.getColumnIndex("address"));
                String body = c.getString(c.getColumnIndex("body"));
                String date = c.getString(c.getColumnIndex("date"));
                String[] text = body.split(" ");
                String nme = extractName(body).toUpperCase();
                if(add.equals("+263164") && text[0].equals("EcoCash:")&& compareNames(nameComparator, nme) ){
                    SmsDetails smsMessage = new SmsDetails();
                    smsMessage.setBody(body);
                    smsMessage.setDate(date);
                    list.add(smsMessage);
                }
            }
        }
        if (!c.isClosed()) {
            c.close();
        }
        for (SmsDetails smsDetails: list) {
            Log.d("Body", "-----------------------------------------"+smsDetails.getBody());
        }
        return list;
    }

    private Cursor renderCursor(){
        Uri inboxURI = Uri.parse("content://sms/inbox");
        String[] reqCols = new String[] { "_id", "address", "body", "date" };
        ContentResolver cr = context.getContentResolver();
        return cr.query(inboxURI, reqCols, null, null, "date desc");
    }

    private String extractName(String messageBody){
        String name = "";
        String[] text = messageBody.split(" ");
        for(int i = 0; i<text.length; i++){
            if(text[i].equals("from")){
                for(int j = i+1; j<text.length; j++){
                    if(text[j].equals("Approval")){
                        for (int k=i+1; k<j; k++){
                            name+= " "+text[k];
                        }
                    }
                }
                break;
            }
        }
        return name;
    }

    public List<SmsDetails> getListData(){return list;}

    private boolean compareNames(String zita, String name){
        String compare1 = zita.toUpperCase();
        String compare2 = name.toUpperCase();
        return compare1.equals(compare2);
    }
}
