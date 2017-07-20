package com.lhd.model.config;

import com.lhd.demolock.R;
import com.lhd.model.object.BackgroundImageLockScreen;

import java.util.ArrayList;

/**
 * Created by D on 7/5/2017.
 */

public class Config {
    public static final java.lang.String SHOW_START = "SHOW_START";
    public static final String ENABLE_LOCK = "ENABLE_LOCK";
    public static final String SOUND = "SOUND";
    public static final String VIBRATION = "VIBRATION";
    public static final String FOMAT_TIME = "FOMAT_TIME";
    public static final String TYPE_LOCK = "TYPE_LOCK";
    public static final String MAU_HINH_TO = "MAU_HINH_TO";
    public static final String LOCK_NONE = "LOCK_NONE";
    public static final String MA_PIN = "MA_PIN";
    public static final String MAU_HINH_SMALL = "MAU_HINH_SMALL";
    public static final int SELECTED_TYPE = 1020;
    public static final String BACKGROUND_FROM_DRAWABLE = "BACKGROUND_FROM_DRAWABLE";
    public static final String BACKGROUND_FROM_STORE = "BACKGROUND_FROM_STORE";
    public static final String IMAGE_BACKGROUND_FLAG = "IMAGE_BACKGROUND_FLAG";
    public static final String IMAGE_BACKGROUND = "IMAGE_BACKGROUND";
    public static final int SELECTED_IMAGE_BACKGROUND = 1021;
    public static final int SELECTED_IMAGE_STORE_BACKGROUND = 1012;
    public static final String PIN_CAP_2 = "PIN_CAP_2";
    private static ArrayList<BackgroundImageLockScreen> backgroundImageLockScreens;

    public static ArrayList<BackgroundImageLockScreen> getBackgroundImageLockScreens() {
        backgroundImageLockScreens = new ArrayList<>();
        backgroundImageLockScreens.add(new BackgroundImageLockScreen(""+R.drawable.bg2));
        backgroundImageLockScreens.add(new BackgroundImageLockScreen(""+R.drawable.bg1));
        backgroundImageLockScreens.add(new BackgroundImageLockScreen(""+R.drawable.bg2));
//        backgroundImageLockScreens.add(new BackgroundImageLockScreen(R.drawable.a4));
//        backgroundImageLockScreens.add(new BackgroundImageLockScreen(R.drawable.a6));
//        backgroundImageLockScreens.add(new BackgroundImageLockScreen(R.drawable.a8));
//        backgroundImageLockScreens.add(new BackgroundImageLockScreen(R.drawable.a10));
//        backgroundImageLockScreens.add(new BackgroundImageLockScreen(R.drawable.a11));
//        backgroundImageLockScreens.add(new BackgroundImageLockScreen(R.drawable.a12));
//        backgroundImageLockScreens.add(new BackgroundImageLockScreen(R.drawable.a13));
//        backgroundImageLockScreens.add(new BackgroundImageLockScreen(R.drawable.a14));
//        backgroundImageLockScreens.add(new BackgroundImageLockScreen(R.drawable.a15));
//        backgroundImageLockScreens.add(new BackgroundImageLockScreen(R.drawable.a16));
//        backgroundImageLockScreens.add(new BackgroundImageLockScreen(R.drawable.a18));
//        backgroundImageLockScreens.add(new BackgroundImageLockScreen(R.drawable.a19));
//        backgroundImageLockScreens.add(new BackgroundImageLockScreen(R.drawable.a20));
//        backgroundImageLockScreens.add(new BackgroundImageLockScreen(R.drawable.a21));
//        backgroundImageLockScreens.add(new BackgroundImageLockScreen(R.drawable.a22));
//        backgroundImageLockScreens.add(new BackgroundImageLockScreen(R.drawable.a23));
//        backgroundImageLockScreens.add(new BackgroundImageLockScreen(R.drawable.a24));
//        backgroundImageLockScreens.add(new BackgroundImageLockScreen(R.drawable.a26));
//        backgroundImageLockScreens.add(new BackgroundImageLockScreen(R.drawable.a27));
//        backgroundImageLockScreens.add(new BackgroundImageLockScreen(R.drawable.a28));
//        backgroundImageLockScreens.add(new BackgroundImageLockScreen(R.drawable.a29));
//        backgroundImageLockScreens.add(new BackgroundImageLockScreen(R.drawable.a30));
//        backgroundImageLockScreens.add(new BackgroundImageLockScreen(R.drawable.a31));
//        backgroundImageLockScreens.add(new BackgroundImageLockScreen(R.drawable.a32));
//        backgroundImageLockScreens.add(new BackgroundImageLockScreen(R.drawable.a33));
//        backgroundImageLockScreens.add(new BackgroundImageLockScreen(R.drawable.a34));
//        backgroundImageLockScreens.add(new BackgroundImageLockScreen(R.drawable.a35));
//        backgroundImageLockScreens.add(new BackgroundImageLockScreen(R.drawable.a36));
//        backgroundImageLockScreens.add(new BackgroundImageLockScreen(R.drawable.a37));
//        backgroundImageLockScreens.add(new BackgroundImageLockScreen(R.drawable.a38));
//        backgroundImageLockScreens.add(new BackgroundImageLockScreen(R.drawable.a39));
//        backgroundImageLockScreens.add(new BackgroundImageLockScreen(R.drawable.a40));
//        backgroundImageLockScreens.add(new BackgroundImageLockScreen(R.drawable.a41));
//        backgroundImageLockScreens.add(new BackgroundImageLockScreen(R.drawable.a42));
//        backgroundImageLockScreens.add(new BackgroundImageLockScreen(R.drawable.a43));
//        backgroundImageLockScreens.add(new BackgroundImageLockScreen(R.drawable.a44));
//        backgroundImageLockScreens.add(new BackgroundImageLockScreen(R.drawable.a45));
//        backgroundImageLockScreens.add(new BackgroundImageLockScreen(R.drawable.a46));
//        backgroundImageLockScreens.add(new BackgroundImageLockScreen(R.drawable.a47));
//        backgroundImageLockScreens.add(new BackgroundImageLockScreen(R.drawable.a48));
//        backgroundImageLockScreens.add(new BackgroundImageLockScreen(R.drawable.a49));
        return backgroundImageLockScreens;
    }
}
