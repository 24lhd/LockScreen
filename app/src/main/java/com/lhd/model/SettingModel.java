package com.lhd.model;

import com.lhd.model.listenner.OnStartViewSetringChangeListenner;

/**
 * Created by D on 7/7/2017.
 */

public interface SettingModel {
    public void setEnableLock(boolean b, OnStartViewSetringChangeListenner listenner);

    public void setSound(boolean b, OnStartViewSetringChangeListenner listenner);

    public void setVibration(boolean b, OnStartViewSetringChangeListenner listenner);

    public void setFomatTime(boolean b, OnStartViewSetringChangeListenner listenner);

//    public boolean getEnableLock();
//
//    public boolean getSound();
//
//    public boolean getVibration();
//
//    public boolean getFomatTime();
}
