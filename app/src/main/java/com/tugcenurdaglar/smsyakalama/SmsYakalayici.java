package com.tugcenurdaglar.smsyakalama;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.widget.Toast;

public class SmsYakalayici extends BroadcastReceiver {
    //verileri okuyabilmek için
    SmsManager sms = SmsManager.getDefault(); //varsayılan SmsManager alındı


    @Override
    public void onReceive(Context context, Intent intent) {
        //sms geldiğinde bu method çalışır dolayısıyla burada yakalamak gerekir

        //birden fazla mesajlar Bundle şeklinde gelir

        Bundle b = intent.getExtras();
        //intent.getExtras(); diyerek bundle ların hepsi alınır
        /*pdus formatında bu mesajlar gelecektir ve bunlar dizi halinde alınmalı*/

        Object [] pdusObj = (Object []) b.get("pdus");

        //for döngüsüyle bunlar alınabilir
        for (int i = 0; i<pdusObj.length; i++){

            SmsMessage guncelMesaj = SmsMessage.createFromPdu((byte []) pdusObj[i]);

            String telNo = guncelMesaj.getDisplayOriginatingAddress();
            String mesaj = guncelMesaj.getDisplayMessageBody();

//            Toast.makeText(context,"Tel No"+telNo+" Mesaj : "+mesaj,Toast.LENGTH_SHORT).show();
        }
    }
}