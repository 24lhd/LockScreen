package com.lhd.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.lhd.activity.Main;
import com.lhd.activity.SelectTypeLock;
import com.lhd.activity.SetImageBackground;
import com.lhd.demolock.R;
import com.lhd.model.config.Config;
import com.lhd.model.object.OnOff;
import com.lhd.presenter.SettingPresenter;
import com.lhd.presenter.SettingPresenterImpl;
import com.lhd.view.SettingView;
import com.lhd.view.service.LockScreen;
import com.orhanobut.hawk.Hawk;

/**
 * Created by D on 7/7/2017.
 */

public class SettingFragment extends Fragment implements SettingView {
    Intent intent;
    private SettingPresenter settingPresenter;
    private View viewContent;
    Switch swEnableLock;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        viewContent = inflater.inflate(R.layout.layout_setting, null);
        settingPresenter = new SettingPresenterImpl(this);
        swEnableLock = viewContent.findViewById(R.id.sw_enable_lock);
        Main.showLog("SettingPresenterImpl");
        swEnableLock.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                Main.showLog("lol " + b);
                if (b) settingPresenter.setOnLockScreen();
                else
                    settingPresenter.setOffLockScreen();
            }
        });
        viewContent.findViewById(R.id.setting).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startSelectTypeLock();
            }
        });
        viewContent.findViewById(R.id.set_im_bg_lock_screen).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startSetWallpager();
            }
        });
        viewContent.findViewById(R.id.chon_cau_hoi).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                startSelectAnswer();
            }
        });
        return viewContent;
    }

    @Override
    public void onLockScreen() {
        Hawk.put(Config.ENABLE_LOCK, new OnOff(true));
        intent = new Intent(getActivity(), LockScreen.class);

        getActivity().startService(intent);
    }

    @Override
    public void offLockScreen() {
        Hawk.put(Config.ENABLE_LOCK, new OnOff(false));
        intent = new Intent(getActivity(), LockScreen.class);

        getActivity().stopService(intent);
    }

    @Override
    public void startSelectTypeLock() {
        Intent intent = new Intent(getActivity(), SelectTypeLock.class);
        getActivity().startActivity(intent);
        getActivity().overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);
    }

    @Override
    public void startSetWallpager() {
        Intent intent = new Intent(getActivity(), SetImageBackground.class);
        getActivity().startActivity(intent);
        getActivity().overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);
    }

    @Override
    public void onSound() {

    }

    @Override
    public void offSound() {

    }

    @Override
    public void onVibration() {

    }

    @Override
    public void offVibration() {

    }

    @Override
    public void set24h() {

    }

    @Override
    public void set12h() {

    }

    @Override
    public void notificationConfig() {

    }

    @Override
    public void showAds() {

    }


}
