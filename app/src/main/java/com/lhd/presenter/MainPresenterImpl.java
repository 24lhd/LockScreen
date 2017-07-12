package com.lhd.presenter;

import com.lhd.model.MainModelImpl;
import com.lhd.view.MainView;
import com.lhd.view.activity.MainActivity;

/**
 * Created by D on 7/7/2017.
 */

public class MainPresenterImpl implements MainPresenter {
    private MainView mainView;
    private MainModelImpl mainModel;

    public MainPresenterImpl(MainView mainView) {
        this.mainView = mainView;
        mainModel=new MainModelImpl();
    }

    @Override
    public void checkStartedStartView() {
        if (mainModel.isDidShowStartView((MainActivity) mainView))mainView.startSettingView();
        else mainView.startStartView();
    }
}
