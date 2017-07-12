package com.lhd.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lhd.demolock.R;
import com.lhd.model.object.BackgroundImageLockScreen;
import com.lhd.view.activity.NormalActivity;
import com.lhd.view.activity.PatternLockActivity;
import com.lhd.view.activity.SetPinActivity;

/**
 * Created by D on 7/7/2017.
 */

public class SelectTypeLock extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_type_lock);
        setView();
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);
    }
    private void setView() {
        BackgroundImageLockScreen.loadImage(this, "" + R.drawable.type_1, (ImageView) findViewById(R.id.im_type_1));
        ((TextView) findViewById(R.id.txt_ten_type_1)).setText("None");
        
        BackgroundImageLockScreen.loadImage(this, "" + R.drawable.type_2, (ImageView) findViewById(R.id.im_type_2));
        ((TextView) findViewById(R.id.txt_ten_type_2)).setText("Kiểu 2");
        ( findViewById(R.id.type_lock_partern)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SelectTypeLock.this, NormalActivity.class);
                startActivity(intent);
               overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);
            }
        });
        ( findViewById(R.id.type_lock_digital)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SelectTypeLock.this, SetPinActivity.class);
                startActivity(intent);
               overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);
            }
        });
        ( findViewById(R.id.type_lock_partern_02)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SelectTypeLock.this, PatternLockActivity.class);
                startActivity(intent);
               overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);
            }
        });
        BackgroundImageLockScreen.loadImage(this, "" + R.drawable.type_3, (ImageView) findViewById(R.id.im_type_3));
        ((TextView) findViewById(R.id.txt_ten_type_3)).setText("Kiểu 3");
        BackgroundImageLockScreen.loadImage(this, "" + R.drawable.type_1, (ImageView) findViewById(R.id.im_type_4));
        ((TextView) findViewById(R.id.txt_ten_type_4)).setText("Kiểu 4");
    }
}
