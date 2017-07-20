package com.lhd.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.widget.SlidingPaneLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.lhd.demolock.R;

/**
 * Created by D on 7/4/2017.
 */

public class Flag extends AppCompatActivity {
    SlidingPaneLayout slidingPaneLayout;
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ls_none);
        slidingPaneLayout = (SlidingPaneLayout) findViewById(R.id.dm_slide);
        slidingPaneLayout.setPanelSlideListener(new SlidingPaneLayout.PanelSlideListener() {
            @Override
            public void onPanelSlide(View panel, float slideOffset) {
                Main.showLog(""+slidingPaneLayout.isOpen());
            }

            @Override
            public void onPanelOpened(View panel) {

            }

            @Override
            public void onPanelClosed(View panel) {

            }
        });
    }


}
