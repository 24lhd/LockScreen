package com.lhd.fragment;

import android.app.Activity;
import android.app.KeyguardManager;
import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.lhd.activity.Main;
import com.lhd.demolock.R;
import com.lhd.object.OnOff;
import com.orhanobut.hawk.Hawk;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by D on 7/3/2017.
 */

public class Setting extends Fragment {
    private View viewContent;
    private Main main;
    private Switch swEnableLock;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewContent = inflater.inflate(R.layout.layout_setting, null);
        main = (Main) getActivity();
        showDiaLogHuongDan();
        Hawk.init(main).build();
        return viewContent;
    }

    private void showDiaLogHuongDan() {
        try {
            if (((OnOff) Hawk.get(Main.IS_START_DIALOG_SETTING)).isTrue()) {
                setView();
                return;
            }
        } catch (NullPointerException e) {
        }
        View viewEnableLockScreen = View.inflate(main, R.layout.dialog_enable_lockscreen, null);
        final AlertDialog.Builder builder = new AlertDialog.Builder(main);
        builder.setView(viewEnableLockScreen);
        Button button = viewEnableLockScreen.findViewById(R.id.btGotIt_dialog);
        final AlertDialog alertDialog = builder.create();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Hawk.put(Main.IS_START_DIALOG_SETTING, new OnOff(true));
                alertDialog.dismiss();
                setView();
            }
        });
        alertDialog.show();
    }

    private void setView() {
        swEnableLock = viewContent.findViewById(R.id.sw_enable_lock);
        Switch swSound = viewContent.findViewById(R.id.sw_sound);
        Switch swRung = viewContent.findViewById(R.id.sw_rung);
        CheckBox cbx24h = viewContent.findViewById(R.id.cbx_24h);
        viewContent.findViewById(R.id.chon_cau_hoi).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startSelectAnswer();
            }
        });
        viewContent.findViewById(R.id.set_im_bg_lock_screen).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startSelectImgaeBackground();
            }
        });
        try {
            swEnableLock.setChecked(((OnOff) Hawk.get(Main.IS_ENABLE_LOCK)).isTrue());

        } catch (NullPointerException e) {
        }
        try {
            swSound.setChecked(((OnOff) Hawk.get(Main.IS_SOUND)).isTrue());
        } catch (NullPointerException e) {
        }
        try {
            swRung.setChecked(((OnOff) Hawk.get(Main.IS_RUNG)).isTrue());
        } catch (NullPointerException e) {
        }
        try {
            cbx24h.setChecked(((OnOff) Hawk.get(Main.IS_24H)).isTrue());
        } catch (NullPointerException e) {
        }
        swEnableLock.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                Hawk.put(Main.IS_ENABLE_LOCK, new OnOff(b));
                if (!((OnOff) Hawk.get(Main.IS_ENABLE_LOCK)).isTrue()) {
                    ((KeyguardManager) main.getSystemService(Activity.KEYGUARD_SERVICE)).newKeyguardLock(main.getPackageName()).reenableKeyguard();
                }
            }
        });
        swSound.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                Hawk.put(Main.IS_SOUND, new OnOff(b));
                playSound(main);
            }
        });
        swRung.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                Hawk.put(Main.IS_RUNG, new OnOff(b));
                playVibiration(main);
            }
        });
        cbx24h.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                Hawk.put(Main.IS_24H, new OnOff(b));
                showTime();
            }
        });
    }

    private void startSelectImgaeBackground() {
        main.getSupportFragmentManager().beginTransaction().replace(R.id.layout_container, new SelectImage()).commit();
    }

    private void startSelectAnswer() {
        main.getSupportFragmentManager().beginTransaction().replace(R.id.layout_container, new Answer()).commit();
    }

    private void showTime() {
        if (((OnOff) Hawk.get(Main.IS_24H)).isTrue()) time24();
        else time12();
    }

    public static String time12() {
        Date date = new Date();
        date.setHours(date.getHours());
        SimpleDateFormat simpDate;
        simpDate = new SimpleDateFormat("h:mm");
        Main.showLog(simpDate.format(date));
        return simpDate.format(date);
    }


    public static String time24() {
        Date date = new Date();
        date.setHours(date.getHours());
        SimpleDateFormat simpDate;
        simpDate = new SimpleDateFormat("HH:mm");
        Main.showLog(simpDate.format(date));
        return simpDate.format(date);
    }
    public static String date() {
        Date date = new Date();
        String dayOfTheWeek = (String) DateFormat.format("EEEE", date); // Thursday
        String day          = (String) DateFormat.format("dd",   date); // 20
        String monthString  = (String) DateFormat.format("MMM",  date); // Jun
        String monthNumber  = (String) DateFormat.format("MM",   date); // 06
        String year         = (String) DateFormat.format("yyyy", date); // 2013
        return dayOfTheWeek+" "+day+" "+monthString+" "+year ;
    }

    private void playVibiration(Context context) {
        if (((OnOff) Hawk.get(Main.IS_RUNG)).isTrue()) {
            Vibrator v = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
            // Vibrate for 500 milliseconds
            v.vibrate(500);
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
        if (((OnOff) Hawk.get(Main.IS_SOUND)).isTrue()) {
            MediaPlayer mp = MediaPlayer.create(context, R.raw.a);
            mp.start();
        }

    }
}
