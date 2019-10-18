package com.acc.tapf.journaleco;

import android.util.Log;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SmsDetails implements Serializable {


    String address;
    String body;
    String date;
    String name;

    public SmsDetails() {
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBody() {
        return body;
    }
    public void setBody(String body){this.body = body;}

    public String getDate() {
        Date dateFromSms = new Date(Long.parseLong(date));
        return new SimpleDateFormat("dd-MM-yyyy").format(dateFromSms);
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {
        String[] text = body.split(" ");
        String name = "";
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
        Log.d("NamesOfGet",name);
        return name;
    }
}
