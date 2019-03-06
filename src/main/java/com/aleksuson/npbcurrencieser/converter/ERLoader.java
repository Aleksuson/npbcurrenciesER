package com.aleksuson.npbcurrencieser.converter;

import com.aleksuson.npbcurrencieser.domain.CurrencyRate;
import com.aleksuson.npbcurrencieser.dto.ExchangeRatesTable;
import com.aleksuson.npbcurrencieser.repository.RemoteRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class ERLoader {

    private final DtoToDomainConverter dtoToDomainConverter;
    private final RemoteRepository remoteRepository;

    private final static LocalDate FIRST_AVAILABLE_CR_DATE = LocalDate.of(2002, 1, 2);
    private final static LocalDate DATE_NOW = LocalDate.now();

    public ERLoader(DtoToDomainConverter dtoToDomainConverter, RemoteRepository remoteRepository) {
        this.dtoToDomainConverter = dtoToDomainConverter;
        this.remoteRepository = remoteRepository;
    }

       public List<CurrencyRate> loadCurrencyExchangeRates() {

           List<CurrencyRate> currencyRates = new ArrayList<>();

           LocalDate startDate = FIRST_AVAILABLE_CR_DATE;
           LocalDate endDate = FIRST_AVAILABLE_CR_DATE;

           while (endDate.compareTo(DATE_NOW) < 0) {

               endDate = endDate.plusDays(90);
               if (endDate.compareTo(DATE_NOW) > 0) {
                   endDate = DATE_NOW;
               }

               List<ExchangeRatesTable> tableA = remoteRepository.getTableFromDateToDate(startDate, endDate, "A");
               List<ExchangeRatesTable> tableB = remoteRepository.getTableFromDateToDate(startDate, endDate, "B");

               List<CurrencyRate> currenciesA = dtoToDomainConverter.convert(tableA);
               List<CurrencyRate> currenciesB = dtoToDomainConverter.convert(tableB);

               currencyRates = concat(currencyRates, currenciesA,currenciesB);
               startDate = endDate.plusDays(1);
           }
           return currencyRates;
       }


       @SafeVarargs private static <T> List<T> concat(List<T>... lists) {
        return Stream.of(lists).flatMap(List::stream).collect(Collectors.toList());
    }

}
