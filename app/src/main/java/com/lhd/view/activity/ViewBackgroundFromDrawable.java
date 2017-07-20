package com.lhd.view.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import com.lhd.activity.Main;
import com.lhd.demolock.R;
import com.lhd.model.config.Config;
import com.lhd.model.object.BackgroundImageLockScreen;
import com.orhanobut.hawk.Hawk;

import static com.lhd.model.object.BackgroundImageLockScreen.loadImage;

/**
 * Created by D on 7/19/2017.
 */

public class ViewBackgroundFromDrawable  extends Activity {
    int sizeImage;
    int index;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Hawk.init(this).build();
        fullManHinh();
        index = getIntent().getExtras().getInt(Config.BACKGROUND_FROM_DRAWABLE);
        sizeImage = Config.getBackgroundImageLockScreens().size();
        setContentView(R.layout.layout_view_selected_image_from_drawable);
        Main.showLog(index + "");
        loadImageView(index);
        ((ImageView) findViewById(R.id.im_next_img_left)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (index == 1) {
                    index = sizeImage - 1;
                    loadImageView(index);
                    Main.showLog(" im_next_img_left " + index);
                    return;
                }else if (index > 1) {
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
                    index = 1;
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
                Hawk.put(Config.IMAGE_BACKGROUND, new BackgroundImageLockScreen(Config.getBackgroundImageLockScreens().get(index).getPickImage()));
                finish();
                overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);
            }
        });
        try {
//            if (((OnOff) Hawk.get(Main.IS_24H)).isTrue())
//                ((TextView) findViewById(R.id.tv_time_view)).setText(Setting.time24());
//            else
//                ((TextView) findViewById(R.id.tv_time_view)).setText(Setting.time12());
        } catch (NullPointerException e) {
//            ((TextView) findViewById(R.id.tv_time_view)).setText(Setting.time12());
        }
//        ((TextView) findViewById(R.id.tv_date_view )).setText(Setting.date());


    }

    private void fullManHinh() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    private void loadImageView(int indexImage) {
        loadImage(this, Config.getBackgroundImageLockScreens().get(indexImage).getPickImage(),((ImageView) findViewById(R.id.im_bg_view_selected)));
//        Glide.with(this).load(Config.getBackgroundImageLockScreens().get(indexImage).getPickImage()).into(((ImageView) findViewById(R.id.im_bg_view_selected)));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);
    }
}
