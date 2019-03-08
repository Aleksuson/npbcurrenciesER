package com.aleksuson.npbcurrencieser.domain;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
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
    @Column(columnDefinition="DECIMAL(19,6)")
    private BigDecimal rate;

    public CurrencyRate(String code, LocalDate localDate, BigDecimal rate) {
        this.code = code;
        this.localDate = localDate;
        this.rate = rate;
    }
}
