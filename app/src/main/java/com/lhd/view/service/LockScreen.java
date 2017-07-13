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
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.lhd.activity.Main;
import com.lhd.demolock.R;
import com.lhd.fragment.Setting;
import com.lhd.model.config.Config;
import com.lhd.model.object.BackgroundImageLockScreen;
import com.lhd.model.object.LockType;
import com.lhd.model.object.OnOff;
import com.orhanobut.hawk.Hawk;
import com.takwolf.android.lock9.Lock9View;

import static android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN;
import static android.view.WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL;
import static com.lhd.model.object.BackgroundImageLockScreen.loadImage;

/**
 * Created by D on 7/7/2017.
 */

public class LockScreen extends Service implements View.OnClickListener {

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
        mReceiver = new LockScreenStateReceiver();
        IntentFilter filter = new IntentFilter(Intent.ACTION_SCREEN_OFF);
        filter.addAction(Intent.ACTION_USER_PRESENT);
        registerReceiver(mReceiver, filter);
    }

    private void setTypeNone() {
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
                unLock();
            }
        });
        Main.showLog("ShowLock");
        loadBackground(imgBackground);

        tvDate.setText(Setting.date());
        try {
            if (((OnOff) Hawk.get(Main.IS_24H)).isTrue())
                tvTime.setText(Setting.time24());
            else
                tvTime.setText(Setting.time12());
        } catch (NullPointerException e) {
            tvTime.setText(Setting.time24());
        }
//
    }

    private void unLock() {
        windowManager.removeViewImmediate(layout);
        isShowing = false;
    }

    public void loadBackground(ImageView imgBackground) {
        try {
            BackgroundImageLockScreen.loadImage(LockScreen.this, ((BackgroundImageLockScreen) Hawk.get(Main.IMAGE_BACKGROUND)).getPickImage(), imgBackground);
        } catch (NullPointerException e) {
            loadImage(LockScreen.this, "" + R.drawable.bg2, imgBackground);
        } catch (ClassCastException e) {
            loadImage(LockScreen.this, "a", imgBackground);
        }
    }

    private void setTypePatternTo() {
        LayoutInflater inflater = LayoutInflater.from(this);
        layout = inflater.inflate(R.layout.khoa_vuot_tron_to_layout, null);
        Lock9View lock9View;
        final TextView textView = layout.findViewById(R.id.txt_content_vuot_to_lock);
        lock9View = (Lock9View) layout.findViewById(R.id.lock_9_view);
        imgBackground = layout.findViewById(R.id.bg_im_lock_tron_to);
        loadBackground(imgBackground);
        textView.setText("Vẽ mấu hình mở khóa của bạn");
        lock9View.setCallBack(new Lock9View.CallBack() {
                                  public void onFinish(String password) {
                                      Main.showLog(password);
                                      if (((LockType) Hawk.get(Config.TYPE_LOCK)).getPass().contains(password)) {
                                          Main.showLog("unlock");
                                          unLock();
                                      } else
                                          textView.setText("Mẫ hình không đúng, thử lại");

                                  }
                              }
        );
    }

    TextView txtPin;
    private void setTypeMaPin() {
        LayoutInflater inflater = LayoutInflater.from(this);
        layout = inflater.inflate(R.layout.khoa_ma_pin, null);
        ImageView imbg=layout.findViewById(R.id.im_bg_pin_sc);
        loadBackground(imbg);
        txtPin = (TextView) layout.findViewById(R.id.tv_pin_input_screen_lock);
        txtPin.setText("");
        ImageView txtXoaPin = (ImageView) layout.findViewById(R.id.tv_xoa_pin_sc);
        txtXoaPin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (pinInPut.length() > 0) {
                    pinInPut = pinInPut.substring(0, pinInPut.length() - 1);
                    txtPin.setText(pinInPut);
                    txtPin.clearAnimation();
                }
            }
        });
//
        ImageView txtKhanCap = (ImageView) layout.findViewById(R.id.tv_khan_cap_sc);
        txtXoaPin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (pinInPut.length() > 0) {
                    pinInPut = pinInPut.substring(0, pinInPut.length() - 1);
                    txtPin.setText(pinInPut);
                    txtPin.clearAnimation();
                }
            }
        });
        Button button01 = layout.findViewById(R.id.btn_ma_pin_sc_01);
        button01.setOnClickListener(this);
        Button button02 = layout.findViewById(R.id.btn_ma_pin_sc_02);
        button02.setOnClickListener(this);
        Button button03 = layout.findViewById(R.id.btn_ma_pin_sc_03);
        button03.setOnClickListener(this);
        Button button04 = layout.findViewById(R.id.btn_ma_pin_sc_04);
        button04.setOnClickListener(this);
        Button button05 = layout.findViewById(R.id.btn_ma_pin_sc_05);
        button05.setOnClickListener(this);
        Button button06 = layout.findViewById(R.id.btn_ma_pin_sc_06);
        button06.setOnClickListener(this);
        Button button07 = layout.findViewById(R.id.btn_ma_pin_sc_07);
        button07.setOnClickListener(this);
        Button button08 = layout.findViewById(R.id.btn_ma_pin_sc_08);
        button08.setOnClickListener(this);
        Button button09 = layout.findViewById(R.id.btn_ma_pin_sc_09);
        button09.setOnClickListener(this);
        Button button00 = layout.findViewById(R.id.btn_ma_pin_sc_00);
        button00.setOnClickListener(this);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_STICKY;
    }

    @Override
    public void onClick(View view) {
        String pin = (String) view.getTag();
        if (pinInPut.length() < 4) {
            pinInPut = txtPin.getText().toString() + pin;
            txtPin.setText(pinInPut);
            txtPin.clearAnimation();
        }
        if (pinInPut.length() == 4) {
            Main.showLog(pinInPut);
            if (((LockType) Hawk.get(Config.TYPE_LOCK)).getPass().contains(pinInPut)) {
                Main.showLog("unlock");
                pinInPut="";
                unLock();
            } else {
                Animation shake = AnimationUtils.loadAnimation(this, R.anim.error_pin);
                txtPin.startAnimation(shake);

            }
        }

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
                        try {
                            if (Config.MAU_HINH_TO.contains(((LockType) Hawk.get(Config.TYPE_LOCK)).getName()))
                                setTypePatternTo();
                            else if (Config.MA_PIN.contains(((LockType) Hawk.get(Config.TYPE_LOCK)).getName()))
                                setTypeMaPin();
                            else setTypeNone();
                        } catch (NullPointerException e) {
                            setTypeNone();
                        }

                        windowManager.addView(layout, params);
                        isShowing = true;
                    }
                } catch (NullPointerException e) {
                    Main.showLog("NullPointerException");
                    Hawk.put(Config.ENABLE_LOCK, new OnOff(false));
                }
            } else if (intent.getAction().equals(Intent.ACTION_USER_PRESENT)) {
                if (isShowing) {
                    ((KeyguardManager) getSystemService(Activity.KEYGUARD_SERVICE)).newKeyguardLock(getPackageName()).disableKeyguard();
                    windowManager.removeViewImmediate(layout);
                    isShowing = false;
                }
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
            ((KeyguardManager) getSystemService(Activity.KEYGUARD_SERVICE)).newKeyguardLock(getPackageName()).reenableKeyguard();
            isShowing = false;
        }
        super.onDestroy();
    }

    String pinInPut = "";


}