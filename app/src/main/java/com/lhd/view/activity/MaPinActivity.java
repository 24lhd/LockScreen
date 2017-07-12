package com.lhd.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.lhd.demolock.R;

/**
 * Created by D on 7/12/2017.
 */

public class MaPinActivity extends AppCompatActivity {
    TextView txtPin;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ma_pin_ios);
        txtPin = (TextView) findViewById(R.id.tv_pin_input);
        txtPin.setText("");
    }

    String pinInPut="";

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

    private void checkPassCode() {

        Animation shake = AnimationUtils.loadAnimation(this, R.anim.error_pin);
        txtPin.startAnimation(shake);
    }

    public void onXoaClick(View view) {
        if (pinInPut.length() > 0){
            pinInPut = pinInPut.substring(0, pinInPut.length() - 1);
            txtPin.setText(pinInPut);
            txtPin.clearAnimation();
        }

    }
    public void onKhanCapClick(View view) {
        if (pinInPut.length() > 0){
            pinInPut = pinInPut.substring(0, pinInPut.length() - 1);
            txtPin.setText(pinInPut);
            txtPin.clearAnimation();
        }

    }
}
