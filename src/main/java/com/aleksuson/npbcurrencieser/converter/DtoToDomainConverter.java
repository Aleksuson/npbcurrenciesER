package com.aleksuson.npbcurrencieser.converter;

import com.aleksuson.npbcurrencieser.domain.CurrencyRate;
import com.aleksuson.npbcurrencieser.dto.ExchangeRatesTable;
import com.aleksuson.npbcurrencieser.dto.Rate;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;


@Component
public class DtoToDomainConverter {

    public List<CurrencyRate> convert(List<ExchangeRatesTable> exchangeRatesTables) {

        return exchangeRatesTables.stream()
                .flatMap(table -> table.getRates()
                        .stream().map(rate -> createCurrency(table,rate))).collect(Collectors.toList());
    }

    private CurrencyRate createCurrency(ExchangeRatesTable table, Rate rate) {
        return new CurrencyRate(
                rate.getCode(),
                LocalDate.parse(table.getEffectiveDate()),
                rate.getMid());
    }

}
