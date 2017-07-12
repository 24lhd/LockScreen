package com.lhd.model.object;

/**
 * Created by D on 7/5/2017.
 */

public class OnOff {
    public OnOff(boolean isTrue) {
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
