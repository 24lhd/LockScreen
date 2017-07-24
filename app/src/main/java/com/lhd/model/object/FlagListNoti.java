package com.lhd.model.object;

import java.util.ArrayList;

/**
 * Created by D on 7/24/2017.
 */

public class FlagListNoti {
    ArrayList<ItemNotification> itemNotifications;

    public ArrayList<ItemNotification> getItemNotifications() {
        return itemNotifications;
    }

    public void setItemNotifications(ArrayList<ItemNotification> itemNotifications) {
        this.itemNotifications = itemNotifications;
    }

    public FlagListNoti(ArrayList<ItemNotification> itemNotifications) {

        this.itemNotifications = itemNotifications;
    }
}
