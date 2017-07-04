package com.lhd.service;

import android.app.KeyguardManager;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.lhd.activity.Main;

/**
 * Created by D on 7/1/2017.
 */

public class ListenOnOff extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Main.showLog("onStartCommand ListenOnOff");
        //    keyguardManager = (KeyguardManager) getSystemService(Activity.KEYGUARD_SERVICE);
        // lock = keyguardManager.newKeyguardLock(KEYGUARD_SERVICE);
        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_SCREEN_ON);
        filter.addAction(Intent.ACTION_SCREEN_OFF);
        registerReceiver(new BroadcastScreenOnOff(), filter);
        return START_STICKY;
    }
    private KeyguardManager keyguardManager;
    private KeyguardManager.KeyguardLock lock;

    class BroadcastScreenOnOff extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(Intent.ACTION_SCREEN_ON)) {
                Main.showLog("ACTION_SCREEN_ON");
                startService(new Intent(ListenOnOff.this, ShowLock.class));
            } else if (intent.getAction().equals(Intent.ACTION_SCREEN_OFF)) {
                Main.showLog("ACTION_SCREEN_OFF");
                stopService(new Intent(ListenOnOff.this, ShowLock.class));
            } else if (intent.getAction().equals(Intent.ACTION_USER_PRESENT)) {
                Main.showLog("ACTION_USER_PRESENT");
                stopService(new Intent(ListenOnOff.this, ShowLock.class));
            } else if (intent.getAction().equals(Intent.ACTION_USER_UNLOCKED)) {
                Main.showLog("ACTION_USER_UNLOCKED");
                stopService(new Intent(ListenOnOff.this, ShowLock.class));
            }
        }
    }

    @Override
    public void onDestroy() {
        Main.showLog("onDestroy ListenOnOff");
        super.onDestroy();
        //   lock.reenableKeyguard();


    }
}
