package com.lhd.object;

/**
 * Created by D on 7/4/2017.
 */

public class EnableLock {
    public EnableLock(boolean isTrue) {
        this.isTrue = isTrue;
    }

    private boolean isTrue;

    public boolean isTrue() {
        return isTrue;
    }

    public void setTrue(boolean aTrue) {
        isTrue = aTrue;
    }
}