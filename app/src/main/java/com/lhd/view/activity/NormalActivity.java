package com.lhd.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lhd.activity.Main;
import com.lhd.demolock.R;
import com.lhd.model.object.BackgroundImageLockScreen;
import com.orhanobut.hawk.Hawk;
import com.takwolf.android.lock9.Lock9View;

public class NormalActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normal);
        setViewLockNormal();
        Hawk.init(this).build();
    }
    TextView tvNoti;
    ImageView imLeft;
    ImageView imRight;
    ImageView imbg;
    Lock9View lock9View;
    String password1 = "";
    String password2 = "";

    private void setViewLockNormal() {
        lock9View = (Lock9View) findViewById(R.id.lock_9_view);
        tvNoti = (TextView) findViewById(R.id.txt_type_noti_set_lock_nomal);
        tvNoti.setText("Vẽ mẫu hình của bạn");
        imLeft = (ImageView) findViewById(R.id.im_next_img_left_lock_normal);
        imRight = (ImageView) findViewById(R.id.im_next_img_right_lock_normal);
        imbg = (ImageView) findViewById(R.id.bg_im_lock_nomal);
        try {
            BackgroundImageLockScreen.loadImage(this, ((BackgroundImageLockScreen) Hawk.get(Main.IMAGE_BACKGROUND)).getPickImage(), imbg);
        } catch (NullPointerException e) {
            BackgroundImageLockScreen.loadImage(this, "" + R.drawable.bg2, imbg);
        } catch (ClassCastException e) {
            BackgroundImageLockScreen.loadImage(this, "a", imbg);
        }
        lock9View.setCallBack(new Lock9View.CallBack() {
                                  public void onFinish(String password) {
                                      if (password1.equals("")) {
                                          password1 = password;
                                          tvNoti.setText("Xác nhận lại mẫu hình của bạn");
                                      } else if (password2.equals("")) {
                                          password2 = password;
                                      }
                                      Main.showLog("password1 " + password1);
                                      Main.showLog("password2 " + password2);
                                      if (password2.equals(password1) && !password1.equals("") && !password2.equals("")) {
                                          tvNoti.setText("Đã lưu mấu hình của bạn");
                                      } else if (!password2.equals(password1) && !password1.equals("") && !password2.equals("")) {
                                          tvNoti.setText("Không khớp, Tạo lại mẫu hình của bạn");
                                          password2 = "";
                                          password1 = "";
                                      }
                                  }
                              }
        );
        imLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (password2.equals(password1) && !password1.equals("") && !password2.equals("")) {
                    tvNoti.setText("Vẽ mẫu hình của bạn");
                    password2 = "";
                    password1 = "";
                } else if (!password1.equals("") || !password2.equals("")) {
                    tvNoti.setText("Vẽ mẫu hình của bạn");
                    password2 = "";
                    password1 = "";
                } else if (password1.equals("") || password2.equals("")) {
                    finish();
                }
            }
        });

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);
    }
}


