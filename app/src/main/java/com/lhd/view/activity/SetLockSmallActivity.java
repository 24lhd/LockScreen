package com.lhd.view.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.lhd.activity.Main;
import com.lhd.demolock.R;
import com.lhd.model.config.Config;
import com.lhd.model.object.BackgroundImageLockScreen;
import com.lhd.model.object.LockType;
import com.orhanobut.hawk.Hawk;
import com.takwolf.android.lock9.Lock9View;

/**
 * Created by D on 7/18/2017.
 */

public class SetLockSmallActivity  extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Hawk.init(this).build();
        setContentView(R.layout.set_lock_type_small);
        setViewLockNormal();
    }
    TextView tvNoti;
    ImageView imLeft;
    ImageView imRight;
    ImageView imbg;
    Lock9View lock9View;
    String password1 = "";
    String password2 = "";
    private void setPinCodeCap2(final Context context) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        View viewContent = View.inflate(context, R.layout.set_pin_cap_2, null);
        builder.setView(viewContent);
        Button btnSubmit = viewContent.findViewById(R.id.set_pin_cap2_btn_submit);
        final EditText edtInput1 = viewContent.findViewById(R.id.set_pin_cap2_txt_input_ma_pin_1);
        final EditText edtInput2 = viewContent.findViewById(R.id.set_pin_cap2_txt_input_ma_pin_2);
        final AlertDialog alertDialog = builder.create();
        alertDialog.setView(viewContent);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String input1 = edtInput1.getText().toString();
                String input2 = edtInput2.getText().toString();
                if (input1.length() < 4)
                    Toast.makeText(context, "Bạn hãy nhập đầy đủ 4 kí tự", Toast.LENGTH_SHORT).show();
                else if (!input1.equals(input2)) {
                    Toast.makeText(context, "Mã pin nhập không giống nhau", Toast.LENGTH_SHORT).show();
                } else {
                    Hawk.put(Config.PIN_CAP_2, input1);
                    Hawk.put(Config.TYPE_LOCK,new LockType(Config.MAU_HINH_SMALL,password1));
                    Toast.makeText(context, "Đã tạo mật khẩu cấp 2", Toast.LENGTH_SHORT).show();
                    alertDialog.dismiss();
                    finish();
                }
            }
        });
        alertDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                Toast.makeText(context, "alertDialog onDismiss", Toast.LENGTH_SHORT).show();
            }
        });
        alertDialog.show();
    }
    private void setViewLockNormal() {
        lock9View = (Lock9View) findViewById(R.id.lock_9_view);
        tvNoti = (TextView) findViewById(R.id.txt_type_noti_set_lock_nomal);
        tvNoti.setText("Vẽ mẫu hình của bạn");
        imLeft = (ImageView) findViewById(R.id.im_next_img_left_lock_normal);
        imRight = (ImageView) findViewById(R.id.im_next_img_right_lock_normal);
        imbg = (ImageView) findViewById(R.id.bg_im_lock_small);
        try {
            BackgroundImageLockScreen.loadImage(this, ((BackgroundImageLockScreen) Hawk.get(Config.IMAGE_BACKGROUND)).getPickImage(), imbg);
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
                                          setPinCodeCap2(SetLockSmallActivity.this);
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


