package com.aleksuson.npbcurrencieser.view.components.main;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Lazy
public class MainPresenter implements MainContract.Presenter {

    private MainContract.View mainViewCtrl;

    public MainPresenter(MainContract.View mainView) {
        this.mainViewCtrl = mainViewCtrl;
    }

    @Override
    public void showPerson() {
        log.debug("in showPerson");

        mainViewCtrl.display();
    }
}
