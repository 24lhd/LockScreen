package com.lhd.broadcast;

import android.content.Context;
import android.util.Log;

import com.lhd.demolock.R;
import com.lhd.model.config.Config;
import com.lhd.model.object.ItemNotification;

import java.util.Date;

/**
 * Created by D on 7/24/2017.
 */

public class CallRecerver extends CallMain {

    private static final String TAG = "CallRecerver";

    @Override
    protected void onIncomingCallReceived(Context ctx, String number, Date start)
    {
        Log.e(TAG,"onIncomingCallReceived"+number);
    }

    @Override
    protected void onIncomingCallAnswered(Context ctx, String number, Date start)
    {
        Log.e(TAG,"onIncomingCallAnswered"+number);
    }

    @Override
    protected void onIncomingCallEnded(Context ctx, String number, Date start, Date end)
    {
        Log.e(TAG,"onIncomingCallEnded"+number);
    }

    @Override
    protected void onOutgoingCallStarted(Context ctx, String number, Date start)
    {
        Log.e(TAG,"onOutgoingCallStarted"+number);
    }

    @Override
    protected void onOutgoingCallEnded(Context ctx, String number, Date start, Date end)
    {
        Log.e(TAG,"onOutgoingCallEnded"+number);
    }

    @Override
    protected void onMissedCall(Context ctx, String number, Date start)
    {
        Log.e(TAG,"onMissedCall "+number);
        Log.e(TAG,"onMissedCall "+ Config.getContactName(ctx,number));
         Config.putNoti(new ItemNotification("Missing call " +Config.getContactName(ctx,number),Config.time24(start),"Bạn đã bỏ lỡ một cuộc gọi" , R.drawable.ic_call_missed_outgoing_red_500_36dp),ctx);
        Log.e("ken", "Missing call");


    }

}