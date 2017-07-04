package com.lhd.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.lhd.activity.Main;
import com.lhd.demolock.R;

/**
 * Created by D on 7/4/2017.
 */

public class SelectTypeLock extends Fragment {
    private View viewContent;
    private Main main;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewContent = inflater.inflate(R.layout.item_type_lock, null);
        main = (Main) getActivity();
        ImageView imageView1 = viewContent.findViewById(R.id.type_1);
        Glide.with(main).load(R.drawable.type_1).into(imageView1);

        ImageView imageView2 = viewContent.findViewById(R.id.type_2);
        Glide.with(main).load(R.drawable.type_2).into(imageView2);

        ImageView imageView3 = viewContent.findViewById(R.id.type_3);
        Glide.with(main).load(R.drawable.type_3).into(imageView3);

        ImageView imageView4 = viewContent.findViewById(R.id.type_4);
        Glide.with(main).load(R.drawable.type_1).into(imageView4);
        //setView();
        return viewContent;
    }

}