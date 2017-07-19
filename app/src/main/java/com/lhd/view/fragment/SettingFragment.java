package com.lhd.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.lhd.view.activity.SelectTypeLock;
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
    Switch swRung;
    Switch swSound;
    CheckBox cbTime;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        viewContent = inflater.inflate(R.layout.layout_setting, null);
        settingPresenter = new SettingPresenterImpl(this);
        swEnableLock = viewContent.findViewById(R.id.sw_enable_lock);
        swRung = viewContent.findViewById(R.id.sw_rung);
        swSound = viewContent.findViewById(R.id.sw_sound);
        cbTime = viewContent.findViewById(R.id.cbx_24h);
        swEnableLock.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) settingPresenter.setOnLockScreen();
                else
                    settingPresenter.setOffLockScreen();
            }
        });
        swRung.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) settingPresenter.setOnVibration();
                else
                    settingPresenter.setOffVibration();
            }
        });
        swSound.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) settingPresenter.setOnSound();
                else
                    settingPresenter.setOffSound();
            }
        });
        cbTime.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) settingPresenter.set24h();
                else
                    settingPresenter.set12h();
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
        loadStateView();
        return viewContent;
    }

    @Override
    public void onLockScreen() {
        intent = new Intent(getActivity(), LockScreen.class);
        getActivity().startService(intent);
    }

    @Override
    public void offLockScreen() {
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
        settingPresenter.setOnSound();
    }

    @Override
    public void offSound() {
        settingPresenter.setOffSound();
    }

    @Override
    public void onVibration() {
        settingPresenter.setOnVibration();
    }

    @Override
    public void offVibration() {
        settingPresenter.setOffVibration();
    }

    @Override
    public void set24h() {
        settingPresenter.set24h();
    }

    @Override
    public void set12h() {
        settingPresenter.set12h();
    }

    @Override
    public void notificationConfig() {

    }

    @Override
    public void showAds() {

    }

    @Override
    public void loadStateView() {

        try {
            swEnableLock.setChecked(((OnOff) Hawk.get(Config.ENABLE_LOCK)).isTrue());
        } catch (NullPointerException e) {
            Hawk.put(Config.ENABLE_LOCK, new OnOff(false));
        }
        try {
            swRung.setChecked(((OnOff) Hawk.get(Config.VIBRATION)).isTrue());
        } catch (NullPointerException e) {
            Hawk.put(Config.VIBRATION, new OnOff(false));
        }
        try {
            swSound.setChecked(((OnOff) Hawk.get(Config.SOUND)).isTrue());
        } catch (NullPointerException e) {
            Hawk.put(Config.SOUND, new OnOff(false));

        }
        try {
            cbTime.setChecked(((OnOff) Hawk.get(Config.FOMAT_TIME)).isTrue());
        } catch (NullPointerException e) {
            Hawk.put(Config.FOMAT_TIME, new OnOff(false));

        }


    }


}
