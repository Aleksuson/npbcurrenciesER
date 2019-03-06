package com.aleksuson.npbcurrencieser.view.components.main;

import com.aleksuson.npbcurrencieser.repository.LocalRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Lazy
public class MainPresenter implements MainContract.Presenter {

    private MainContract.View mainView;
    private LocalRepository localRepository;

    public MainPresenter(MainContract.View mainView, LocalRepository localRepository) {
        this.mainView = mainView;
        this.localRepository = localRepository;
    }

    @Override
    public void showCurrency() {
        log.debug("in showPerson");

    }
}
