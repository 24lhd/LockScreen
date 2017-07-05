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
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lhd.activity.Main;
import com.lhd.demolock.R;
import com.lhd.fragment.Setting;
import com.lhd.object.BackgroundImageLockScreen;
import com.lhd.object.OnOff;
import com.orhanobut.hawk.Hawk;

import static android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN;
import static android.view.WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL;

/**
 * Created on 2/20/2016.
 */
public class FloatIcon extends Service {

    private BroadcastReceiver mReceiver;
    private boolean isShowing = false;
    private TextView textview;
    private Button btUnLock;
    private View layout;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    ImageView imgBackground;
    private WindowManager windowManager;
    WindowManager.LayoutParams params;

    @Override
    public void onCreate() {
        super.onCreate();
        Hawk.init(this).build();
        Main.showLog("FloatIcon onCreate");
        ((KeyguardManager) getSystemService(KEYGUARD_SERVICE)).newKeyguardLock(getPackageName()).disableKeyguard();
        windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
        LayoutInflater inflater = LayoutInflater.from(this);
        layout = inflater.inflate(R.layout.lock_screen, null);
        btUnLock = (Button) layout.findViewById(R.id.btn_unlock_screen);
        imgBackground = layout.findViewById(R.id.im_bg_lockscreen);
        TextView tvDate = layout.findViewById(R.id.txt_date_lockscreen);
        TextView tvTime = layout.findViewById(R.id.txt_time_lockscreen);
        tvDate.setText(Setting.date());

        try {
            if (((OnOff) Hawk.get(Main.IS_24H)).isTrue())
                tvTime.setText(Setting.time24());
            else
                tvTime.setText(Setting.time12());
        } catch (NullPointerException e) {
            tvDate.setText(Setting.time24());
        }
        btUnLock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Main.showLog("ShowLock stopSelf");
                windowManager.removeViewImmediate(layout);
//                stopSelf();
                isShowing = false;
            }
        });
        params = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.TYPE_TOAST,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                        + FLAG_NOT_TOUCH_MODAL
                        + WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED
                        + WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH
                        + FLAG_FULLSCREEN | FLAG_NOT_TOUCH_MODAL + WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN,
                PixelFormat.TRANSLUCENT);
        params.gravity = Gravity.CENTER;
        mReceiver = new LockScreenStateReceiver();
        IntentFilter filter = new IntentFilter(Intent.ACTION_SCREEN_OFF);
        filter.addAction(Intent.ACTION_USER_PRESENT);
        registerReceiver(mReceiver, filter);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_STICKY;
    }

    public class LockScreenStateReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(Intent.ACTION_SCREEN_OFF)) {
                //if screen is turn off show the textviewx
                Hawk.init(context).build();
                if (!isShowing && ((OnOff) Hawk.get(Main.IS_ENABLE_LOCK)).isTrue()) {
                    Main.showLog("ShowLock");
                    try {
                        Main.showLog("load " + ((BackgroundImageLockScreen) Hawk.get(Main.IMAGE_BACKGROUND)).getDrawImage());
                        Glide.with(FloatIcon.this).load(((BackgroundImageLockScreen) Hawk.get(Main.IMAGE_BACKGROUND)).getDrawImage()).into(imgBackground);
                    } catch (NullPointerException e) {
                        Glide.with(FloatIcon.this).load(R.drawable.a19).into(imgBackground);
                    }
                    ((KeyguardManager) getSystemService(Activity.KEYGUARD_SERVICE)).newKeyguardLock(getPackageName()).disableKeyguard();
                    windowManager.addView(layout, params);
                    isShowing = true;
                }
            } else if (intent.getAction().equals(Intent.ACTION_USER_PRESENT)) {
                //Handle resuming events if user is present/screen is unlocked remove the textview immediately
                if (isShowing) {
                    ((KeyguardManager) getSystemService(Activity.KEYGUARD_SERVICE)).newKeyguardLock(getPackageName()).reenableKeyguard();
                    windowManager.removeViewImmediate(layout);
                    isShowing = false;
                }
            }
        }
    }

    @Override
    public void onDestroy() {
        //unregister receiver when the service is destroy
        if (mReceiver != null) {
            unregisterReceiver(mReceiver);
        }

        //remove view if it is showing and the service is destroy
        if (isShowing) {
            Main.showLog("removeViewImmediate");
            windowManager.removeViewImmediate(layout);
            ((KeyguardManager) getSystemService(Activity.KEYGUARD_SERVICE)).newKeyguardLock(getPackageName()).reenableKeyguard();
            isShowing = false;
        }
        super.onDestroy();
    }

}