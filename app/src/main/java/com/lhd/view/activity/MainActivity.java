package com.lhd.view.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.lhd.activity.Main;
import com.lhd.demolock.R;
import com.lhd.fragment.Start;
import com.lhd.presenter.MainPresenter;
import com.lhd.presenter.MainPresenterImpl;
import com.lhd.view.MainView;
import com.lhd.view.fragment.SettingFragment;
import com.orhanobut.hawk.Hawk;

import java.io.FileNotFoundException;
import java.io.InputStream;

import static com.lhd.fragment.SelectImage.RESULT_LOAD_IMG;

/**
 * Created by D on 7/7/2017.
 */

public class MainActivity extends AppCompatActivity implements MainView {
    private MainPresenter mainPresenter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main);
        mainPresenter=new MainPresenterImpl(this);
        mainPresenter.checkStartedStartView();
//        startService(new Intent(this, LockScreen.class));
    }
    @Override
    public void startStartView() {
        getSupportFragmentManager().beginTransaction().replace(R.id.layout_container, new Start()).commit();
    }

    @Override
    public void startSettingView() {
        getSupportFragmentManager().beginTransaction().replace(R.id.layout_container, new SettingFragment()).commit();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_LOAD_IMG && resultCode == RESULT_OK) {
            try {
                Uri imageUri = data.getData();
                InputStream imageStream = getContentResolver().openInputStream(imageUri);
//                Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                Hawk.put(Main.IMAGE_BACKGROUND, imageUri);
                Main.showLog("RESULT_LOAD_IMG");
            } catch (FileNotFoundException e) {
                Main.showLog("FileNotFoundException");
                e.printStackTrace();
            }

        }
    }
}
