package com.aleksuson.npbcurrencieser.repository;

import com.aleksuson.npbcurrencieser.domain.CurrencyRate;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface LocalRepository extends CrudRepository<CurrencyRate, Long> {

    List<CurrencyRate> findByCodeAndLocalDateBetweenOrderByLocalDate(String currencyCode, LocalDate startDate, LocalDate endDate);


}
