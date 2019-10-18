package com.acc.tapf.journaleco;

import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.content.ContextCompat;

public class CheckForReadPermission {
    Context context;
    public final static int REQUSET_CODE_PERMISSION_READ_SMS = 123;

    public CheckForReadPermission(Context context) {
        this.context = context;
    }

    public boolean checkPermission(String permission){
        int checkPermission = ContextCompat.checkSelfPermission(context, permission);
        return checkPermission == PackageManager.PERMISSION_GRANTED;
    }
}
