package com.lhd.adaptor;

import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.view.View;

import com.lhd.fragment.LockScreenLeftFragment;
import com.lhd.fragment.LockScreenMidFragment;
import com.lhd.fragment.LockScreenRightFragment;

/**
 * Created by D on 7/17/2017.
 */

public class AdaptorLock extends PagerAdapter {

    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new LockScreenLeftFragment();

            case 1:
                return new LockScreenMidFragment();
            case 2:
                return new LockScreenRightFragment();
        }
        return new LockScreenMidFragment();
    }



    @Override
    public Object instantiateItem(View container, int position) {
        return super.instantiateItem(container, position);
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return false;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "SECTION 1";
            case 1:
                return "SECTION 2";
            case 2:
                return "SECTION 3";
        }
        return null;
    }
}