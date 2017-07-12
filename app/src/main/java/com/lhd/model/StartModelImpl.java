package com.lhd.model;

import android.content.Context;

import com.lhd.model.config.Config;
import com.lhd.model.object.OnOff;
import com.lhd.view.MainView;
import com.orhanobut.hawk.Hawk;

/**
 * Created by D on 7/7/2017.
 */

public class StartModelImpl implements StartModel{
    @Override
    public void putDidShowStartView(boolean b, MainView mainView) {
        Hawk.init((Context) mainView).build();
        Hawk.put(Config.SHOW_START,new OnOff(b));

    }
}
