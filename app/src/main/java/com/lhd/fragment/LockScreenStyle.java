package com.lhd.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lhd.activity.Main;
import com.lhd.demolock.R;

/**
 * Created by D on 7/4/2017.
 */

public class LockScreenStyle extends Fragment {
    private View viewContent;
    private Main main;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewContent = inflater.inflate(R.layout.activity_main, null);
//        main = (Main) getActivity();
//        ImageView imageView=viewContent.findViewById(R.id.im_bg_selected);
//        Glide.with(main).load(R.drawable.bg_main).into(imageView);
        //setView();
        return viewContent;
    }

}