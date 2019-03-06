package com.aleksuson.npbcurrencieser.repository;

import com.aleksuson.npbcurrencieser.domain.CurrencyRate;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocalRepository extends CrudRepository<CurrencyRate, Long> {

}
