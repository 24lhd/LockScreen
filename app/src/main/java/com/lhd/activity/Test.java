package com.lhd.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.lhd.demolock.R;
import com.lhd.model.object.OnOff;
import com.orhanobut.hawk.Hawk;

/**
 * Created by D on 7/3/2017.
 */

public class Test extends AppCompatActivity {
    private WindowManager windowManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (Settings.canDrawOverlays(this)) {
                final View viewEnableLockScreen = View.inflate(this, R.layout.dialog_enable_lockscreen, null);
                final AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setView(viewEnableLockScreen);
                Button button = viewEnableLockScreen.findViewById(R.id.btGotIt_dialog);
                final AlertDialog alertDialog = builder.create();
                alertDialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
                alertDialog.getWindow().addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Hawk.put(Main.IS_START_DIALOG_SETTING, new OnOff(true));
                        alertDialog.dismiss();
                    }
                });
                alertDialog.show();
//            Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
//                    Uri.parse("package:" + getPackageName()));
//            startActivityForResult(intent, REQUEST_CODE);
//            startActivityForResult(new Intent(this,Flag.class),12);

        }

        }

    }
}