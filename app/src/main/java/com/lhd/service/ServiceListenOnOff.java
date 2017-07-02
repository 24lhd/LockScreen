package com.lhd.service;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.lhd.activity.MainActivity;

/**
 * Created by D on 7/1/2017.
 */

public class ServiceListenOnOff extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {


        IntentFilter screenStateFilter = new IntentFilter();
        screenStateFilter.addAction(Intent.ACTION_SCREEN_ON);
        screenStateFilter.addAction(Intent.ACTION_SCREEN_OFF);
        registerReceiver(new BroadcastScreenOnOff(), screenStateFilter);
        MainActivity.showLog("START_STICKY ServiceListenOnOff");
        return START_STICKY;

    }

    @Override
    public void onCreate() {
//        KeyguardManager keyguardManager = (KeyguardManager) getSystemService(Activity.KEYGUARD_SERVICE);
//        KeyguardManager.KeyguardLock lock = keyguardManager.newKeyguardLock(KEYGUARD_SERVICE);
//        lock.disableKeyguard();,
        super.onCreate();
    }

    class BroadcastScreenOnOff extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(Intent.ACTION_SCREEN_ON)) {
                MainActivity.showLog("ACTION_SCREEN_ON");
                startService(new Intent(ServiceListenOnOff.this,ShowLock.class));

            } else if (intent.getAction().equals(Intent.ACTION_SCREEN_OFF)) {
                MainActivity.showLog("ACTION_SCREEN_OFF");
                stopService(new Intent(ServiceListenOnOff.this,ShowLock.class));
        }
        }
    }
}
