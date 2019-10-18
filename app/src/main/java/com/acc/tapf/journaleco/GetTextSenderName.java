package com.acc.tapf.journaleco;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class GetTextSenderName {

    Set<SmsDetails> set;
    List<SmsDetails>list;
    String comparator;
    Context context;

    public GetTextSenderName(String comparator, Context context){
        this.comparator = comparator;
        this.context = context;
        list = new ArrayList<>();
        set = new LinkedHashSet<>();
    }

    public void searchInbox(){
        Cursor c = renderCursor();
        if (c.getColumnCount()>0){
            c.moveToFirst();
            Set<String> copies = new HashSet<>();
            while (c.moveToNext()){
                String add = c.getString(c.getColumnIndex("address"));
                String body = c.getString(c.getColumnIndex("body"));
                String date = c.getString(c.getColumnIndex("date"));
                String[] text = body.split(" ");
                String nme = extractName(body).toUpperCase();
                if(add.equals("+263164") && smsTypeFilter(body,comparator) && compareNames(copies, nme)){
                    SmsDetails smsMessage = new SmsDetails();
                    smsMessage.setBody(body);
                    smsMessage.setDate(date);
                    set.add(smsMessage);
                    list.add(smsMessage);
                    assignSetNames(copies, nme);
                }
            }if (!copies.isEmpty()){
                     copies.clear();
                     copies = null;
            }

        }
        if (!c.isClosed()) {
            c.close();
        }

    }

    private Cursor renderCursor(){
        Uri inboxURI = Uri.parse("content://sms/inbox");
        String[] reqCols = new String[] { "_id", "address", "body", "date" };
        ContentResolver cr = context.getContentResolver();
        return cr.query(inboxURI, reqCols, null, null, "date desc");
    }

    public boolean smsTypeFilter(String body, String comparator) {
        String[] text = body.split(" ");
        if (text[0].equals(comparator)) //"Transfer" for outgoing && "EcoCash:" for incoming
            return true;
        else
            return false;
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

    public void assignSetNames(Set<String> setter, String names) {
        setter.add(names);

    }

    public Set<SmsDetails> getSetData(){return set;}

    private boolean compareNames(Set<String> copies, String name){
        String zita = name;
        return !copies.contains(name);
    }

}
