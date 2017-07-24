package com.lhd.presenter;

import android.app.Activity;
import android.app.KeyguardManager;

import com.lhd.model.SettingModel;
import com.lhd.model.SettingModelImpl;
import com.lhd.model.listenner.OnStateChangeListenner;
import com.lhd.view.fragment.SettingFragment;

/**
 * Created by D on 7/7/2017.
 */

public class SettingPresenterImpl implements SettingPresenter, OnStateChangeListenner {
    private SettingFragment settingFragment;
    private SettingModel settingModel;

    public SettingPresenterImpl(SettingFragment settingFragment) {
        this.settingFragment = settingFragment;
        settingModel = new SettingModelImpl(settingFragment);
    }

    @Override
    public void setOnLockScreen() {
        ((KeyguardManager) settingFragment.getActivity().getSystemService(Activity.KEYGUARD_SERVICE)).newKeyguardLock(settingFragment.getActivity().getPackageName()).disableKeyguard();
        settingFragment.onLockScreen();
        settingModel.setEnableLock(true, this);
    }

    @Override
    public void setOffLockScreen() {
        ((KeyguardManager) settingFragment.getActivity().getSystemService(Activity.KEYGUARD_SERVICE)).newKeyguardLock(settingFragment.getActivity().getPackageName()).reenableKeyguard();
        settingFragment.offLockScreen();
        settingModel.setEnableLock(false, this);
    }

    @Override
    public void startSelectTypeLock() {
        settingFragment.startSelectTypeLock();
    }

    @Override
    public void startSetWallpager() {
        settingFragment.startSetWallpager();
    }

    @Override
    public void setOnSound() {
        settingModel.setSound(true, this);
    }

    @Override
    public void setOffSound() {
        settingModel.setSound(false, this);
    }

    @Override
    public void setOnVibration() {
        settingModel.setVibration(true, this);
    }

    @Override
    public void setOffVibration() {
        settingModel.setVibration(false, this);
    }

    @Override
    public void set24h() {
        settingModel.setFomatTime(true, this);
    }

    @Override
    public void set12h() {
        settingModel.setFomatTime(false, this);
    }

    @Override
    public void setNotifi() {

    }

    @Override
    public void setAds() {

    }

    @Override
    public void setLoadStateView() {
        settingFragment.loadStateView();
    }

    @Override
    public void setOnNoti() {
        settingModel.setNoti(true,this);
    }

    @Override
    public void setOffNoti() {
        settingModel.setNoti(false,this);
    }


    @Override
    public void onChange(String tag, boolean b) {
        settingFragment.loadStateView();
    }

}
