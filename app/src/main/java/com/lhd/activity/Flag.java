package com.lhd.activity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.lhd.demolock.R;

/**
 * Created by D on 7/4/2017.
 */

public class Flag extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
////            if (!Settings.canDrawOverlays(this)) {
//            Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
//                    Uri.parse("package:" + getPackageName()));
//            startActivityForResult(intent, REQUEST_CODE);
        final View viewEnableLockScreen = View.inflate(this, R.layout.dialog_enable_lockscreen, null);
//        setContentView(viewEnableLockScreen);
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(viewEnableLockScreen);
        Button button = viewEnableLockScreen.findViewById(R.id.btGotIt_dialog);
        final AlertDialog alertDialog = builder.create();
//            alertDialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_OVERLAY);
//            alertDialog.getWindow().addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });
//        setContentView(alertDialog);
        alertDialog.show();
//        }
    }


}
