package com.lhd.activity;

import android.Manifest;
import android.app.Activity;
import android.app.KeyguardManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.lhd.demolock.R;
import com.lhd.service.ServiceLockOnOff;

public class MainActivity extends AppCompatActivity {

    public static void showLog(String logContent) {
        Log.e("duong", logContent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        xinQuyen(Manifest.permission.SYSTEM_ALERT_WINDOW, 1);
        xinQuyen(Manifest.permission.DISABLE_KEYGUARD, 1);
        xinQuyen(Manifest.permission.READ_CONTACTS, 1);
        KeyguardManager keyguardManager = (KeyguardManager) getSystemService(Activity.KEYGUARD_SERVICE);
        KeyguardManager.KeyguardLock lock = keyguardManager.newKeyguardLock(KEYGUARD_SERVICE);
        lock.disableKeyguard();
        Intent intent = new Intent(this, ServiceLockOnOff.class);
        startService(intent);
    }

    private void xinQuyen(String quyen, int indexResult) {
        if (ContextCompat.checkSelfPermission(this, quyen)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, quyen)) {
            } else {
                ActivityCompat.requestPermissions(this, new String[]{quyen}, indexResult);
            }
        }
    }

}
