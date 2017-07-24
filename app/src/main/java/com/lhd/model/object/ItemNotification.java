package com.lhd.model.object;

/**
 * Created by D on 7/24/2017.
 */

public class ItemNotification {
    String title;
    String date;
    String content;
    int icon;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public ItemNotification(String title, String date, String content, int icon) {
        this.title = title;
        this.date = date;
        this.content = content;
        this.icon = icon;
    }
}
