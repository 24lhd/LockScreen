package com.lhd.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.lhd.demolock.R;
import com.lhd.fragment.Setting;
import com.lhd.fragment.Start;
import com.lhd.object.OnOff;
import com.lhd.service.FloatIcon;
import com.orhanobut.hawk.Hawk;

public class Main extends AppCompatActivity {

    public static final java.lang.String IS_START = "is_start";
    public static final java.lang.String IS_START_DIALOG_SETTING = "is_start_dialog_setting";
    public static final String IS_ENABLE_LOCK = "IS_ENABLE_LOCK";
    public static final String IS_SOUND = "IS_SOUND";
    public static final String IS_RUNG = "IS_RUNG";
    public static final String IS_24H = "IS_24H";
    public static final String IMAGE_BACKGROUND = "IMAGE_BACKGROUND";
    public static final String INDEX_SELECT_IMAGE_BACKGROUND_LOCK_SCREEN = "INDEX_SELECT_IMAGE_BACKGROUND_LOCK_SCREEN";
    public static final String QUYEN_VE_LEN_TREN = "QUYEN_VE_LEN_TREN";


    public static void showLog(String logContent) {
        Log.e("duong", logContent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Hawk.init(this).build();
        setContentView(R.layout.layout_main);
        checkPermisstion();
        try {
            if (((OnOff) Hawk.get(IS_START)).isTrue()) {
                setFragmentSetting();
            } else {
                setFragmentStart();
            }
        } catch (NullPointerException e) {
            setFragmentStart();
        }
        Intent intent = new Intent(this, FloatIcon.class);
        startService(intent);
        try {
            boolean b = ((OnOff) Hawk.get(QUYEN_VE_LEN_TREN)).isTrue();
        } catch (NullPointerException e) {
            Hawk.put(Main.QUYEN_VE_LEN_TREN, new OnOff(false));
        }

    }

    private void setFragmentSetting() {
        getSupportFragmentManager().beginTransaction().replace(R.id.layout_container, new Setting()).commit();
    }

    private void checkPermisstion() {
        xinQuyen(Manifest.permission.SYSTEM_ALERT_WINDOW, 1000);
        xinQuyen(Manifest.permission.DISABLE_KEYGUARD, 1100);
        if (Hawk.get(Main.QUYEN_VE_LEN_TREN)==null){
            Hawk.put(Main.QUYEN_VE_LEN_TREN,new OnOff(false));
        }
    }

    private void setFragmentStart() {
        getSupportFragmentManager().beginTransaction().replace(R.id.layout_container, new Start()).commit();
    }

    public void xinQuyen(String quyen, int indexResult) {
        if (ContextCompat.checkSelfPermission(this, quyen)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, quyen)) {
            } else {
                ActivityCompat.requestPermissions(this, new String[]{quyen}, indexResult);
            }
        }
    }

    public final static int REQUEST_CODE = 21;

    public void checkDrawOverlayPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            if (!Settings.canDrawOverlays(this)) {
                Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                        Uri.parse("package:" + getPackageName()));
                startActivityForResult(intent, REQUEST_CODE);
//            }
//            else {
//                showLog("chay roi");
//                Intent intent = new Intent(this, FloatIcon.class);
//                startService(intent);
//            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                Hawk.put(Main.QUYEN_VE_LEN_TREN, new OnOff(Settings.canDrawOverlays(this)));
                if (Settings.canDrawOverlays(this)) {
                    showLog("cho phep chay");
//                    Intent intent = new Intent(this, FloatIcon.class);
//                    startService(intent);
                } else {
                    showLog("khong cho phep");
//                    checkDrawOverlayPermission();
//                    Snackbar.make(getCurrentFocus(), "khong cho phep", Snackbar.LENGTH_LONG)
//                            .setAction("Action", null)
//                            .show();
                }
            }
        }
    }


}
