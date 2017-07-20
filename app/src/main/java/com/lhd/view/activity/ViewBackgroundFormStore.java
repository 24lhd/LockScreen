package com.lhd.view.activity;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.lhd.demolock.R;
import com.lhd.model.config.Config;
import com.lhd.model.object.BackgroundImageLockScreen;
import com.orhanobut.hawk.Hawk;

import static com.lhd.model.config.Config.IMAGE_BACKGROUND;
import static com.lhd.model.object.BackgroundImageLockScreen.loadImage;

/**
 * Created by D on 7/19/2017.
 */

public class ViewBackgroundFormStore extends Activity {
    int sizeImage;
    int index;
    Uri imageUri;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Hawk.init(this).build();
        fullManHinh();
        setContentView(R.layout.layout_view_selected_image_from_store);
        imageUri = (Uri) Hawk.get(Config.IMAGE_BACKGROUND_FLAG);
        Glide.with(this).load((Uri) Hawk.get(Config.IMAGE_BACKGROUND_FLAG)).
                into(((ImageView) findViewById(R.id.im_bg_view_selected_store)));
        ((Button) findViewById(R.id.btn_set_image_backgroud_from_store)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(RESULT_OK);
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
//        ((TextView) findViewById(R.id.tv_date_view)).setText(Setting.date());


    }

    public void loadBackground(ImageView imgBackground) {
        try {
            loadImage(ViewBackgroundFormStore.this, ((BackgroundImageLockScreen) Hawk.get(IMAGE_BACKGROUND)).getPickImage(), imgBackground);
        } catch (NullPointerException e) {
            loadImage(ViewBackgroundFormStore.this, "" + R.drawable.bg2, imgBackground);
        } catch (ClassCastException e) {
            loadImage(ViewBackgroundFormStore.this, "a", imgBackground);
        }
    }

    private void fullManHinh() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    private void loadImageView(int indexImage) {
        loadImage(this, Config.getBackgroundImageLockScreens().get(indexImage).getPickImage(), ((ImageView) findViewById(R.id.im_bg_view_selected)));
    }

}
