package com.aleksuson.npbcurrencieser.view.components.main;

import com.aleksuson.npbcurrencieser.domain.CurrencyRate;
import com.aleksuson.npbcurrencieser.view.components.BaseView;

import java.util.List;

public interface MainContract {

    interface View extends BaseView<Presenter> {

        void showCurrencyOnChart(List<CurrencyRate> currencyRates);
    }

    interface Presenter {

        void showCurrency();
    }
}

