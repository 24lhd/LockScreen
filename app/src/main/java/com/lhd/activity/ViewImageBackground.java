package com.lhd.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.lhd.demolock.R;

/**
 * Created by D on 7/5/2017.
 */

public class ViewImageBackground extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int index = getIntent().getExtras().getInt(Main.INDEX_SELECT_IMAGE_BACKGROUND_LOCK_SCREEN);

        setContentView(R.layout.layout_view_selected_image);
        Main.showLog(index + "");

    }
}
