package com.lhd.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBarActivity;

import com.eftimoff.patternview.PatternView;
import com.lhd.demolock.R;

/**
 * Created by D on 7/12/2017.
 */

public class DisHome extends ActionBarActivity {
    PatternView patternView;
    private String patternString;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.demo);
//        Lock9View lock9View = (Lock9View) findViewById(R.id.lock_9_view);
//        lock9View.setCallBack(new Lock9View.CallBack() {
//            @Override
//            public void onFinish(String password) {
//                Toast.makeText(DisHome.this, password, Toast.LENGTH_SHORT).show();
//            }
//        });

    }
}