package com.lhd.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.lhd.demolock.R;

/**
 * Created by D on 7/6/2017.
 */

public class SecurityQuestion extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_answer);
        setView();
    }

    private void setView() {

    }
}
