package com.lhd.model.config;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.Contacts;
import android.util.Log;

import com.lhd.activity.Main;
import com.lhd.demolock.R;
import com.lhd.model.object.BackgroundImageLockScreen;
import com.lhd.model.object.FlagListNoti;
import com.lhd.model.object.ItemNotification;
import com.orhanobut.hawk.Hawk;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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
    public static final java.lang.String LIST_NOTI = "LIST_NOTI";
    private static ArrayList<BackgroundImageLockScreen> backgroundImageLockScreens;
    private static Object allNoti;
    Context context;

    public Config(Context context) {
        this.context = context;
        Hawk.init(context).build();
    }

    public static String getContactName(Context context, String phoneNumber) {
        Uri uri;
        String[] projection;
        Uri mBaseUri = Contacts.Phones.CONTENT_FILTER_URL;
        projection = new String[]{android.provider.Contacts.People.NAME};
        try {
            Class<?> c = Class.forName("android.provider.ContactsContract$PhoneLookup");
            mBaseUri = (Uri) c.getField("CONTENT_FILTER_URI").get(mBaseUri);
            projection = new String[]{"display_name"};
        } catch (Exception e) {
            Log.e("Config", "lỗi ");
            e.printStackTrace();
        }
        uri = Uri.withAppendedPath(mBaseUri, Uri.encode(phoneNumber));
        Cursor cursor = context.getContentResolver().query(uri, projection, null, null, null);
        String contactName = "";
        if (cursor.moveToFirst()) {
            contactName = cursor.getString(0);
        }
        cursor.close();
        return contactName + " [ " + phoneNumber + " ]";
    }

    public static String time24(Date date) {
        date.setHours(date.getHours());
        SimpleDateFormat simpDate;
        simpDate = new SimpleDateFormat("HH:mm");
        Main.showLog(simpDate.format(date));
        return simpDate.format(date);
    }

    public static ArrayList<BackgroundImageLockScreen> getBackgroundImageLockScreens() {
        backgroundImageLockScreens = new ArrayList<>();
        backgroundImageLockScreens.add(new BackgroundImageLockScreen("" + R.drawable.bg2));
        backgroundImageLockScreens.add(new BackgroundImageLockScreen("" + R.drawable.bg1));
        backgroundImageLockScreens.add(new BackgroundImageLockScreen("" + R.drawable.bg2));
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

    public ArrayList<ItemNotification> getAllNoti(Context context) {

        FlagListNoti flagListNoti;
        try {
            flagListNoti = (FlagListNoti) Hawk.get(Config.LIST_NOTI);
        } catch (NullPointerException e) {
            flagListNoti = new FlagListNoti(new ArrayList<ItemNotification>());
            Hawk.put(Config.LIST_NOTI, flagListNoti);
        }
        if (flagListNoti.getItemNotifications() == null) {
            flagListNoti = new FlagListNoti(new ArrayList<ItemNotification>());
            Hawk.put(Config.LIST_NOTI, flagListNoti);
            return flagListNoti.getItemNotifications();
        }
        return flagListNoti.getItemNotifications();
    }

    public void removeNoti(Context context) {
        getAllNoti(context).clear();
    }

    public void putNoti(ItemNotification itemNotification, Context context) {
        ArrayList<ItemNotification> itemNotifications = getAllNoti(context);
        itemNotifications.add(itemNotification);
        Hawk.put(Config.LIST_NOTI, itemNotifications);
    }
}
