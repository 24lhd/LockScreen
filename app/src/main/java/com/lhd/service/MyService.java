package com.lhd.service;

import android.app.Activity;
import android.app.KeyguardManager;
import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.lhd.activity.Main;
import com.lhd.broadcast.BroadcastScreenOnOff;

/**
 * Created by D on 7/1/2017.
 */

public class MyService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        //    keyguardManager = (KeyguardManager) getSystemService(Activity.KEYGUARD_SERVICE);
        // lock = keyguardManager.newKeyguardLock(KEYGUARD_SERVICE);

        return START_STICKY;
    }

    private KeyguardManager keyguardManager;
    private KeyguardManager.KeyguardLock lock;
    BroadcastScreenOnOff broadcastScreenOnOff;
    @Override
    public void onCreate() {
        ((KeyguardManager) getSystemService(KEYGUARD_SERVICE)).newKeyguardLock(getPackageName()).disableKeyguard();
        Main.showLog("onCreate MyService");
        broadcastScreenOnOff=new BroadcastScreenOnOff();
        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_SCREEN_ON);
        filter.addAction(Intent.ACTION_SCREEN_OFF);
        registerReceiver(broadcastScreenOnOff, filter);
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        unregisterReceiver(broadcastScreenOnOff);

        ((KeyguardManager) getSystemService(Activity.KEYGUARD_SERVICE)).newKeyguardLock(getPackageName()).reenableKeyguard();
        Main.showLog("onDestroy MyService");
        super.onDestroy();
        //   lock.reenableKeyguard();


    }
}
