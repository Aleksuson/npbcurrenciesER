package com.aleksuson.npbcurrencieser.view.components.main;

import com.aleksuson.npbcurrencieser.view.components.BaseView;

public interface MainContract {

    interface View extends BaseView<Presenter> {

        void display();
    }

    interface Presenter {

        void showPerson();
    }
}

