package com.lhd.view.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.lhd.activity.Main;
import com.lhd.demolock.R;
import com.lhd.model.config.Config;
import com.lhd.model.object.OnOff;
import com.lhd.presenter.SettingPresenter;
import com.lhd.presenter.SettingPresenterImpl;
import com.lhd.view.SettingView;
import com.lhd.view.activity.ChangePinCode2;
import com.lhd.view.activity.SelectTypeLock;
import com.lhd.view.activity.SetImageBackground;
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
    Switch swNoti;
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
        swNoti = viewContent.findViewById(R.id.sw_notity);
        swNoti.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) settingPresenter.setOnNoti();
                else
                    settingPresenter.setOffNoti();
            }
        });
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
        viewContent.findViewById(R.id.thay_cau_hoi).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changePinCode2();
//                setPinCodeCap2();
//                checkPinDuPhong();
            }
        });
        loadStateView();
        return viewContent;
    }

    private void checkPinDuPhong() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View viewContent = View.inflate(getContext(), R.layout.check_pin_code, null);
        builder.setView(viewContent);
        Button btnSubmit = viewContent.findViewById(R.id.check_pin_code_btn_mo_khoa);
        final EditText edtInput = viewContent.findViewById(R.id.check_pin_code_pin_input);
        final AlertDialog alertDialog = builder.create();
        alertDialog.setView(viewContent);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String input = edtInput.getText().toString();
                String pass = Hawk.get(Config.PIN_CAP_2);

                if (input.equals(pass)) {

                } else {
                    Toast.makeText(getContext(), "Sai mã pin", Toast.LENGTH_SHORT).show();
                    alertDialog.dismiss();
                }
            }
        });
        alertDialog.show();
    }

    private void changePinCode2() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View viewContent = View.inflate(getContext(), R.layout.change_pin_code, null);
        builder.setView(viewContent);
        Button btnSubmit = viewContent.findViewById(R.id.change_pin_code_btn_change);
        final EditText edtInput1 = viewContent.findViewById(R.id.change_pin_code_pin_1);
        final EditText edtInput2 = viewContent.findViewById(R.id.change_pin_code_pin_2);
        final EditText edtInputCu = viewContent.findViewById(R.id.change_pin_code_pin_cu);
        final AlertDialog alertDialog = builder.create();
        alertDialog.setView(viewContent);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String input1 = edtInput1.getText().toString();
                String input2 = edtInput2.getText().toString();
                String inputCu = edtInputCu.getText().toString();
                if (input1.length() < 4)
                    Toast.makeText(getContext(), "Bạn hãy nhập đầy đủ 4 kí tự", Toast.LENGTH_SHORT).show();
                else if (!input1.equals(input2)) {
                    Toast.makeText(getContext(), "Mã pin nhập không giống nhau", Toast.LENGTH_SHORT).show();
                } else if (!inputCu.equals((String) Hawk.get(Config.PIN_CAP_2))) {
                    Toast.makeText(getContext(), "Mã pin cũ của bạn không đúng", Toast.LENGTH_SHORT).show();
                } else {
                    Hawk.put(Config.PIN_CAP_2, input1);
                    Toast.makeText(getContext(), "Đã thay đổi mã pin dự phòng", Toast.LENGTH_SHORT).show();
                    alertDialog.dismiss();
                }
            }
        });
        alertDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
            }
        });
        alertDialog.show();
    }

    private void setPinCodeCap2() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View viewContent = View.inflate(getContext(), R.layout.set_pin_cap_2, null);
        builder.setView(viewContent);
        Button btnSubmit = viewContent.findViewById(R.id.set_pin_cap2_btn_submit);
        final EditText edtInput1 = viewContent.findViewById(R.id.set_pin_cap2_txt_input_ma_pin_1);
        final EditText edtInput2 = viewContent.findViewById(R.id.set_pin_cap2_txt_input_ma_pin_2);
        final AlertDialog alertDialog = builder.create();
        alertDialog.setView(viewContent);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String input1 = edtInput1.getText().toString();
                String input2 = edtInput2.getText().toString();
                if (input1.length() < 4)
                    Toast.makeText(getContext(), "Bạn hãy nhập đầy đủ 4 kí tự", Toast.LENGTH_SHORT).show();
                else if (!input1.equals(input2)) {
                    Toast.makeText(getContext(), "Mã pin nhập không giống nhau", Toast.LENGTH_SHORT).show();
                } else {
                    Hawk.put(Config.PIN_CAP_2, input1);
                    Toast.makeText(getContext(), "Đã tạo mật khẩu cấp 2", Toast.LENGTH_SHORT).show();
                    alertDialog.dismiss();
                }
            }
        });
        alertDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                Toast.makeText(getContext(), "alertDialog onDismiss", Toast.LENGTH_SHORT).show();
            }
        });
        alertDialog.show();
    }

    private void startChangePinCode2() {
        Intent intent = new Intent(getActivity(), ChangePinCode2.class);
        getActivity().startActivity(intent);
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
        settingPresenter.setNotifi();
    }

    @Override
    public void showAds() {

    }

    @Override
    public void loadStateView() {
        try {
            swEnableLock.setChecked(((OnOff) Hawk.get(Config.ENABLE_LOCK)).isTrue());
            Main.showLog("hehe " + ((OnOff) Hawk.get(Config.ENABLE_LOCK)).isTrue());
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
        try {
            swNoti.setChecked(((OnOff) Hawk.get(Config.SHOW_NOTI)).isTrue());
        } catch (NullPointerException e) {
            Hawk.put(Config.SHOW_NOTI, new OnOff(false));

        }


    }

    @Override
    public void onNoti() {

    }

    @Override
    public void offNoti() {

    }


}
