package com.lhd.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lhd.demolock.R;
import com.orhanobut.hawk.Hawk;

/**
 * Created by D on 7/4/2017.
 */

public class Flag extends AppCompatActivity {
    private static final String TAG = "Flag";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notication);
        Hawk.init(this).build();
        RecyclerView recyclerView;
        ImageView imvClear;
        TextView imvRefresh;
        LinearLayout lbLayout;
        imvRefresh = (TextView) findViewById(R.id.im_refresh_noti_view);
        imvClear = (ImageView) findViewById(R.id.imv_clear_all_noti);
        recyclerView = (RecyclerView) findViewById(R.id.rcv_noti_ios);
        lbLayout = (LinearLayout) findViewById(R.id.frame_lb_noti);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
//        recyclerView.setHasFixedSize(true);
//        imvRefresh.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                recyclerView.setAdapter(new AdaptorNotiIos(Flag.this, getAllNoti(Flag.this)));
//                if (getAllNoti(Flag.this).size()==0) lbLayout.setVisibility(View.GONE);
//                else lbLayout.setVisibility(View.VISIBLE);
//            }
//        });
//        imvClear.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                removeNoti(Flag.this);
//                recyclerView.setAdapter(new AdaptorNotiIos(Flag.this, getAllNoti(Flag.this)));
//                if (getAllNoti(Flag.this).size()==0) lbLayout.setVisibility(View.GONE);
//                else lbLayout.setVisibility(View.VISIBLE);
//            }
//        });
//        recyclerView.setAdapter(new AdaptorNotiIos(this, getAllNoti(Flag.this)));
//        if (getAllNoti(this).size()==0) lbLayout.setVisibility(View.GONE);
//        else lbLayout.setVisibility(View.VISIBLE);
    }



}
