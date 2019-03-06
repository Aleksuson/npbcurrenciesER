package com.aleksuson.npbcurrencieser.domain;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Data
@Entity
@NoArgsConstructor
public class CurrencyRate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String code;
    private LocalDate localDate;
    private Double rate;

    public CurrencyRate(String code, LocalDate localDate, Double rate) {
        this.code = code;
        this.localDate = localDate;
        this.rate = rate;
    }
}
