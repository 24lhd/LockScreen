package com.lhd.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.lhd.activity.Main;

/**
 * Created by D on 7/4/2017.
 */

public class BroadcastScreenOnOff extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(Intent.ACTION_SCREEN_ON)) {
            Main.showLog("ACTION_SCREEN_ON");
            //   lock.reenableKeyguard();
//            startService(new Intent(ListenOnOff.this, ShowLock.class));
        } else if (intent.getAction().equals(Intent.ACTION_SCREEN_OFF)) {
            Main.showLog("ACTION_SCREEN_OFF");
//            lock.reenableKeyguard();
            //   lock.disableKeyguard();
//            stopService(new Intent(ListenOnOff.this, ShowLock.class));
        }
    }

}
