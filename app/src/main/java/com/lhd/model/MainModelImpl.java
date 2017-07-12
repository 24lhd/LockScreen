package com.lhd.model;

import com.lhd.model.config.Config;
import com.lhd.model.object.OnOff;
import com.lhd.view.activity.MainActivity;
import com.orhanobut.hawk.Hawk;

/**
 * Created by D on 7/7/2017.
 */

public class MainModelImpl implements MainModel {

    @Override
    public boolean isDidShowStartView(MainActivity contextMainView) {
        try {
            Hawk.init(contextMainView).build();
            boolean is=((OnOff) Hawk.get(Config.SHOW_START)).isTrue();
            return true;
        }catch (NullPointerException ex){
            Hawk.put(Config.SHOW_START,new OnOff(false));
            return false;
        }
    }
//    @Override
//    public void setDidShowStartView() {
//        Hawk.put(Config.SHOW_START,new OnOff(true));
//    }
}
