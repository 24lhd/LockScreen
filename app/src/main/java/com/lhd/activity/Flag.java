package com.lhd.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.lhd.demolock.R;
import com.lhd.model.adaptor.AdaptorNotiIos;
import com.lhd.model.config.Config;

/**
 * Created by D on 7/4/2017.
 */

public class Flag extends AppCompatActivity {
    private static final String TAG = "Flag";
    RecyclerView recyclerView;
    ImageView imvClear;
    ImageView imvRefresh;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notication);
        imvRefresh = (ImageView) findViewById(R.id.im_refresh_noti_view);
        imvClear = (ImageView) findViewById(R.id.imv_clear_all_noti);
        recyclerView = (RecyclerView) findViewById(R.id.rcv_noti_ios);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        imvRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recyclerView.setAdapter(new AdaptorNotiIos(Flag.this, new Config(Flag.this).getAllNoti(Flag.this)));
            }
        });
        imvClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Config(Flag.this).removeNoti(Flag.this);
                recyclerView.setAdapter(new AdaptorNotiIos(Flag.this, new Config(Flag.this).getAllNoti(Flag.this)));
            }
        });
        recyclerView.setAdapter(new AdaptorNotiIos(this, new Config(this).getAllNoti(this)));
    }


}
