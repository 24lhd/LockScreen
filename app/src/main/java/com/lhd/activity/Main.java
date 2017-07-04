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
import com.lhd.fragment.SelectTypeLock;
import com.lhd.service.ListenOnOff;

public class Main extends AppCompatActivity {

    public static void showLog(String logContent) {
        Log.e("duong", logContent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main);
        checkPermisstion();
        setFragmentStart();
    }

    private void checkPermisstion() {
        xinQuyen(Manifest.permission.SYSTEM_ALERT_WINDOW, 1000);
        xinQuyen(Manifest.permission.DISABLE_KEYGUARD, 1100);
        // checkDrawOverlayPermission();
    }

    private void setFragmentStart() {
        getSupportFragmentManager().beginTransaction().replace(R.id.layout_container, new SelectTypeLock()).commit();
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
        /** check if we already  have permission to draw over other apps */
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!Settings.canDrawOverlays(this)) {
                /** if not construct intent to request permission */
                Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                        Uri.parse("package:" + getPackageName()));
                /** request permission via start activity for result */
                startActivityForResult(intent, REQUEST_CODE);
            } else {
                Intent intent = new Intent(this, ListenOnOff.class);
                startService(intent);
            }
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
                if (Settings.canDrawOverlays(this)) {
                    showLog("cho phep");
                } else {
                    showLog("khong cho phep");

                }
            }
        }
    }


}
