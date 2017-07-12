package com.lhd.presenter;

import com.lhd.model.StartModel;
import com.lhd.model.StartModelImpl;
import com.lhd.view.MainView;
import com.lhd.view.fragment.StartFragment;

/**
 * Created by D on 7/7/2017.
 */

public class StartPresenterImpl implements StartPresenter {

    private StartFragment startFragment;
    private StartModel startModel;

    public StartPresenterImpl(StartFragment contextMainView) {
        startFragment=contextMainView;
        startModel=new StartModelImpl();
    }

    @Override
    public void setDidShowStartView(boolean b, MainView mainView) {
        startModel.putDidShowStartView(true, (MainView) startFragment.getActivity());
    }
}
