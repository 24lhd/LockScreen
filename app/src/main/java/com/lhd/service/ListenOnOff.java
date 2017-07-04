package com.lhd.service;

import android.app.Activity;
import android.app.KeyguardManager;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.os.PowerManager;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.lhd.activity.Main;
import com.lhd.demolock.R;

/**
 * Created by D on 7/1/2017.
 */

public class ListenOnOff extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    PowerManager.WakeLock wl;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Main.showLog("onStartCommand ListenOnOff");
        KeyguardManager keyguardManager = (KeyguardManager) getSystemService(Activity.KEYGUARD_SERVICE);
        KeyguardManager.KeyguardLock lock = keyguardManager.newKeyguardLock(KEYGUARD_SERVICE);
        lock.disableKeyguard();

        IntentFilter screenStateFilter = new IntentFilter();
        screenStateFilter.addAction(Intent.ACTION_SCREEN_ON);
        screenStateFilter.addAction(Intent.ACTION_SCREEN_OFF);
        registerReceiver(new BroadcastScreenOnOff(), screenStateFilter);


        return START_STICKY;

    }
    private WindowManager windowManager;
    private View viewWindow;
    private WindowManager.LayoutParams params;
    private void showWindow() {
        Button btUnLock;
        LayoutInflater inflater = LayoutInflater.from(this);
        viewWindow = inflater.inflate(R.layout.activity_main, null);
        windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
        params = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.TYPE_SYSTEM_ALERT,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSLUCENT);
        params.gravity = Gravity.CENTER;
        btUnLock = (Button)viewWindow.findViewById(R.id.bt_unlock);
        btUnLock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Main.showLog("ShowLock stopSelf");
                if (viewWindow != null) windowManager.removeView(viewWindow);
                // stopSelf();
            }
        });
        windowManager.addView(viewWindow, params);
    }
    class BroadcastScreenOnOff extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(Intent.ACTION_SCREEN_ON)) {
                Main.showLog("ACTION_SCREEN_ON");
                showWindow();
            } else if (intent.getAction().equals(Intent.ACTION_SCREEN_OFF)) {
                Main.showLog("ACTION_SCREEN_OFF");
                try {
                    if (viewWindow != null) windowManager.removeView(viewWindow);
                }catch (IllegalArgumentException e){}
            }
        }
    }
}
