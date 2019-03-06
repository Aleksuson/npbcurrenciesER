package com.aleksuson.npbcurrencieser.bootstrapDb;


import com.aleksuson.npbcurrencieser.converter.ERLoader;
import com.aleksuson.npbcurrencieser.domain.CurrencyRate;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BootStrapH2 implements ApplicationListener<ContextRefreshedEvent> {

    private final CrudRepository<CurrencyRate,Long> localRepository;
    private final ERLoader erLoader;


    public BootStrapH2(CrudRepository<CurrencyRate, Long> localRepository, ERLoader erLoader) {
        this.localRepository = localRepository;
        this.erLoader = erLoader;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {

        List<CurrencyRate> currencyRates = erLoader.loadCurrencyExchangeRates();

        localRepository.saveAll(currencyRates);

    }




}
