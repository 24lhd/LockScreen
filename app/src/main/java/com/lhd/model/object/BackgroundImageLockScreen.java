package com.lhd.model.object;

import android.content.Context;
import android.net.Uri;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.orhanobut.hawk.Hawk;

import static com.lhd.activity.Main.IMAGE_BACKGROUND;

/**
 * Created by D on 7/5/2017.
 */

public class BackgroundImageLockScreen {
    private String pickImage;

    public String getPickImage() {
        return pickImage;
    }

    public void setPickImage(String pickImage) {
        this.pickImage = pickImage;
    }

    public BackgroundImageLockScreen(String pickImage) {
        this.pickImage = pickImage;
    }

    public static void loadImage(Context activity, String uri, ImageView imageView) {
        try {
            Glide.with(activity).load(Integer.parseInt(uri)).into(imageView);
        } catch (NumberFormatException e) {
            Glide.with(activity).load((Uri) Hawk.get(IMAGE_BACKGROUND)).into(imageView);
        }
    }

}
