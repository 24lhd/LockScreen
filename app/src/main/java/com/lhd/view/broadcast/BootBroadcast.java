package com.lhd.view.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.lhd.model.config.Config;
import com.lhd.model.object.OnOff;
import com.lhd.view.service.LockScreen;
import com.orhanobut.hawk.Hawk;

/**
 * Created by D on 7/19/2017.
 */

public class BootBroadcast extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(Intent.ACTION_BOOT_COMPLETED)){
            Hawk.init(context).build();

            if (  ((OnOff)Hawk.get(Config.ENABLE_LOCK)).isTrue()) {
                context.startService(new Intent(context, LockScreen.class));
                Toast.makeText(context,"Bật lock nè",Toast.LENGTH_LONG);
            }

        }

    }
}
