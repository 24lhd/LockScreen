package com.lhd.activity;

import android.content.Context;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.lhd.demolock.R;

/**
 * Created by D on 7/4/2017.
 */

public class Flag extends AppCompatActivity implements View.OnTouchListener {

        private WindowManager windowManager;
        private int widthPixels;
        private int heightPixels;
        private View view;

        @Override
        protected void onCreate(@Nullable Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                windowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
                DisplayMetrics outMetrics = new DisplayMetrics();
                windowManager.getDefaultDisplay().getMetrics(outMetrics);
                widthPixels = outMetrics.widthPixels;
                heightPixels = outMetrics.heightPixels;
                view = View.inflate(this, R.layout.activity_main, null);
                view.setOnTouchListener(this);

                ImageView imgTouch = new ImageView(this);
//                imgTouch.setImageResource((app.getThemeList().get(app.getDisplayTheme())).intValue());
                imgTouch.setImageDrawable(getResources().getDrawable(R.drawable.ic_help_black_36dp));
                final WindowManager.LayoutParams params = new WindowManager.LayoutParams();

                params.gravity = Gravity.LEFT + Gravity.TOP;

//                params.x = mSharedPreferences.getInt("Left", 0);
//                params.y = mSharedPreferences.getInt("Top", 0);

                params.height = WindowManager.LayoutParams.MATCH_PARENT;
                params.width = WindowManager.LayoutParams.MATCH_PARENT;
                params.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;// |

                params.format = PixelFormat.TRANSLUCENT;

                params.type = WindowManager.LayoutParams.TYPE_TOAST;

                windowManager.addView(view, params);
        }

        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
                return false;
        }
}
