package com.lhd.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

import com.lhd.demolock.R;
import com.lhd.model.object.OnOff;
import com.lhd.presenter.StartPresenter;
import com.lhd.presenter.StartPresenterImpl;
import com.lhd.view.StartView;
import com.lhd.view.activity.MainActivity;
import com.orhanobut.hawk.Hawk;

import static com.lhd.activity.Main.IS_START;

/**
 * Created by D on 7/7/2017.
 */

public class StartFragment extends Fragment implements StartView, OnClickListener {
    private StartView startView;
    private StartPresenter startPresenter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View viewContent = inflater.inflate(R.layout.layout_start, null);
//        main = (Main) getActivity();
//        main.getSupportActionBar().hide();
//        Hawk.init(main).build();
        Button btnGotIt = viewContent.findViewById(R.id.btn_gotit);
        btnGotIt.setOnClickListener(this);
        startPresenter=new StartPresenterImpl(this);
        return viewContent;
    }

    @Override
    public void onClick(View view) {
        startSettingView();
    }

    @Override
    public void startSettingView() {
        Hawk.put(IS_START, new OnOff(true));
        startView.getMain().getSupportFragmentManager().beginTransaction().replace(R.id.layout_container, new SettingFragment()).commit();
    }

    @Override
    public MainActivity getMain() {
        return (MainActivity) getActivity();
    }
}
