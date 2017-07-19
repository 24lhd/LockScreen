package com.lhd.view.service;

import android.app.Activity;
import android.app.KeyguardManager;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.PixelFormat;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.IBinder;
import android.os.Vibrator;
import android.support.v4.widget.SlidingPaneLayout;
import android.text.format.DateFormat;
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

import java.text.SimpleDateFormat;
import java.util.Date;

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

    public String time12() {
        Date date = new Date();
        date.setHours(date.getHours());
        SimpleDateFormat simpDate;
        simpDate = new SimpleDateFormat("h:mm");
        Main.showLog(simpDate.format(date));
        return simpDate.format(date);
    }


    public String time24() {
        Date date = new Date();
        date.setHours(date.getHours());
        SimpleDateFormat simpDate;
        simpDate = new SimpleDateFormat("HH:mm");
        Main.showLog(simpDate.format(date));
        return simpDate.format(date);
    }

    public String date() {
        Date date = new Date();
        String dayOfTheWeek = (String) DateFormat.format("EEEE", date); // Thursday
        String day = (String) DateFormat.format("dd", date); // 20
        String monthString = (String) DateFormat.format("MMM", date); // Jun
        String monthNumber = (String) DateFormat.format("MM", date); // 06
        String year = (String) DateFormat.format("yyyy", date); // 2013
        return dayOfTheWeek + " " + day + " " + monthString + " " + year;
    }

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
        if (((OnOff) Hawk.get(Config.SOUND)).isTrue()) playSound(LockScreen.this);
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
        SlidingPaneLayout slidingPaneLayout;
        LayoutInflater inflater = LayoutInflater.from(this);
        if (((OnOff) Hawk.get(Config.VIBRATION)).isTrue()) {
            layout = inflater.inflate(R.layout.ls_pattern_large_vibrate_layout, null);
            Lock9View lock9View;
            TextView textTime = layout.findViewById(R.id.txt_time_ls_pattern_large_vibrate_layout);
            if (((OnOff) Hawk.get(Config.FOMAT_TIME)).isTrue())
                textTime.setText(time24());
            else textTime.setText(time12());
            TextView textDate = layout.findViewById(R.id.txt_date_ls_pattern_large_vibrate_layout);
            textDate.setText(date());
            lock9View = (Lock9View) layout.findViewById(R.id.lock_9_view_ls_pattern_large_vibrate_layout);
            imgBackground = layout.findViewById(R.id.im_bg_lockscreen_ls_pattern_large_vibrate_layout);
            ImageView imgBackgroundNone = layout.findViewById(R.id.im_bg_none_ls_pattern_large_vibrate_layout);
            imgBackgroundNone.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
            loadBackground(imgBackground);
//        textView.setText("Vẽ mấu hình mở khóa của bạn");
            lock9View.setCallBack(new Lock9View.CallBack() {
                                      public void onFinish(String password) {
                                          if (((LockType) Hawk.get(Config.TYPE_LOCK)).getPass().equals(password))

                                              unLock();
                                      }
                                  }
            );
        } else {
            layout = inflater.inflate(R.layout.ls_pattern_large_no_vibrate_layout, null);
            Lock9View lock9View;
            TextView textTime = layout.findViewById(R.id.txt_time_ls_pattern_large_no_vibrate_layout);
            if (((OnOff) Hawk.get(Config.FOMAT_TIME)).isTrue())
                textTime.setText(time24());
            else textTime.setText(time12());
            TextView textDate = layout.findViewById(R.id.txt_date_ls_pattern_large_no_vibrate_layout);
            textDate.setText(date());
            lock9View = (Lock9View) layout.findViewById(R.id.lock_9_view_ls_pattern_large_no_vibrate_layout);
            imgBackground = layout.findViewById(R.id.im_bg_lockscreen_ls_pattern_large_no_vibrate_layout);
            ImageView imgBackgroundNone = layout.findViewById(R.id.im_bg_none_ls_pattern_large_no_vibrate_layout);
            imgBackgroundNone.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
            loadBackground(imgBackground);
//        textView.setText("Vẽ mấu hình mở khóa của bạn");
            lock9View.setCallBack(new Lock9View.CallBack() {
                                      public void onFinish(String password) {
                                          if (((LockType) Hawk.get(Config.TYPE_LOCK)).getPass().equals(password))
                                              unLock();
                                      }
                                  }
            );
        }

    }

    private void playSound(Context context) {
//        try {
//            Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
//            Ringtone r = RingtoneManager.getRingtone(main.getApplicationContext(), notification);
//            r.play();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        MediaPlayer mp = MediaPlayer.create(context, R.raw.a);
        mp.start();

    }

    TextView txtPin;

    private void setTypeMaPin() {
        LayoutInflater inflater = LayoutInflater.from(this);
        layout = inflater.inflate(R.layout.ls_ma_pin_to, null);
        ImageView imbg = layout.findViewById(R.id.im_bg_lockscreen_ls_ma_pin_to);
        ImageView imbgNone = layout.findViewById(R.id.im_bg_lockscreen_none_ls_ma_pin_to);
        imbgNone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        loadBackground(imbg);
        txtPin = (TextView) layout.findViewById(R.id.tv_pin_input_screen_lock_ls_ma_pin_to);
        txtPin.setText("");
        ImageView txtXoaPin = (ImageView) layout.findViewById(R.id.tv_xoa_pin_sc_ls_ma_pin_to);
        txtXoaPin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (((OnOff) Hawk.get(Config.VIBRATION)).isTrue()) playVibiration(LockScreen.this);
                if (pinInPut.length() > 0) {
                    pinInPut = pinInPut.substring(0, pinInPut.length() - 1);
                    txtPin.setText(pinInPut);
                    txtPin.clearAnimation();
                }
            }
        });
        ImageView txtKhanCap = (ImageView) layout.findViewById(R.id.tv_khan_cap_sc_ls_ma_pin_to);
        txtKhanCap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (((OnOff) Hawk.get(Config.VIBRATION)).isTrue()) playVibiration(LockScreen.this);
                String number = "7777777777";
                Uri call = Uri.parse("tel:" + number);
                Intent surf = new Intent(Intent.ACTION_CALL, call);
                surf.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_NO_USER_ACTION);
                startActivity(surf);
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

    private void playVibiration(Context context) {
        Vibrator v = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        // Vibrate for 500 milliseconds
        v.vibrate(30);
    }

    @Override
    public void onClick(View view) {
        if (((OnOff) Hawk.get(Config.VIBRATION)).isTrue()) playVibiration(LockScreen.this);
        String pin = (String) view.getTag();
        if (pinInPut.length() < 4) {
            pinInPut = txtPin.getText().toString() + pin;
            txtPin.setText(pinInPut);
            txtPin.clearAnimation();
        }
        if (pinInPut.length() == 4) {
            Main.showLog(pinInPut);
            if (((LockType) Hawk.get(Config.TYPE_LOCK)).getPass().equals(pinInPut)) {
                Main.showLog("unlock");
                pinInPut = "";

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
                            if (Config.MAU_HINH_SMALL.contains(((LockType) Hawk.get(Config.TYPE_LOCK)).getName()))
                                setTypePatternSmall();
                            else if (Config.MAU_HINH_TO.contains(((LockType) Hawk.get(Config.TYPE_LOCK)).getName()))
                                setTypePatternTo();
                            else if (Config.MA_PIN.contains(((LockType) Hawk.get(Config.TYPE_LOCK)).getName()))
                                setTypeMaPin();
                            else setTypeNone();
                        } catch (NullPointerException e) {
                            Main.showLog(e.getMessage());
                            setTypeNone();
                        }
                        windowManager.addView(layout, params);
                        isShowing = true;
                    }
                } catch (NullPointerException e) {
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

    private void setTypePatternSmall() {
        SlidingPaneLayout slidingPaneLayout;
        LayoutInflater inflater = LayoutInflater.from(this);
        if (((OnOff) Hawk.get(Config.VIBRATION)).isTrue()) {
            layout = inflater.inflate(R.layout.ls_pattern_small_vibrate_layout, null);
            Lock9View lock9View;
            TextView textTime = layout.findViewById(R.id.txt_time_ls_pattern_small_vibrate_layout);
            if (((OnOff) Hawk.get(Config.FOMAT_TIME)).isTrue())
                textTime.setText(time24());
            else textTime.setText(time12());
            TextView textDate = layout.findViewById(R.id.txt_date_ls_pattern_small_vibrate_layout);
            textDate.setText(date());
            lock9View = (Lock9View) layout.findViewById(R.id.lock_9_view_ls_pattern_small_vibrate_layout);
            imgBackground = layout.findViewById(R.id.im_bg_lockscreen_ls_pattern_small_vibrate_layout);
            ImageView imgBackgroundNone = layout.findViewById(R.id.im_bg_none_ls_pattern_small_vibrate_layout);
            imgBackgroundNone.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
            loadBackground(imgBackground);
//        textView.setText("Vẽ mấu hình mở khóa của bạn");
            lock9View.setCallBack(new Lock9View.CallBack() {
                                      public void onFinish(String password) {
                                          if (((LockType) Hawk.get(Config.TYPE_LOCK)).getPass().equals(password))
                                              unLock();
                                      }
                                  }
            );
        } else {
            layout = inflater.inflate(R.layout.ls_pattern_small_no_vibrate_layout, null);
            Lock9View lock9View;
            TextView textTime = layout.findViewById(R.id.txt_time_ls_pattern_small_no_vibrate_layout);
            if (((OnOff) Hawk.get(Config.FOMAT_TIME)).isTrue())
                textTime.setText(time24());
            else textTime.setText(time12());
            TextView textDate = layout.findViewById(R.id.txt_date_ls_pattern_small_no_vibrate_layout);
            textDate.setText(date());
            lock9View = (Lock9View) layout.findViewById(R.id.lock_9_view_ls_pattern_small_no_vibrate_layout);
            imgBackground = layout.findViewById(R.id.im_bg_lockscreen_ls_pattern_small_no_vibrate_layout);
            ImageView imgBackgroundNone = layout.findViewById(R.id.im_bg_none_ls_pattern_small_no_vibrate_layout);
            imgBackgroundNone.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
            loadBackground(imgBackground);
//        textView.setText("Vẽ mấu hình mở khóa của bạn");
            lock9View.setCallBack(new Lock9View.CallBack() {
                                      public void onFinish(String password) {
                                          if (((LockType) Hawk.get(Config.TYPE_LOCK)).getPass().equals(password))
                                              unLock();
                                      }
                                  }
            );
        }
    }


    @Override
    public void onDestroy() {
        if (mReceiver != null) {
            unregisterReceiver(mReceiver);
        }
        if (isShowing) {
            windowManager.removeViewImmediate(layout);
            ((KeyguardManager) getSystemService(Activity.KEYGUARD_SERVICE)).newKeyguardLock(getPackageName()).reenableKeyguard();
            isShowing = false;
        }
        super.onDestroy();
    }

    String pinInPut = "";


}