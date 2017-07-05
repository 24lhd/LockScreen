package com.lhd.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.lhd.activity.Main;
import com.lhd.demolock.R;
import com.lhd.object.OnOff;
import com.orhanobut.hawk.Hawk;

import static com.lhd.activity.Main.IS_START;

/**
 * Created by D on 7/3/2017.
 */

public class Start extends Fragment {
    private View viewContent;
    private Main main;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewContent = inflater.inflate(R.layout.layout_start, null);
        main = (Main) getActivity();
        main.getSupportActionBar().hide();
        setView();
        return viewContent;
    }

    private void setView() {
        Hawk.init(main).build();
        Button btnGotIt = viewContent.findViewById(R.id.btn_gotit);
        btnGotIt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Hawk.put(IS_START,new OnOff(true));
                main.getSupportFragmentManager().beginTransaction().replace(R.id.layout_container, new Setting()).commit();
            }
        });
    }


}
