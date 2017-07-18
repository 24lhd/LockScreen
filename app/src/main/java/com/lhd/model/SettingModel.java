package com.lhd.model;

import com.lhd.model.listenner.OnStateChangeListenner;

/**
 * Created by D on 7/7/2017.
 */

public interface SettingModel {
    public void setEnableLock(boolean b, OnStateChangeListenner listenner);

    public void setSound(boolean b, OnStateChangeListenner listenner);

    public void setVibration(boolean b, OnStateChangeListenner listenner);

    public void setFomatTime(boolean b, OnStateChangeListenner listenner);

//    public boolean getEnableLock();
//
//    public boolean getSound();
//
//    public boolean getVibration();
//
//    public boolean getFomatTime();
}
