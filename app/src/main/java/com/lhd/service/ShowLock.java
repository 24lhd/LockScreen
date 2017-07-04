package com.lhd.service;

import android.app.Activity;
import android.app.KeyguardManager;
import android.app.Service;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.lhd.activity.Main;
import com.lhd.demolock.R;

/**
 * Created by D on 7/2/2017.
 */

public class ShowLock extends Service {
    private WindowManager windowManager;
    private View view;
    private WindowManager.LayoutParams params;
    private KeyguardManager keyguardManager;
    private KeyguardManager.KeyguardLock lock;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        Main.showLog("ShowLock");
        keyguardManager = (KeyguardManager) getSystemService(Activity.KEYGUARD_SERVICE);
        lock = keyguardManager.newKeyguardLock(KEYGUARD_SERVICE);
        showWindow();
        super.onCreate();
    }


    private void showWindow() {
        Button btUnLock;
        LayoutInflater inflater = LayoutInflater.from(this);
        view = inflater.inflate(R.layout.activity_main, null);
        windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
        params = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.TYPE_PHONE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL,
                PixelFormat.TRANSLUCENT);
        params.gravity = Gravity.CENTER;
        btUnLock = (Button) view.findViewById(R.id.bt_unlock);
        btUnLock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Main.showLog("ShowLock stopSelf");
                stopSelf();
            }
        });
        windowManager.addView(view, params);
        lock.disableKeyguard();
    }

    @Override
    public void onDestroy() {
        Main.showLog("onDestroy");
        if (view != null) windowManager.removeView(view);
        super.onDestroy();
        //   lock.reenableKeyguard();


    }
}
