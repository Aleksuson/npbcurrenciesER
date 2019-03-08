package com.aleksuson.npbcurrencieser.services;

import com.aleksuson.npbcurrencieser.domain.CurrencyRate;
import com.aleksuson.npbcurrencieser.repository.LocalRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class LocalRepositoryService {

    private LocalRepository localRepository;

    public LocalRepositoryService(LocalRepository localRepository) {
        this.localRepository = localRepository;
    }


    public List<CurrencyRate> findCurrencyFromDateToDate(String currencyCode, LocalDate startDate, LocalDate endDate) {
        return localRepository.findByCodeAndLocalDateBetweenOrderByLocalDate( currencyCode, startDate, endDate);
    }

}
