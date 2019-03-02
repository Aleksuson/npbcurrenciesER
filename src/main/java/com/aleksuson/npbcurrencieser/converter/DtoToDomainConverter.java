package com.aleksuson.npbcurrencieser.converter;

import com.aleksuson.npbcurrencieser.domain.CurrencyRate;
import com.aleksuson.npbcurrencieser.dto.ExchangeRatesTable;
import com.aleksuson.npbcurrencieser.dto.Rate;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;


@Component
public class DtoToDomainConverter {


    public List<CurrencyRate> convert(List<ExchangeRatesTable> exchangeRatesTables) {

        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;

        return exchangeRatesTables.stream()
                .flatMap(table -> table.getRates()
                        .stream().map(getRateCurrencyRateFunction(table))).collect(Collectors.toList());
    }

    private Function<Rate, CurrencyRate> getRateCurrencyRateFunction(ExchangeRatesTable table) {
        return rate -> new CurrencyRate(rate.getCurrency(),
                rate.getCode(),
                LocalDate.parse(table.getEffectiveDate()));
    }


}
