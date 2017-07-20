package com.lhd.view.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.lhd.demolock.R;
import com.lhd.model.config.Config;
import com.lhd.model.object.BackgroundImageLockScreen;
import com.lhd.model.object.LockType;
import com.orhanobut.hawk.Hawk;

import static com.lhd.model.object.BackgroundImageLockScreen.loadImage;

/**
 * Created by D on 7/12/2017.
 */

public class SetPinActivity extends AppCompatActivity {
    TextView txtPin;
    TextView txtInputPin;
    ImageView imBg;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.set_pin_layout);
        Hawk.init(this).build();
        txtPin = (TextView) findViewById(R.id.tv_pin_input_txt_noti_input_pin_set_pin_layout);
        imBg = (ImageView) findViewById(R.id.im_bg_set_pin);
        txtInputPin = (TextView) findViewById(R.id.txt_noti_input_pin_set_pin_layout);
        txtInputPin.setText("Nhập mã pin gồm 4 chữ số");
        txtPin.setText("");
        loadBackground(imBg);
    }

    public void loadBackground(ImageView imgBackground) {
        try {
            loadImage(this, ((BackgroundImageLockScreen) Hawk.get(Config.IMAGE_BACKGROUND)).getPickImage(), imgBackground);
        } catch (NullPointerException e) {
            loadImage(this, "" + R.drawable.bg2, imgBackground);
        } catch (ClassCastException e) {
            loadImage(this, "a", imgBackground);
        }
    }

    public void onPinClick(View view) {
        String pin = (String) view.getTag();
        if (pinInPut.length() < 4) {
            pinInPut = txtPin.getText().toString() + pin;
            txtPin.setText(pinInPut);
            Log.e("duong", pinInPut);
            txtPin.clearAnimation();
        }
        if (pinInPut.length() == 4)
            checkPassCode();


    }

    String pinInPut = "";
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
                    Hawk.put(Config.TYPE_LOCK, new LockType(Config.MA_PIN, password1));
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

    private void checkPassCode() {
        if (password1.equals("")) {
            password1 = pinInPut;
            txtInputPin.setText("Xác nhận lại mã pin của bạn");
            pinInPut = "";
            txtPin.setText("");
        } else if (password2.equals("")) {
            password2 = pinInPut;
        }
        if (password2.equals(password1) && !password1.equals("") && !password2.equals("")) {
            txtInputPin.setText("Đã lưu lại mã pin của bạn");
            txtPin.setText("");
            setPinCodeCap2(this);

        } else if (!password2.equals(password1) && !password1.equals("") && !password2.equals("")) {
            txtInputPin.setText("Không khớp, Tạo lại mã pin của bạn");
            Animation shake = AnimationUtils.loadAnimation(this, R.anim.error_pin);
            txtPin.startAnimation(shake);
            password2 = "";
            password1 = "";
            pinInPut = "";
            txtPin.setText("");
        }
    }

    public void onXoaClick(View view) {
        if (pinInPut.length() > 0) {
            pinInPut = pinInPut.substring(0, pinInPut.length() - 1);
            txtPin.setText(pinInPut);
            txtPin.clearAnimation();
        }
    }

    public void onRefeshClick(View view) {
        txtInputPin.setText("Nhập mã pin gồm 4 chữ số");
        pinInPut = "";
        txtPin.setText(pinInPut);
        password1 = "";
        password2 = "";
    }

    public void onKhanCapClick(View view) {
        if (pinInPut.length() > 0) {
            pinInPut = pinInPut.substring(0, pinInPut.length() - 1);
            txtPin.setText(pinInPut);
            txtPin.clearAnimation();
        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);
    }
}
