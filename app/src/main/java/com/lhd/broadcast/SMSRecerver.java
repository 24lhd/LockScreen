package com.lhd.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;

import com.lhd.demolock.R;
import com.lhd.model.config.Config;
import com.lhd.model.object.ItemNotification;

import java.util.Date;

/**
 * Created by D on 7/24/2017.
 */

public class SMSRecerver  extends BroadcastReceiver {
    private static final String TAG = "SMSRecerver";
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("android.provider.Telephony.SMS_RECEIVED")) {
            Bundle bundle = intent.getExtras();
            SmsMessage[] msgs = null;
            String msg_from = "";
            String msgBody = "";
            if (bundle != null) {
                try {
                    Object[] pdus = (Object[]) bundle.get("pdus");
                    msgs = new SmsMessage[pdus.length];
                    for (int i = 0; i < msgs.length; i++) {
                        msgs[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
                        msg_from = msgs[i].getOriginatingAddress();
                        msgBody = msgBody + msgs[i].getMessageBody();
                    }
                    Log.e(TAG, Config.getContactName(context,msg_from));
                    Log.e(TAG, msgBody);
                     Config.putNoti(new ItemNotification("SMS : " +Config.getContactName(context,msg_from),Config.time24(new Date()),"Một tin nhắn mới", R.drawable.ic_message_red_500_36dp),context);
                    Log.e("ken", msgBody);
                } catch (Exception e) {
                    Log.e("ken", e.getMessage());
                    e.printStackTrace();
                }
            }
        }
    }
}
