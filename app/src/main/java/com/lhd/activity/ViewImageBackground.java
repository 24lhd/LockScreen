package com.lhd.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.lhd.config.Data;
import com.lhd.demolock.R;
import com.lhd.fragment.Setting;
import com.lhd.object.BackgroundImageLockScreen;
import com.lhd.object.OnOff;
import com.orhanobut.hawk.Hawk;

/**
 * Created by D on 7/5/2017.
 */

public class ViewImageBackground extends Activity {
    int sizeImage;
    int index;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Hawk.init(this).build();
       fullManHinh();
        index = getIntent().getExtras().getInt(Main.INDEX_SELECT_IMAGE_BACKGROUND_LOCK_SCREEN);
        sizeImage = Data.getBackgroundImageLockScreens().size();
        setContentView(R.layout.layout_view_selected_image);
        Main.showLog(index + "");
        loadImageView(index);
        ((ImageView) findViewById(R.id.im_next_img_left)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (index == 0) {
                    index = sizeImage - 1;
                    loadImageView(index);
                    Main.showLog(" im_next_img_left " + index);
                    return;
                }else if (index > 0) {
                    index = index-1;
                    loadImageView(index);
                    Main.showLog(" im_next_img_left " + index);
                    return;
                }

            }
        });
        ((ImageView) findViewById(R.id.im_next_img_right)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (index == sizeImage - 1) {//2
                    index = 0;
                    loadImageView(index);
                    Main.showLog(" im_next_img_right if" + index);
                    return;
                } else if (index < sizeImage - 1) {
                    index = index+1;
                    loadImageView(index);
                    Main.showLog(" im_next_img_right  else if" + index);
                    return;
                }
            }
        });
        ((Button) findViewById(R.id.btn_set_image_backgroud)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Hawk.put(Main.IMAGE_BACKGROUND, new BackgroundImageLockScreen(Data.getBackgroundImageLockScreens().get(index).getPickImage()));
                finish();
                finish();
                overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);
            }
        });
        try {
            if (((OnOff) Hawk.get(Main.IS_24H)).isTrue())
                ((TextView) findViewById(R.id.tv_time_view)).setText(Setting.time24());
            else
                ((TextView) findViewById(R.id.tv_time_view)).setText(Setting.time12());
        } catch (NullPointerException e) {
            ((TextView) findViewById(R.id.tv_time_view)).setText(Setting.time12());
        }
        ((TextView) findViewById(R.id.tv_date_view )).setText(Setting.date());


    }

    private void fullManHinh() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    private void loadImageView(int indexImage) {
        BackgroundImageLockScreen.loadImage(this,Data.getBackgroundImageLockScreens().get(indexImage).getPickImage(),((ImageView) findViewById(R.id.im_bg_view_selected)));
//        Glide.with(this).load(Data.getBackgroundImageLockScreens().get(indexImage).getPickImage()).into(((ImageView) findViewById(R.id.im_bg_view_selected)));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.right_to_left, R.anim.left_to_right);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        overridePendingTransition(R.anim.right_to_left, R.anim.left_to_right);
    }
}
