package com.lhd.model;

import com.lhd.model.config.Config;
import com.lhd.model.listenner.OnStartViewSetringChangeListenner;
import com.lhd.model.object.OnOff;
import com.lhd.view.fragment.SettingFragment;
import com.orhanobut.hawk.Hawk;

/**
 * Created by D on 7/7/2017.
 */

public class SettingModelImpl implements SettingModel {
    public SettingModelImpl(SettingFragment settingFragment) {
        Hawk.init(settingFragment.getActivity()).build();
    }

    @Override
    public void setEnableLock(boolean b, OnStartViewSetringChangeListenner listenner) {
        Hawk.put(Config.ENABLE_LOCK, new OnOff(b));
//        listenner.onChange(Config.ENABLE_LOCK,b);
    }

    @Override
    public void setSound(boolean b, OnStartViewSetringChangeListenner listenner) {
        Hawk.put(Config.SOUND, new OnOff(b));
//        listenner.onChange(Config.SOUND,b);
    }

    @Override
    public void setVibration(boolean b, OnStartViewSetringChangeListenner listenner) {
        Hawk.put(Config.VIBRATION, new OnOff(b));
//        listenner.onChange(Config.VIBRATION,b);
    }

    @Override
    public void setFomatTime(boolean b, OnStartViewSetringChangeListenner listenner) {
        Hawk.put(Config.FOMAT_TIME, new OnOff(b));
//        listenner.onChange(Config.SOUND,b);
    }

//    @Override
//    public boolean getEnableLock() {
//        try {
//            return ((OnOff) Hawk.get(Config.ENABLE_LOCK)).isTrue();
//        } catch (NullPointerException e) {
//            setEnableLock(false);
//            return false;
//        }
//    }
//
//    @Override
//    public boolean getSound() {
//        try {
//            return ((OnOff) Hawk.get(Config.SOUND)).isTrue();
//        } catch (NullPointerException e) {
//            setEnableLock(false);
//            return false;
//        }
//    }
//
//    @Override
//    public boolean getVibration() {
//        try {
//            return ((OnOff) Hawk.get(Config.VIBRATION)).isTrue();
//        } catch (NullPointerException e) {
//            setEnableLock(false);
//            return false;
//        }
//    }
//
//    @Override
//    public boolean getFomatTime() {
//        try {
//            return ((OnOff) Hawk.get(Config.FOMAT_TIME)).isTrue();
//        } catch (NullPointerException e) {
//            setEnableLock(false);
//            return false;
//        }
//    }
}
