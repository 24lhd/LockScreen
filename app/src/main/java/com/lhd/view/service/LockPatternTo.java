package com.lhd.view.service;

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

import com.lhd.activity.Main;
import com.lhd.demolock.R;
import com.lhd.fragment.Setting;
import com.lhd.model.config.Config;
import com.lhd.model.object.BackgroundImageLockScreen;
import com.lhd.model.object.OnOff;
import com.orhanobut.hawk.Hawk;

import static android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN;
import static android.view.WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL;

/**
 * Created by D on 7/13/2017.
 */

public class LockPatternTo extends Service {

    private BroadcastReceiver mReceiver;
    private boolean isShowing = false;
    private Button btUnLock;
    private View layout;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    TextView tvDate;
    TextView tvTime;
    ImageView imgBackground;
    private WindowManager windowManager;
    WindowManager.LayoutParams params;

    @Override
    public void onCreate() {
        super.onCreate();
        Main.showLog("onReceive");
        Hawk.init(this).build();
        ((KeyguardManager) getSystemService(KEYGUARD_SERVICE)).newKeyguardLock(getPackageName()).disableKeyguard();
        windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
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
        setType();
        mReceiver = new LockScreenStateReceiver();
        IntentFilter filter = new IntentFilter(Intent.ACTION_SCREEN_OFF);
        filter.addAction(Intent.ACTION_USER_PRESENT);
        registerReceiver(mReceiver, filter);
    }

    private void setType() {
        LayoutInflater inflater = LayoutInflater.from(this);
        layout = inflater.inflate(R.layout.lock_screen, null);
        btUnLock = (Button) layout.findViewById(R.id.btn_unlock_screen);
        imgBackground = layout.findViewById(R.id.im_bg_lockscreen);
        tvDate = layout.findViewById(R.id.txt_date_lockscreen);
        tvTime = layout.findViewById(R.id.txt_time_lockscreen);
        btUnLock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Main.showLog("removeViewImmediate");
                windowManager.removeViewImmediate(layout);
                isShowing = false;
            }
        });
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_STICKY;
    }

    public class LockScreenStateReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(Intent.ACTION_SCREEN_OFF)) {
                if (isShowing) {
                    ((KeyguardManager) getSystemService(Activity.KEYGUARD_SERVICE)).newKeyguardLock(getPackageName()).disableKeyguard();
                    windowManager.removeViewImmediate(layout);
                    isShowing = false;
                }
                Hawk.init(context).build();
                try {
                    if (!isShowing && ((OnOff) Hawk.get(Config.ENABLE_LOCK)).isTrue()) {
                        Main.showLog("ShowLock");
                        try {
                            BackgroundImageLockScreen.loadImage(LockPatternTo.this, ((BackgroundImageLockScreen) Hawk.get(Main.IMAGE_BACKGROUND)).getPickImage(), imgBackground);
                        } catch (NullPointerException e) {
                            BackgroundImageLockScreen.loadImage(LockPatternTo.this, "" + R.drawable.bg2, imgBackground);
                        } catch (ClassCastException e) {
                            BackgroundImageLockScreen.loadImage(LockPatternTo.this, "a", imgBackground);
                        }
                        tvDate.setText(Setting.date());
                        try {
                            if (((OnOff) Hawk.get(Main.IS_24H)).isTrue())
                                tvTime.setText(Setting.time24());
                            else
                                tvTime.setText(Setting.time12());
                        } catch (NullPointerException e) {
                            tvTime.setText(Setting.time24());
                        }
                        windowManager.addView(layout, params);
                        isShowing = true;
                    }
                } catch (NullPointerException e) {
                    Hawk.put(Config.ENABLE_LOCK, new OnOff(false));
                }
            } else if (intent.getAction().equals(Intent.ACTION_USER_PRESENT)) {
//                if (isShowing) {
//                    ((KeyguardManager) getSystemService(Activity.KEYGUARD_SERVICE)).newKeyguardLock(getPackageName()).disableKeyguard();
//                    windowManager.removeViewImmediate(layout);
//                    isShowing = false;
//                }
            }
        }
    }

    @Override
    public void onDestroy() {
        if (mReceiver != null) {
            unregisterReceiver(mReceiver);
        }
        if (isShowing) {
            Main.showLog("removeViewImmediate");
            windowManager.removeViewImmediate(layout);
//            ((KeyguardManager) getSystemService(Activity.KEYGUARD_SERVICE)).newKeyguardLock(getPackageName()).reenableKeyguard();
            isShowing = false;
        }
        super.onDestroy();
    }

}