package com.aleksuson.npbcurrencieser.repository;

import com.aleksuson.npbcurrencieser.converter.DtoToDomainConverter;
import com.aleksuson.npbcurrencieser.domain.CurrencyRate;
import com.aleksuson.npbcurrencieser.dto.ExchangeRatesTable;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class RemoteRepositoryTest {

    @Autowired
    private RemoteRepository remoteRepository;

    @Autowired
    private DtoToDomainConverter dtoToDomainConverter;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getTableFromDateToDate() {

        LocalDate startDate = LocalDate.of(2018,1,1);
        LocalDate endDate = LocalDate.of(2018,1,30);

        List<ExchangeRatesTable> exchangeRatesTableList = remoteRepository.getTableFromDateToDate(startDate, endDate, "A");

        assertNotNull(exchangeRatesTableList);
    }
}