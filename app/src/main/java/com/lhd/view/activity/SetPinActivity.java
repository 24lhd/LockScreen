package com.lhd.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.lhd.activity.Main;
import com.lhd.demolock.R;

/**
 * Created by D on 7/12/2017.
 */

public class SetPinActivity extends AppCompatActivity {
    TextView txtPin;
    TextView txtInputPin;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.set_pin_layout);
        txtPin = (TextView) findViewById(R.id.tv_pin_input);
        txtInputPin = (TextView) findViewById(R.id.txt_noti_input_pin_lock);
        txtInputPin.setText("Nhập mã pin gồm 4 chữ số");
        txtPin.setText("");
    }


    public void onPinClick(View view) {
        String pin = (String) view.getTag();
        if (pinInPut.length() < 4) {
            pinInPut = txtPin.getText().toString() + pin;
            txtPin.setText(pinInPut);
            Log.e("duong", pinInPut);
            txtPin.clearAnimation();
        }
        if (pinInPut.length() == 4)
            checkPassCode();


    }

    String pinInPut = "";
    String password1 = "";
    String password2 = "";

    private void checkPassCode() {
        if (password1.equals("")) {
            password1 = pinInPut;
            txtInputPin.setText("Xác nhận lại mã pin của bạn");
            pinInPut = "";
            txtPin.setText("");
        } else if (password2.equals("")) {
            password2 = pinInPut;
        }
        Main.showLog("password1 " + password1);
        Main.showLog("password2 " + password2);
        if (password2.equals(password1) && !password1.equals("") && !password2.equals("")) {
            txtInputPin.setText("Đã lưu lại mã pin của bạn");
            txtPin.setText("");
        } else if (!password2.equals(password1) && !password1.equals("") && !password2.equals("")) {
            txtInputPin.setText("Không khớp, Tạo lại mã pin của bạn");
            Animation shake = AnimationUtils.loadAnimation(this, R.anim.error_pin);
            txtPin.startAnimation(shake);
            password2 = "";
            password1 = "";
            pinInPut = "";
            txtPin.setText("");
        }
    }

    public void onXoaClick(View view) {
        if (pinInPut.length() > 0) {
            pinInPut = pinInPut.substring(0, pinInPut.length() - 1);
            txtPin.setText(pinInPut);
            txtPin.clearAnimation();
        }
    }

    public void onRefeshClick(View view) {
        txtInputPin.setText("Nhập mã pin gồm 4 chữ số");
        pinInPut = "";
        txtPin.setText(pinInPut);
        password1 = "";
        password2 = "";
    }

    public void onKhanCapClick(View view) {
        if (pinInPut.length() > 0) {
            pinInPut = pinInPut.substring(0, pinInPut.length() - 1);
            txtPin.setText(pinInPut);
            txtPin.clearAnimation();
        }

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);
    }
}
