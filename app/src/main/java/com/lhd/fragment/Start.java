package com.lhd.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.lhd.activity.Main;
import com.lhd.demolock.R;

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
        setView();
        return viewContent;
    }

    private void setView() {
        LayoutInflater inflater = LayoutInflater.from(main);
        View viewEnableLockScreen = inflater.inflate(R.layout.dialog_enable_lockscreen, null);
        final AlertDialog.Builder builder=new AlertDialog.Builder(main);
        builder.setView(viewEnableLockScreen);
        Button button=viewEnableLockScreen.findViewById(R.id.btGotIt_dialog);
        final AlertDialog alertDialog=  builder.create();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
                main.getSupportFragmentManager().beginTransaction().replace(R.id.layout_container, new Setting()).commit();
            }
        });
        alertDialog.show();
    }
}
